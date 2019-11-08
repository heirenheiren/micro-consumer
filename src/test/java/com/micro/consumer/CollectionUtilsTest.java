package com.micro.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class CollectionUtilsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> lists = new ArrayList<>();
		lists.add(4);
		lists.add(null);
		lists.add(8);
		for (int i = 0; i<lists.size() && !CollectionUtils.isEmpty(lists);i++) {
			System.out.println(lists.get(i));
		}
	}

}
