package com.telecom.ccs.task;

import com.telecom.ccs.utils.file.thread.TheadExecutorScheduleUtil;
import com.telecom.ccs.utils.file.thread.ThreadExecutorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CcsTaskDispatch  implements Runnable{

    protected Logger logger = LoggerFactory.getLogger(CcsTaskDispatch.class);

    @Override
    public void run() {

        logger.info("各省份质检调度任务开始执行 ...");


        //山东
        if(true){
            TheadExecutorScheduleUtil.executeScheduleTask(new OneProvinceTask("sd"));

        }


        //河北
        if(false){
            TheadExecutorScheduleUtil.executeScheduleTask(new OneProvinceTask("河北"));
        }


        //天津
        if(false){
            TheadExecutorScheduleUtil.executeScheduleTask(new OneProvinceTask("天津"));
        }



        //北京
        if(false){
            TheadExecutorScheduleUtil.executeScheduleTask(new OneProvinceTask("北京"));
        }






    }
}
