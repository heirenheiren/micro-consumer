package com.micro.consumer.order.service.manager;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class OrderServiceManagger {
	public static int checkTaskDone(List<Future<Integer>> futureList) {
        //判断异步调用的方法是否全都执行完了
        while(true) {
            int doneSize = 0;
            for ( Future<Integer> future  : futureList) {
                //该异步方法是否执行完成
                if(future.isDone()) {
                    doneSize++;
                }
            }
            //如果异步方法全部执行完，跳出循环
            if(doneSize == futureList.size()) {
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//每隔2秒判断一次
        }
        
        int importCount = 0;
        for ( Future<Integer> future  : futureList) {
            try {
                importCount += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return importCount;
    }
}
