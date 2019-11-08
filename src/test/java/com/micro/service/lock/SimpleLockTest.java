package com.micro.service.lock;

public class SimpleLockTest {
	public static void main(String[] args) throws InterruptedException {
		final SimpleLock lock = new SimpleLock();
		lock.lock();

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					System.out.println(Thread.currentThread().getId() + " acquired the lock!");
					lock.unlock();
				}
			}).start();
			// 简单的让线程按照for循环的顺序阻塞在lock上
			Thread.sleep(1000);
		}

		System.out.println("main thread unlock!");
		lock.unlock();
	}
}
