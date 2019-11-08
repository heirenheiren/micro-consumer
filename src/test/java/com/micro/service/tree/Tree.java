package com.micro.service.tree;

import java.util.List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
 
public class Tree<E> implements Treeable<E>{
	private int size = 0;//整棵树的节点数
	private TreeNode<E> root;//根节点
	
    //构造方法
	public Tree(TreeNode<E> root) {
		this.root = root;
		size++;
	}
 
	@Override
	public int getSize() {
		return size;
	}
 
	@Override
	public TreeNode<E> getRoot() {
		return root;
	}
 
	@Override
	public TreeNode<E> getParent(TreeNode<E> e) {
		return e.parent;
	}
 
	@Override
	public TreeNode<E> getFirstChild(TreeNode<E> e) {
		return e.children.get(0);
	}
 
	@Override
	public TreeNode<E> getNextSibling(TreeNode<E> e) {
		List<TreeNode<E>> children = e.parent.children;
		int i = children.indexOf(e);
		try {
			return children.get(i+1);
		}catch(Exception e2) {
			return null;
		}
	}
 
	@Override
	public int getHeight(TreeNode<E> e) {
		if(e.children == null) {
			return 0;
		}else {
			int h = 0;
			for(int i=0;i<e.children.size();i++) {//此处使用dfs进行尝试取最大高度
				h = max(h,getHeight(e.children.get(i)));
			}
			return h+1;//加上根节点
		}
	}
 
	@Override 
	public void insertChild(TreeNode<E> e, TreeNode<E> child) {
		if(e.children == null) {
			e.children = new ArrayList<>();
		}
		e.children.add(child);
		child.parent = e;
		size++;
	}
 
	@Override
	public void deleteChild(TreeNode<E> e, int i) {
		if(i<0 || i>=e.children.size()) {
			throw new IndexOutOfBoundsException("下标不合法/下标越界");
		}{
			e.children.remove(i);
			size--;
		}
	}
 
	@Override
	public List<TreeNode<E>> preOrder(TreeNode<E> e) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public List<TreeNode<E>> postOrder(TreeNode<E> e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 换行式层序遍历
	 * @param e
	 * @return
	 */
	public List<List<TreeNode<E>>> levelOrder2(TreeNode<E> e){
		List<List<TreeNode<E>>> ans = new ArrayList<>();
		Queue<TreeNode<E>> q = new LinkedList<>();
		q.add(e);
		TreeNode<E> last = e;
		TreeNode<E> nLast = null;
		List<TreeNode<E>> l = new ArrayList<>();
		ans.add(l);
		
		while(!q.isEmpty()) {
			TreeNode<E> peek = q.peek();
			//把即将弹出节点的子节点加入队列
			if(peek.children != null) {
				for (TreeNode<E> n : peek.children) {
					q.add(n);
					nLast = n;
				}
			}
			
			l.add(q.poll());//弹出队头元素
			
			if(peek == last && !q.isEmpty()) {
				l = new ArrayList<>();
				ans.add(l);
				last = nLast;
			}
		}
		
		return ans;
	
	}
	
	@Override
	public List<TreeNode<E>> levelOrder(TreeNode<E> e) {
		List<TreeNode<E>> ans = new ArrayList<TreeNode<E>>();
		Queue<TreeNode<E>> que = new LinkedList<>();
		que.add(e);
		while(!que.isEmpty()) {//bfs核心,使用队列,弹一个加多个
			ans.add(que.peek());
			if(que.peek().children != null)que.addAll(que.element().children);
			que.poll();
		}
		return ans;
		
	}
	
	private final int max(int a,int b) {
		return a>b?a:b;
	}
}