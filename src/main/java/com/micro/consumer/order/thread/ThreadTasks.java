package com.micro.consumer.order.thread;

import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
//@EnableAsync如果没有配置TreadPoolConfig可以直接在用这个注解开启这个类的异步方法
public class ThreadTasks {
	@Async
	//@Async("TreadPoolConfig自定义的Bean名字")
	public void startThreadTasks() {
		Random random = new Random();
		log.info("{} start task",Thread.currentThread().getName());
		try {
			//todo
			Thread.sleep(random.nextInt(5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("{} end task",Thread.currentThread().getName());
	}
}
