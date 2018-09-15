package com.telecom.ccs.utils.file.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 普通线程池
 */

public class ThreadExecutorUtil {

    private static ExecutorService executorService = Executors.newCachedThreadPool();


    public static  void executeTask(Runnable task){
        executorService.execute(task);
    }
}
