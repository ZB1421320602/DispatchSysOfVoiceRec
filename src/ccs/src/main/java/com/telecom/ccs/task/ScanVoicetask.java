package com.telecom.ccs.task;

import com.telecom.ccs.utils.file.SupportFormat;
import com.telecom.ccs.utils.file.TaskDto;
import com.telecom.ccs.utils.file.TxtDto;
import com.telecom.ccs.utils.file.VoiceDto;
import javafx.concurrent.Task;
import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描 ftp server 上一个固定目录下的语音任务
 */
@Data
public class ScanVoicetask {

    Logger logger = LoggerFactory.getLogger(ScanVoicetask.class);

    private StringBuffer onlyVoice = new StringBuffer("");  //记录异常： 只有语音文件
    private StringBuffer onlyTxt = new StringBuffer("");  //记录异常： 只有随路信息文件
    private StringBuffer exceptions = new StringBuffer("");  //记录异常： 语音文件异常

    private FTPClient ftp  = null;

    private   SupportFormat fmt_vioce = new SupportFormat("wav V3");
    private   SupportFormat fmt_txt = new SupportFormat(" txt");


    private   ArrayList<VoiceDto> allVoiceFiles = new ArrayList<VoiceDto>();
    private   ArrayList<TxtDto> allTxtFiles = new ArrayList<TxtDto>();
    private  ArrayList<TaskDto> alltask = new ArrayList<TaskDto>();


    public ScanVoicetask(){
        ftp =  new FTPClient();
    }


