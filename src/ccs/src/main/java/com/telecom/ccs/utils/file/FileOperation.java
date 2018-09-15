package com.telecom.ccs.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
  
public class FileOperation {  
   
 /** 
  * 创建文件 
  * @param fileName 
  * @return 
  */  
 public static boolean createFile(File fileName)throws Exception{  
	  boolean flag=false;  
	  try{  
	   if(!fileName.exists()){  
	    fileName.createNewFile();  
	    flag=true;  
	   }  
	  }catch(Exception e){  
	   e.printStackTrace();  
	  }  
	  return true;  
	 }   
   
 /** 
  * 读TXT文件内容 
  * @param fileName 
  * @return 
  */  
 public static ArrayList readTxtFile2List(File fileName)throws Exception{  
	  String result="";  
	  ArrayList<String> list =new ArrayList<String>();
	  FileReader fileReader=null;  
	  BufferedReader bufferedReader=null;  
	  try{  
	   fileReader=new FileReader(fileName);  
	   bufferedReader=new BufferedReader(fileReader);  
	   try{  
	    String read=null;  
	    while((read=bufferedReader.readLine())!=null){  
	        list.add(read);
	    	result=result+read+"\r\n";  
	    }  
	   }catch(Exception e){  
	    e.printStackTrace();  
	   }  
	  }catch(Exception e){  
	   e.printStackTrace();  
	  }finally{  
	   if(bufferedReader!=null){  
	    bufferedReader.close();  
	   }  
	   if(fileReader!=null){  
	    fileReader.close();  
	   }  
	  }  
	  System.out.println("读取出来的文件内容是："+"\r\n"+result);  
	  //return result;  
	  return list;
	 }  
	   
   
 public static boolean writeTxtFile(String content,File  fileName)throws Exception{  
  
	  RandomAccessFile mm=null; 
	  boolean flag=false;  
	  FileOutputStream o=null;  
	  try {  
	   o = new FileOutputStream(fileName);  
	      o.write(content.getBytes("GBK"));  
	      o.close();  
	//   mm=new RandomAccessFile(fileName,"rw");  
	//   mm.writeBytes(content);  
	   flag=true;  
	  } catch (Exception e) {  
	   // TODO: handle exception  
	   e.printStackTrace();  
	  }finally{  
	   if(mm!=null){  
	    mm.close();  
	   }  
	  }  
	  return flag;  
	 }  
 public static void appendTXT2File(String file, String conent){
	
		 BufferedWriter out = null;
		 try {
		 out = new BufferedWriter(new OutputStreamWriter(
		 new FileOutputStream(new File(file), true)));
		 //out.write(conent+"\r\n");
		 out.write(conent+"\r\n");
		 } catch (Exception e) {
		 e.printStackTrace();
		 } finally {
		 try {
		 out.close();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 }
		 
 }
  
  
  
public static void contentToTxt(String filePath, String content) {  
        String str = new String(); //原有txt内容  
        String s1 = new String();//内容更新  
        try {  
            File f = new File(filePath);  
            if (f.exists()) {  
                System.out.print("文件存在");  
            } else {  
                System.out.print("文件不存在");  
                f.createNewFile();// 不存在则创建  
            }  
            BufferedReader input = new BufferedReader(new FileReader(f));  
  
            while ((str = input.readLine()) != null) {  
                s1 += str + "\n";  
            }  
            System.out.println(s1);  
            input.close();  
            s1 += content;  
  
            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
            output.write(s1);  
            output.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        }  
    }  

    public static Map list2Map(ArrayList<String> list){
    	Map<String,String> map = new HashMap<String,String>();
    	for(String msg : list){
			map.put(msg.substring(0, msg.indexOf('|')).trim(), msg.substring(msg.indexOf('|')+1,msg.length()).trim());
		}
    	
    	return map;
    }
	public static void main(String[] args) {
		try {
			//new FileOperation().createFile(new File("D:/20170122.txt"));
			ArrayList<String> list = new ArrayList<String>();
			list = new FileOperation().readTxtFile2List(new File("D:/Q_[YYYYMMDD]_[X].txt"));
			
			/*for(String msg : list){
				System.out.println(msg);
			}*/
			
			HashMap<String,String> map = (HashMap<String, String>) list2Map(list);
			for (Map.Entry<String, String> entry : map.entrySet()) {  
				  
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			  
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
}  

