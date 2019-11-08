package com.micro.consumer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tree {
	
	Node node = null;
	
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(0,1);
		tree.insert(1,2);
		tree.insert(1,3);
		tree.insert(3,4);
		tree.insert(3,5);
		
		tree.print(tree.node);
		System.out.println();
		
		tree.delete(3);
		tree.print(tree.node);
		System.out.println();
		
		tree.delete(2);
		tree.print(tree.node);
		System.out.println();
		
		tree.insert(1, 2);
		tree.print(tree.node);
		System.out.println();
		
		tree.insert(4, 6);
		tree.print(tree.node);
	}
	
	private void delete(int id) {
		if(node==null) {
			return;
		}
		
		remove(null,node,id);
	}

	private void remove(Node pNode,Node node, int id) {
		if(node.id==id && pNode!=null) {
			if(node.childrens!=null && node.childrens.size()>0) {
				for(Node childNode : node.childrens) {
					childNode.pid=node.pid;
					pNode.childrens.add(childNode);
				}
				
				for(Node pchildren:pNode.childrens) {
					if(pchildren.id==id) {
						pNode.childrens.remove(pchildren);
						break;
					}
				}
			}else {
				pNode.childrens.remove(node);
			}
		}else {
			for(Node childNode : node.childrens) {
				remove(node,childNode,id);
			}
		}
	}

	public synchronized void insert(int pid,int id) {
		if(node==null) {
			node = new Node();
			addFirst(node,pid,id);
		}else {
			addNode(node,pid,id);
		}
	}
	
	private void addNode(Node node, int pid, int id) {
		if(node.id==pid) {
			Node newNode = new Node();
			newNode.id=id;
			newNode.pid=pid;
			node.childrens.add(newNode);
		}else {
			for(Node childNode : node.childrens) {
				addNode(childNode,pid,id);
			}
		}
	}

	private void addFirst(Node node,int pid, int id) {
		node.id=id;
		node.pid=pid;
	}
	
	public void print(Node node) {
		System.out.println(node.id+":"+node.pid);
		if(node.childrens != null && node.childrens.size() > 0) {
			for(Node childNode : node.childrens) {
				print(childNode);
			}
		}
	}

	static class Node{
		public int id;
		public int pid;
		public List<Node> childrens = new CopyOnWriteArrayList<>();
	}
}
