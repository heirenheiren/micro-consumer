package com.micro.consumer;

public class IntegerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer a = new Integer(9);
		Integer b = new Integer(9);
		Long c = new Long(9);
		System.out.println(a==b);
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		//System.out.println(a==c);
	}

}
