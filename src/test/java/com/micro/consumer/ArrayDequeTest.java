package com.micro.consumer;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Object> a = new ArrayDeque<Object>(10);
		Deque<Object> b = new ArrayDeque<Object>(10);
		
		Class<ArrayDeque> c = ArrayDeque.class;
		
		System.out.println(a==b);
		System.out.println(a.getClass()==b.getClass());
		System.out.println(a.getClass()==c);
		Long f=0L;
		
		try {
			Class d = Class.forName("java.util.ArrayDeque");
			System.out.println(a.getClass()==d);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
