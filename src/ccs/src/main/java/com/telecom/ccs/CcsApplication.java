package com.telecom.ccs;

import com.telecom.ccs.task.CcsTaskDispatch;
import com.telecom.ccs.task.InitElasticsearch;
import com.telecom.ccs.utils.file.thread.ThreadExecutorUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CcsApplication {


	public static void main(String[] args) {


		System.out.println("CCS Springboot service is started ...");
		SpringApplication.run(CcsApplication.class, args);
		ThreadExecutorUtil.executeTask(new CcsTaskDispatch());
		ThreadExecutorUtil.executeTask(new InitElasticsearch("shandong","shandong","task"));


	}
}