    public  ArrayList<TaskDto> run(String hostname,int port,String username,String password,String scanPath) {

        try {
            boolean flag = login(hostname,port,username,password);
            System.out.println("login flag: "+flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ftp.isConnected());

        try {
            logger.info("开始扫描"+scanPath+"下的任务 ..");
            scanDir(scanPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("all voice size: "+allVoiceFiles.size());
        System.out.println("all txt size: "+allTxtFiles.size());

        for(VoiceDto voiceDto: allVoiceFiles){
            System.out.println("voice file: "+voiceDto.getPath() +"        size:"+voiceDto.getSize());
        }

        for(TxtDto txtDto: allTxtFiles){
            System.out.println("voice txt: "+txtDto.getPath());
        }

        isFTPFileExist(allVoiceFiles,allTxtFiles);

        logger.info("exceptions:"+exceptions.toString());
        logger.info("onlyTxt:"+onlyTxt.toString());
        logger.info("onlyVoice:"+onlyVoice.toString());


        /*本批次如若有异常，则写异常文件到ftp*/
        writeTxt2ftp("/home/zhangbo","onlyVoice.txt",onlyVoice.toString());
        writeTxt2ftp("/home/zhangbo","onlyTxt.txt",onlyTxt.toString());
        writeTxt2ftp("/home/zhangbo","exceptions.txt",exceptions.toString());

        close(ftp);

        return alltask;

    }

    /* 关闭ftp 连接 */
    public  void close(FTPClient client){
        if(client.isConnected()){
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* 登陆 */
    public   boolean login(String hostname,int port ,String username,String password) throws  IOException{

        ftp.setConnectTimeout(10*1000); // 设置10s 连接不上就包超时异常
        ftp.connect(hostname,port);
        if(ftp.isConnected()){
            if(ftp.login(username,password)){
                logger.info("login ftp server success ...");
                return true;
            }else {
                logger.error("login ftp server failed ...");

            }
        }

        return false;
    }

    /* 扫描任务*/
    public  void scanDir(String pathName) throws IOException{

        if(pathName.startsWith("/") ){

            // add to do 目录是否存在

            String directory = pathName;
            ftp.changeWorkingDirectory(directory);


            FTPFile[] files = ftp.listFiles();
            for(FTPFile file : files){
                if(file.isFile()){
                    String exname = SupportFormat.getExtensionName(file.getName());
                    if(fmt_vioce.isSupport(exname)){

                        VoiceDto voiceDto = new VoiceDto(directory+file.getName(),file.getSize());
                        allVoiceFiles.add(voiceDto);
                    }else if(fmt_txt.isSupport(exname)){
                        //校验txt
                       String[]   recordedInfo = analysisTxt(directory+file.getName());

                      /*  byte[] buff = file.getName().getBytes("UTF-8");
                        System.out.println(new String(buff,"UTF-8"));*/

                        TxtDto txtDto = new TxtDto(directory+file.getName(),recordedInfo);
                        allTxtFiles.add(txtDto);
                    }
                }else if(file.isDirectory()){
                    scanDir(directory+file.getName()+"/");
                }
            }


        }
    }

    public  String[] analysisTxt(String path) throws  IOException{

        StringBuilder stringBuilder = new StringBuilder("");
        BufferedReader br = null;
        InputStream is = ftp.retrieveFileStream(path);

        try {

            br = new BufferedReader(new InputStreamReader(is));
            String line ;
            while ((line=br.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is!=null) is.close();
            if(br!=null) br.close();
            ftp.completePendingCommand();

            /*
            * 官方说： 完成文件传输必须调用completependingcommand和检查它的返回值来验证成功。
如果没有这么做，后续命令可能会出错。 简单来说：completePendingCommand()会一直等 ftp server返回226
Transfer complete,但是ftp server 只要在接受到inputstream 执行close方法是，才会返回。所以要先执行close方法
            * */
        }

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString().split("\\|");
    }



    /*   对 txt voice 进行校验*/
    public  void  isFTPFileExist(List<VoiceDto> voiceDtoList, List<TxtDto> txtDtoList){

        for(TxtDto txt : txtDtoList){
            String[] splits = txt.getRecordedInfo();
            String serialNumber = splits[0];
            String fileSize = splits[1];
            String txtPath = txt.getPath();

            String voicePath = getVoicePath(txtPath,serialNumber);
            boolean flag_voicefileisExist = false;

           for(VoiceDto voice : voiceDtoList){
               if(voice.getPath().contains(voicePath)){
                   //System.out.println("对应录音文件存在。");
                   flag_voicefileisExist = true;
                   if(voice.getSize()==Integer.parseInt(fileSize)){
                      // logger.info("对应录音文件大小一致。");
                       alltask.add(new TaskDto(voice.getPath(),voice.getSize(),txt.getPath(),txt.getRecordedInfo()));
                   }else{
                       logger.error("校验不合法，文件大小不一致！ 描述: "+txt.getPath()+"|"+fileSize+"|"+voice.getPath()+"|"+voice.getSize());
                       exceptions.append(txt.getPath()+"|"+fileSize+"|"+voice.getPath()+"|"+voice.getSize()+"\r\n");
                   }
               }
           }

           if(!flag_voicefileisExist){
               logger.error("校验不合法，只有随路信息文件！ 描述： "+ txt.getPath()+"|"+serialNumber);
               onlyTxt.append(txt.getPath()+"|"+serialNumber+"\r\n");
           }

        }



        for(VoiceDto voiceDto: voiceDtoList){
           String path =  voiceDto.getPath().substring(0,voiceDto.getPath().lastIndexOf("."));
           boolean isTxtExist = false;
           for(TxtDto txt:txtDtoList){
               if(txt.getPath().contains(path)){
                   //对应的 txt 文件存在
                   isTxtExist = true;
               }
           }

           if(!isTxtExist){
               logger.error("校验不合法，只有录音文件！ 描述： "+ voiceDto.getPath());
               onlyVoice.append(voiceDto.getPath()+"\r\n");
           }

        }
    }


    public   String  getVoicePath(String txtPath,String serialNumber){
        String path = txtPath.substring(0,txtPath.lastIndexOf("/"));
        //String file = path+"/"+serialNumber+".wav";

        String file = path+"/"+serialNumber;
        System.out.println("construct voice path: "+file);

        return file;
    }

    public void  writeTxt2ftp(String path, String fileName,String content){

        InputStream is = null;
        try {

            is = new ByteArrayInputStream(content.getBytes());
            ftp.changeWorkingDirectory(path);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
           boolean flag =  ftp.storeFile(new String(fileName.getBytes("utf-8"),
                    "utf-8"), is);

            if (flag) {
                System.out.println("upload File succeed");

            } else {
                System.out.println("upload File false");
            }
                is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
