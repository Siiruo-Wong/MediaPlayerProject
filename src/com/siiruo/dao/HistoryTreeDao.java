package com.siiruo.dao;

import javax.swing.tree.TreeNode;

import com.siiruo.beans.Video;

public interface HistoryTreeDao {
	public void add(Video son);
	public void add(TreeNode parent,Video son);
	public void remove(TreeNode son);
	public void clear(TreeNode son);
}
