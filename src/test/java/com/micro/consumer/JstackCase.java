package com.micro.consumer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JstackCase {

	public static Executor executor = Executors.newFixedThreadPool(5);
	public static Object lock = new Object();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TaskRun task1 = new TaskRun();
		TaskRun task2 = new TaskRun();
		executor.execute(task1);
		executor.execute(task2);
	}
	
	static class TaskRun implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (lock) {
				calculate();
				/*try {
	                lock.wait();
	                //TimeUnit.SECONDS.sleep(100000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }*/
			}
		}

		private void calculate() {
			// TODO Auto-generated method stub
			int i=0;
			while(true) {
				i++;
			}
		}
		
	}
}
