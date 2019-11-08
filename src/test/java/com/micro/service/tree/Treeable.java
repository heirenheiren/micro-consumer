package com.micro.service.tree;

import java.util.List;

public interface Treeable<E> {
	/**
	 * 得到这棵树的节点数
	 * @return
	 */
	int getSize();
	
	/**
	 * 得到根节点
	 * @return
	 */
	TreeNode<E> getRoot();
	
	/**
	 * 得到节点e的父节点
	 * @param e
	 * @return
	 */
	TreeNode<E> getParent(TreeNode<E> e);
	
	/**
	 * 得到e的第一个子节点
	 * @param e
	 * @return
	 */
	TreeNode<E> getFirstChild(TreeNode<E> e);
	
	/**
	 * 得到e的下一个子节点
	 * @param e
	 * @return
	 */
	TreeNode<E> getNextSibling(TreeNode<E> e);
	
	/**
	 * 这棵树的高度
	 * @param e
	 * @return
	 */
	int getHeight(TreeNode<E> e);
 
	/**
	 * 向父节点e插入一个子节点
	 * @param e
	 * @param child
	 */
	void insertChild(TreeNode<E> e,TreeNode<E> child);
	
	/**
	 * 在节点e中删除第i个子节点
	 * @param e
	 * @param i
	 */
	void deleteChild(TreeNode<E> e,int i);
 
	/**
	 * 先根遍历
	 * @param e
	 * @return
	 */
	List<TreeNode<E>> preOrder(TreeNode<E> e);
	
	/**
	 * 后根遍历
	 * @param e
	 * @return
	 */
	List<TreeNode<E>> postOrder(TreeNode<E> e);
	
	/**
	 * 层次遍历
	 * @param e
	 * @return
	 */
	List<TreeNode<E>> levelOrder(TreeNode<E> e);
}