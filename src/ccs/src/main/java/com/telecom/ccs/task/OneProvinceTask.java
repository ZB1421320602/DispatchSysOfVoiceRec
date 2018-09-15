package com.telecom.ccs.task;

import com.alibaba.fastjson.JSON;
import com.telecom.ccs.utils.file.TaskDto;
import com.telecom.ccs.utils.redis.RedisOps;
import com.telecom.ccs.utils.task.ProvinceScanPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OneProvinceTask  implements  Runnable{

    private Logger logger  = LoggerFactory.getLogger(OneProvinceTask.class);


    private String province;
    public  OneProvinceTask(String province){
        this.province = province;
    }
    @Override
    public void run() {

        logger.info("task province:"+province+" is started ...");

        ProvinceScanPath provinceScanPath = new ProvinceScanPath(province);
        String scanPath =  provinceScanPath.getCurrentScanPath("/task");
        ArrayList<TaskDto> list = new ScanVoicetask().run("192.168.5.177",21,"ftpuser","root",scanPath);
        for(TaskDto taskDto: list){
            logger.info("创建任务到缓存队列。。。"+JSON.toJSONString(taskDto));

            logger.info("task: "+taskDto.getVoicePath());
            RedisOps.leftPush(province+"-queue", JSON.toJSONString(taskDto));

            logger.info("end: "+taskDto.getVoicePath());

        }


    }
}
