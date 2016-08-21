package com.siiruo.dao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import org.apache.log4j.Logger;

import com.siiruo.beans.Video;
import com.siiruo.model.SiiruoTreeNode;
import com.siiruo.util.HandlerUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.TreeAndTreeNodeUtil;

public class HistoryTreeDaoImpl implements HistoryTreeDao {
	
	private JTree historyTree;
	private SiiruoTreeNode historyRoot;
	private Logger logger=LoggerUtil.getLogger(HistoryTreeDaoImpl.class.getName());
	

	/**
	 * 在播放记录树的第一个二级父节点(今天的播放记录)下添加一条新的视频信息
	 */
	@Override
	public void add(Video son) {
		if(historyTree==null) historyTree=TreeAndTreeNodeUtil.getHistoryTree();
		if(historyRoot==null) historyRoot=TreeAndTreeNodeUtil.getHistoryRoot();
		add(historyRoot.getFirstChild(),son);	
	}
	/**
	 *  在播放记录树的parent二级父节点下添加一条新的视频信息
	 */
	@Override
	public void add(TreeNode parent, Video son) {
		if(parent==null||son==null){
			logger.error("fail to add one video item to historyTree because of the TreeNode parent or Video son is null...");
			return;
		}
		if(historyTree==null) historyTree=TreeAndTreeNodeUtil.getHistoryTree();
		DefaultTreeModel model = (DefaultTreeModel) historyTree.getModel();
		if(!isExistVideo(parent,son.getName())){
			model.insertNodeInto(new SiiruoTreeNode(ImageIconUtil.VIDEO,parent.getChildCount()+1+". ",
							son.getName(),HandlerUtil.getStringTime(son.getTime())+"/"+HandlerUtil.getStringSize(son.getSize())),
							(SiiruoTreeNode)parent, parent.getChildCount());						
			historyTree.repaint();
		}else{
			logger.error("fail to add one video item because of the video has been exist...");
		}
	}
/**
 * 将该节点从其父节点中删除
 */
	@Override
	public void remove(TreeNode son) {
		if(son==null){
			logger.error("fail to remove subnode from historyTree because of subnode is null...");
			return;
		}
		if(historyTree==null) historyTree=TreeAndTreeNodeUtil.getHistoryTree();
		DefaultTreeModel model = (DefaultTreeModel) historyTree.getModel();			
		model.removeNodeFromParent((SiiruoTreeNode)son);
		historyTree.repaint();
	}
	/**
	 * 清空播放记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear(TreeNode node) {
		if(node==null)return;
		Enumeration<TreeNode> sons=node.children();
		if(historyTree==null) historyTree=TreeAndTreeNodeUtil.getHistoryTree();
		DefaultTreeModel model = (DefaultTreeModel) historyTree.getModel();
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
		historyTree.repaint();
	}
	/**
	 * 判断节点node下是否包含值为name的子节点
	 * @param node
	 * @param name
	 * @return
	 */
	public boolean isExistVideo(TreeNode node,String name){
		System.out.println("****************************isExistVideo");
		SiiruoTreeNode snode=(SiiruoTreeNode) node;
		SiiruoTreeNode temp;
		Enumeration<SiiruoTreeNode> enumeration=node.children();
		while(enumeration.hasMoreElements()){
			temp=enumeration.nextElement();
			if(temp.getNameLabel().getText().equals(name)) return true;
		}		
		return false;
	}
	

}
