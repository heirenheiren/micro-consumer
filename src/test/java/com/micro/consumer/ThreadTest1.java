package com.micro.consumer;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest1 {

    private final List<Integer> list =new ArrayList<>();

    public static void main(String[] args) {
    	ThreadTest1 demo =new ThreadTest1();
        new Thread(()->{
            for (int i=0;i<10;i++){
                synchronized (demo.list){
                    if(demo.list.size()%2==1){
                        try {
                            demo.list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    demo.list.add(i);
                    System.out.print(Thread.currentThread().getName());
                    System.out.println(demo.list);
                    demo.list.notify();
                }
            }

        }).start();

        new Thread(()->{
            for (int i=0;i<10;i++){
                synchronized (demo.list){
                    if(demo.list.size()%2==0){
                        try {
                            demo.list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    demo.list.add(i);
                    System.out.print(Thread.currentThread().getName());
                    System.out.println(demo.list);
                    demo.list.notify();
                }
            }
        }).start();
    }
}
