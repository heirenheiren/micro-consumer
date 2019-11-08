package com.micro.consumer;

public class FinalStaticTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();
        System.out.println(myClass1.i);
        System.out.println(myClass1.j);
        System.out.println(myClass1.k);
        System.out.println(myClass2.i);
        System.out.println(myClass2.j);
        System.out.println(myClass2.k);
	}

}

class MyClass {
    public final double i = Math.random();
    public static double j = Math.random();
    public static final double k = Math.random();
}
