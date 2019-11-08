package com.micro.consumer;

public class B extends A
{
	static {
		System.out.println("B static");
	}
	
	public B(int i) {
		System.out.println("B con");
	}
	public void test()
	{
		System.out.println("B test");
	}
	public static void main(String[] args) {
		A b = new B(2);
		b.test();
		
		StringBuilder s1 = new StringBuilder("A");
		StringBuilder s2 = new StringBuilder("B");
		conn(s1,s2);
		System.out.println(s1+","+s2);
	}
	private static void conn(StringBuilder s1, StringBuilder s2) {
		// TODO Auto-generated method stub
		s1.append(s2);
		s2=s1;
		System.out.println(s2);
	}
	
	
}
