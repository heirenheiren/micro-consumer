package com.micro.consumer;

public class Factorial {
	static int g=0;
	public static void main(String[] args) {
		fac(1024);
		System.out.println(g);
	}
	
	private static int fac(int i) {
		if(i%10==0&&i%100!=0) {
			//System.out.println(i);
			g++;
		}
		if(i%100==0) {
			//System.out.println(i);
			g=g+2;
		}
		if(i==1) {
			return 1;
		}
		return i*fac(i-1);
	}
}
