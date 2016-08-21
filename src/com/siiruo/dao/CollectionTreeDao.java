package com.siiruo.dao;

import javax.swing.tree.TreeNode;

import com.siiruo.beans.Video;

public interface CollectionTreeDao {
	public void create(String collectionName);
	public void remove(String collectionName);
	public void remove(TreeNode node);
	public void clear(TreeNode node);
	public void add(TreeNode parentNode,Video video);
	public void add(String parentName,Video video);
	

}
