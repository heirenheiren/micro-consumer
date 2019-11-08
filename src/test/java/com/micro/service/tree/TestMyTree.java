package com.micro.service.tree;

import java.util.List;

public class TestMyTree {
	static Tree<String> t1;
	public static void main(String[] args) {
		t1 = new Tree<String>(new TreeNode<String>("a", null));
		TreeNode<String> root = t1.getRoot();
		//1层
		TreeNode<String> b = new TreeNode<String>("b");
		TreeNode<String> c = new TreeNode<String>("c");
		TreeNode<String> d = new TreeNode<String>("d");
		
		//其他层
		TreeNode<String> e = new TreeNode<String>("e");
		TreeNode<String> f = new TreeNode<String>("f");
		TreeNode<String> g = new TreeNode<String>("g");
		TreeNode<String> h = new TreeNode<String>("h");
 
		//将b、c、d分别挂到主节点上
		t1.insertChild(root, b);
		t1.insertChild(root, c);
		t1.insertChild(root, d);
		
		t1.insertChild(b, e);
		t1.insertChild(c, f);
		t1.insertChild(d, g);
		
		TreeNode<String> i = new TreeNode<String>("i");
		TreeNode<String> j = new TreeNode<String>("j");
		
		//将i和j挂到e节点上
		t1.insertChild(e, i);
		t1.insertChild(e, j);
		
		System.out.println("子节点数:"+t1.getSize());//子节点个数
		System.out.println("root的子节点数量:"+root.children.size());
		System.out.println("root的第一个子节点:"+root.children.get(0).data);
		System.out.println("根节点的高度:"+t1.getHeight(root));
		System.out.println();
		//简单打印树
		List<List<TreeNode<String>>> list = t1.levelOrder2(t1.getRoot());
		for (List<TreeNode<String>> lst : list) {
			for(TreeNode<String> n:lst) {
				System.out.print(n.data+" ");
			}
			System.out.println();
		}
	}
}
