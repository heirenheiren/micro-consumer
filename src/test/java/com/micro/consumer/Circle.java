package com.micro.consumer;

import java.util.ArrayList;
import java.util.List;

public class Circle 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int num = 88;
		int mod = 3;
		int flag = num%mod;
		
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= num; i++) 
		{
			list.add(i);
		}
		
		handle(list,flag,mod);
		
		//handle1(list,flag);
	}

	private static void handle(List<Integer> list, int flag, int mod) 
	{
		for (Integer integer : list) 
		{
			System.out.print(integer+" ");
		}
		System.out.println();
		int lsize = list.size();
		if(lsize<mod)
		{
//			if(mod-lsize>lsize)
//			{
//				mod = lsize;
//			}
//			list.remove(mod-lsize-1);
//			handle(list,flag,mod);
			System.out.println(list.get(1));
			return;
		}
		
		List<Integer> olist = new ArrayList<Integer>();
		for (int i = flag; i > 0; i--) 
		{
			olist.add(list.get(lsize-i));
		}
		
		for (int i = 1; i < lsize+1-flag; i++) 
		{
			if(i%mod != 0)
			{
				olist.add(list.get(i-1));
			}
		}
		
		flag = olist.size()%mod;
		handle(olist,flag,mod);
	}

	private static void handle1(List<Integer> list, int flag) 
	{
		if(list.size()==2)
		{
			System.out.println(list.get(1)-1);
			return;
		}
		
		List<Integer> olist = new ArrayList<Integer>();
		if(flag==0)
		{
			for (int i = 1; i < list.size()+1; i++) 
			{
				if(i%3 != 0)
				{
					olist.add(list.get(i-1));
				}
			}
		}
		else if(flag==1)
		{
			olist.add(list.get(list.size()-1));
			for (int i = 1; i < list.size(); i++) 
			{
				if(i%3 != 0)
				{
					olist.add(list.get(i-1));
				}
			}
		}
		else if(flag==2)
		{
			olist.add(list.get(list.size()-2));
			olist.add(list.get(list.size()-1));
			for (int i = 1; i < list.size()-1; i++) 
			{
				if(i%3 != 0)
				{
					olist.add(list.get(i-1));
				}
			}
		}
		
		flag = olist.size()%3;
		for (Integer integer : olist) 
		{
			System.out.print(integer);
		}
		System.out.println();
		handle1(olist,flag);
	}

}
