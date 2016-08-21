package com.siiruo.util;

import javax.swing.JTree;
import javax.swing.tree.TreePath;
import com.siiruo.model.SiiruoTreeNode;

public class TreeAndTreeNodeUtil {
	public static JTree historyTree;
	public static JTree collectionTree;
//	public static STreeNode historyRoot;
//	public static STreeNode collectionRoot;
	public static SiiruoTreeNode historyRoot;
	public static SiiruoTreeNode collectionRoot;
	public synchronized static JTree getHistoryTree() {
		if(historyTree==null){
			historyTree=new JTree(getHistoryRoot());
		}
		return historyTree;
	}
	public synchronized static JTree getCollectionTree() {
		if(collectionTree==null){
			collectionTree=new JTree(getCollectionRoot() );
		}
		return collectionTree;
	}
	public static SiiruoTreeNode getHistoryRoot() {
		if(historyRoot==null){
			//historyRoot=new STreeNode(null, null);
			historyRoot=new SiiruoTreeNode(null,null,null);
		}
		return historyRoot;
	}
	public static SiiruoTreeNode getCollectionRoot() {
		if(collectionRoot==null){
			//collectionRoot=new STreeNode(null,null);
			collectionRoot=new SiiruoTreeNode(null,null,null);
		}
		return collectionRoot;
	}
	public static String getSelectItem(JTree tree){
		TreePath path = tree.getSelectionPath();// 获取选中节点路径
		SiiruoTreeNode node = null;
		try {
			node = (SiiruoTreeNode) path.getLastPathComponent();
		} catch (java.lang.NullPointerException e1) {
			System.out.println("JTree node is null");
			return null;
		}
		
		return node.getNameLabel().getText();
	}
}
