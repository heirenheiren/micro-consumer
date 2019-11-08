package com.micro.consumer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class HelloWorld {

	private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(111);
		Map<Float,Float> hmap = new HashMap<Float,Float>();
		hmap.hashCode();
		Iterator<?> iter = hmap.entrySet().iterator();
		while(iter.hasNext()) 
		{
			Map.Entry entry = (Map.Entry)iter.next();
			Float key = (Float) entry.getKey();
			Float val = (Float) entry.getValue();
		}
		
		hmap.put(1.02f, 1.01f);hmap.put(1.01f, 1.01f);
		System.out.println(hmap.size());
		Map<String,String> tmap = new Hashtable<String,String>();
		Map<Integer,Integer> cmap = new ConcurrentHashMap<Integer,Integer>();
		
        new Thread("Thread1"){  
            @Override  
            public void run() {  
                map.put(3, 33);  
            }  
        };  
          
        new Thread("Thread2"){  
            @Override  
            public void run() {  
                map.put(4, 44);  
            }  
        };  
          
        new Thread("Thread3"){  
            @Override  
            public void run() {  
                map.put(7, 77);  
            }  
        };  
        System.out.println(map);  
        
        String a = "hello2"; 
        final String b = "hello";
        String d = "hello";
        String c = b + 2; 
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
        
        try {
        		System.out.println(111);
        		//System.exit(0);
        }finally {
        		System.out.println(222);
        }
        //Collections.sort(null);
        
        Queue<String> queue = new ArrayBlockingQueue<String>(5);
        for (int i = d.length()-1; i >=0; i--) 
        {
        		queue.add(String.valueOf(d.charAt(i)));
        }
         while(!queue.isEmpty())
         {
        	 	d += queue.poll();
         }
         System.out.println(d);
         
    }  

}
