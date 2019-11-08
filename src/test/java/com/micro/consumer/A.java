package com.micro.consumer;

public class A 
{
	static {
		System.out.println("static");
	}
	public A() {
		System.out.println("A()");
	}
	public A(int i) {
		System.out.println("a con");
	}
	
	public void test() 
	{
		System.out.println("A test");
	}
	
	int test(int a)
	{
		return a;
		
	}

	public static void main(String[] args) {
		A aa = new A(1);
		String a = "12";
		String b = "12";
		String c = "1"+"2";
		String d = new String("12");
		String e = new String("12");
		char[] f = {'1','2'};
		System.out.println(a==c);
		System.out.println(a==d);
		System.out.println(e==d);
		System.out.println(a.equals(f));
		System.out.println(e.equals(d));
	}
}
