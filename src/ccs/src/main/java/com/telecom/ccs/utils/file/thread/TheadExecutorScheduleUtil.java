package com.telecom.ccs.utils.file.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时器线程池
 */

public class TheadExecutorScheduleUtil {

     private static  ScheduledExecutorService executorService = Executors.newScheduledThreadPool(32);  //线城市对应省份数


     public static void executeScheduleTask(Runnable task){
        executorService.scheduleAtFixedRate(task,2,3600,TimeUnit.SECONDS);   // 延迟周期时间参数后续进行修改
     }

}
