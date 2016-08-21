package com.siiruo.dao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.log4j.Logger;

import com.siiruo.beans.Video;
import com.siiruo.model.SiiruoTreeNode;
import com.siiruo.util.HandlerUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.TreeAndTreeNodeUtil;

public class CollectionTreeDaoImpl implements CollectionTreeDao {
	private JTree collectionTree;
	private SiiruoTreeNode collectionRoot;
	private Logger logger=LoggerUtil.getLogger(CollectionTreeDaoImpl.class.getName());
	/**
	 * 在收藏夹树结构中信添加一个节点(新的收藏夹)
	 */
	@Override
	public void create(String collectionName) {
		if(collectionName==null||collectionName.isEmpty()){
			logger.error("fail to create collection because of the given collection name is null or empty...");
			return;
		}
		if(isCollectionExist(collectionName)){
			logger.error("fail to create collection because of the given collection name is exist...");
			return;
		}
		if(collectionTree==null) collectionTree=TreeAndTreeNodeUtil.getCollectionTree();
		if(collectionRoot==null) collectionRoot=TreeAndTreeNodeUtil.getCollectionRoot();
		DefaultTreeModel model = (DefaultTreeModel) collectionTree.getModel();
		model.insertNodeInto(new SiiruoTreeNode(null,null,collectionName), collectionRoot, collectionRoot.getChildCount());
		collectionTree.repaint();
	}

	@Override
	public void remove(String collectionName) {
		

	}

	@Override
	public void add(TreeNode parent, Video video) {
		if(parent==null||video==null){
			logger.error("fail to add one video item because of parent node or video is null");
			return ;
		}
		if(collectionTree==null) collectionTree=TreeAndTreeNodeUtil.getCollectionTree();
		DefaultTreeModel model = (DefaultTreeModel) collectionTree.getModel();
		if(!isExit(parent,video.getName())){
			model.insertNodeInto(new SiiruoTreeNode(ImageIconUtil.VIDEO,parent.getChildCount()+1+". ",
					video.getName(),HandlerUtil.getStringTime(video.getTime())+"/"+HandlerUtil.getStringSize(video.getSize())),
							(SiiruoTreeNode)parent, parent.getChildCount());						
			collectionTree.repaint();
		}else{
			logger.error("fail to add one video item because of the video has been exist...");
		}

	}

	@Override
	public void add(String parentName, Video video) {
		TreeNode parentNode=getCollectionTreeNodeByName(parentName);
		if(parentNode!=null) add(parentNode,video);
	}

	@Override
	public void remove(TreeNode node) {
		if(node==null){
			logger.error("fail to remove node because of it is null..."); 
			return;
		}
		if(collectionTree==null) collectionTree=TreeAndTreeNodeUtil.getCollectionTree();
		DefaultTreeModel model = (DefaultTreeModel) collectionTree.getModel();
		model.removeNodeFromParent((SiiruoTreeNode) node);
		collectionTree.repaint();
	}
	@Override
	public void clear(TreeNode node) {
		if(node==null)return;
		Enumeration<TreeNode> sons=node.children();
		if(collectionTree==null) collectionTree=TreeAndTreeNodeUtil.getCollectionTree();
		DefaultTreeModel model = (DefaultTreeModel) collectionTree.getModel();
		List<SiiruoTreeNode> list=new ArrayList<SiiruoTreeNode>();
		while(sons.hasMoreElements()){
			SiiruoTreeNode son=(SiiruoTreeNode) sons.nextElement();
			//model.removeNodeFromParent(son);
			list.add(son);
		}
		Iterator<SiiruoTreeNode> it=list.iterator();
		while(it.hasNext()){
			model.removeNodeFromParent(it.next());
		}
		collectionTree.repaint();
	}

	/**
	 * 判断是否已经存在给定名字的收藏夹
	 * @param name
	 * @return
	 */
	private boolean isCollectionExist(String name){
		if(collectionRoot==null) collectionRoot=TreeAndTreeNodeUtil.getCollectionRoot();
		return isExit(collectionRoot,name);
	}
	/**
	 * 判断是否已经存在给定名字的子节点
	 * @param parent
	 * @param name
	 * @return
	 */
	private boolean isExit(TreeNode parent,String name){
		Enumeration<TreeNode> sons=parent.children();
		while(sons.hasMoreElements()){
			SiiruoTreeNode node=(SiiruoTreeNode) sons.nextElement();
			if(node.getNameLabel().getText().equals(name)) return true;
		}
		return false;
	}
	/**
	 * 根据收藏夹名称返回对应的收藏夹树节点
	 * @param name
	 * @return
	 */
	private TreeNode getCollectionTreeNodeByName(String name){
		if(collectionRoot==null) collectionRoot=TreeAndTreeNodeUtil.getCollectionRoot();
		Enumeration<TreeNode> sons=collectionRoot.children();
		while(sons.hasMoreElements()){
			SiiruoTreeNode node=(SiiruoTreeNode) sons.nextElement();
			if(node.getNameLabel().getText().equals(name)) return node;
		}
		return null;
	}
}
