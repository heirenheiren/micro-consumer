package com.micro.consumer;

import java.util.HashMap;
import java.util.Map;

public class FinallyTest1 {

	public static void main(String[] args) {

		// System.out.println(test2());
		System.out.println(test4().get("KEY").toString());
	}

	//1 finally语句在return语句执行之后return返回之前执行的
	public static int test1() {
		int b = 20;

		try {
			System.out.println("try block");

			return b += 80;
		} catch (Exception e) {

			System.out.println("catch block");
		} finally {

			System.out.println("finally block");

			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}
		}

		return b;
	}

	public static String test11() {
		try {
			System.out.println("try block");

			return test12();
		} finally {
			System.out.println("finally block");
		}
	}

	public static String test12() {
		System.out.println("return statement");

		return "after return";
	}

	//2 finally块中的return语句会覆盖try块中的return返回
	public static int test2() {
		int b = 20;

		try {
			System.out.println("try block");

			return b += 80;
		} catch (Exception e) {

			System.out.println("catch block");
		} finally {

			System.out.println("finally block");

			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}

			return 200;
		}

		// return b;
	}

	//3 如果finally语句中没有return语句覆盖返回值，那么原来的返回值可能因为finally里的修改而改变也可能不变
	public static int test3() {
		int b = 20;

		try {
			System.out.println("try block");

			return b += 80;
		} catch (Exception e) {

			System.out.println("catch block");
		} finally {

			System.out.println("finally block");

			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}

			b = 150;
		}

		return 2000;
	}

	public static Map<String, String> test4() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("KEY", "INIT");

		try {
			map.put("KEY", "TRY");
			return map;
		} catch (Exception e) {
			map.put("KEY", "CATCH");
		} finally {
			map.put("KEY", "FINALLY");
			map = null;
		}

		return map;
	}

	//4 try块里的return语句在异常的情况下不会被执行，这样具体返回哪个看情况
	public static int test5() {
		int b = 20;

		try {
			System.out.println("try block");

			b = b / 0;

			return b += 80;
		} catch (Exception e) {

			b += 15;
			System.out.println("catch block");
		} finally {

			System.out.println("finally block");

			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}

			b += 50;
		}

		return b;
	}

	//5 当发生异常后，catch中的return执行情况与未发生异常时try中return的执行情况完全一样
	public static int test6() {
		int b = 20;

		try {
			System.out.println("try block");

			b = b / 0;

			return b += 80;
		} catch (Exception e) {

			System.out.println("catch block");
			return b += 15;
		} finally {

			System.out.println("finally block");

			if (b > 25) {
				System.out.println("b>25, b = " + b);
			}

			b += 50;
		}

		// return b;
	}
}
