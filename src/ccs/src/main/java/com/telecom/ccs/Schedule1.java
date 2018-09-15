package com.telecom.ccs;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule1 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(32);



        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxx");
            }
        },5,60,TimeUnit.SECONDS);

    }
}
