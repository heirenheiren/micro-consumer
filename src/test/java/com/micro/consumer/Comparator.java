package com.micro.consumer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Comparator 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		List<String> names = Arrays.asList("peter", "anna", "bike", "xenia");
		for (int i = 0; i < names.size(); i++) 
		{
			System.out.println(names.get(i));
		}
		//第一种方法
		Collections.sort(names, (a,b)->a.compareTo(b));
		//第二种方法
		Collections.sort(names, (String a, String b) -> b.compareTo(a));
		//第三种方法
		Collections.sort(names, (String a, String b) -> 
		{
		    return b.compareTo(a);
		});
		for (int i = 0; i < names.size(); i++) 
		{
			System.out.println(names.get(i));
		}
	}

}
