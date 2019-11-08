package com.micro.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	public static void main(String[] args) {
		BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(5);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
		ExecutorService executorService = 
				new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS, blockingQueue, handler);
		
		for (int i = 0; i < 10; i++) {
			int threadSize = blockingQueue.size();
			System.out.println("线程队列大小为-->"+threadSize);
			if(blockingQueue.size()==5) {
				boolean flag = blockingQueue.offer(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("我是新线程，看看能不能搭个车加进去！");
					}
				});
				System.out.println(flag);
				//continue;
			}
			executorService.execute(new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(3000);
						System.out.println(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}));
		}
		executorService.shutdown();
	}
}
