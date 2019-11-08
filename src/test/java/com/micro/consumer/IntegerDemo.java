package com.micro.consumer;

public class IntegerDemo {
	public static void main(String[] args) {
		int i = 128;
		Integer i2 = 128;
		Integer i3 = new Integer(128);

		System.out.println("i == i2 = " + (i == i2));
		// Integer会自动拆箱为int，所以为true
		System.out.println("i == i3 = " + (i == i3));
		// true，理由同上

		Integer i4 = 127;
		// 编译时被翻译成：Integer i4 = Integer.valueOf(127);
		Integer i5 = 127;
		System.out.println("i4 == i5 = " + (i4 == i5));
		// true

		Integer i6 = 128;
		Integer i7 = 128;
		System.out.println("i6 == i7 = " + (i6 == i7));
		// false

		Integer i8 = new Integer(127);
		System.out.println("i5 == i8 = " + (i5 == i8));
		// false

		Integer i9 = new Integer(128);
		Integer i10 = new Integer(128);
		System.out.println("i9 == i10 = " + (i9 == i10));
		// false
	}
}
