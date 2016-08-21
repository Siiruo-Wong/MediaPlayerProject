package com.siiruo.scomponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.siiruo.model.SiiruoTreeNode;
import com.siiruo.util.ColorUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.views.SidePanel;

public class SiiruoTreeCellRenderer extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7360502123386308480L;
	SiiruoTreeNode node;
	public static int mouseRow = -1;
	private boolean mouseEnter = false; // 通过mouseEnter判定当前鼠标是否悬停
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		 node = (SiiruoTreeNode) value;
		/**
		 * 通过mouseRow判断鼠标是否悬停在当前行 
		 */
	     if (mouseRow == row) {
	            mouseEnter = true;
	      } else {
	            mouseEnter = false;
	     }
	        // 分组,根节点从0开始，依次往下  
	     /**
	      * 第二个树节点(分组信息)
	      */
	        if (node.getLevel() == 1) { 
	        	///是否展开时设置背景图标
	            if (expanded) {  
	                node.firstIconLabel.setIcon(ImageIconUtil.TREE_EXPANDED);
	                node.lastIconLabel.setIcon(ImageIconUtil.TREE_EXPANDED);
	            } else {    
	                node.firstIconLabel.setIcon(ImageIconUtil.TREE_UNEXPANDED);
	                node.lastIconLabel.setIcon(ImageIconUtil.TREE_UNEXPANDED);
	            }
	            ///是否进入时设置背景颜色
	            if(mouseEnter){
	            	node.getRootView().setBackground(ColorUtil.TREE_ENTER_ROOT_BACKGROUND_COLOR);
	            }else{
	            	node.getRootView().setBackground(ColorUtil.TREE_ROOT_BACKGROUND_COLOR);
	            }
	            ///是否被选择时设置前进色
	            if(selected){
	            	node.getNameLabel().setForeground(ColorUtil.TREE_SELECTED_FOREGROUND_COLOR);
	            	
	            }else{	            	
	            	node.getNameLabel().setForeground(ColorUtil.TREE_UNSELECTED_FOREGROUND_COLOR);
	            }
	            
	            return node.getRootView();
	        }
	        /**
	         * 第三个树节点(视频信息)
	         */
	        if (node.getLevel() == 2) {  
	        	///设置背景图标
	        	node.getVideoIconLabel().setIcon(ImageIconUtil.VIDEO);
	        	 ///是否进入时设置背景颜色
	        	  if(mouseEnter){
		            	node.getNodeView().setBackground(ColorUtil.TREE_ENTER_ITEM_BACKGROUND_COLOR);
		            }else{
		            	node.getNodeView().setBackground(ColorUtil.TREE_ITEM_BACKGROUND_COLOR);
		            }
	        	  ///是否被选择时设置前进色
	        	  if(selected){	
	        		  node.getVideoIconLabel().setForeground(ColorUtil.TREE_SELECTED_FOREGROUND_COLOR);
	        		  node.getNameLabel().setForeground(ColorUtil.TREE_SELECTED_FOREGROUND_COLOR);
	        		  node.getInfoLabel().setForeground(ColorUtil.TREE_SELECTED_FOREGROUND_COLOR);
	        		
		            }else{
		            	  node.getVideoIconLabel().setForeground(ColorUtil.TREE_UNSELECTED_FOREGROUND_COLOR);
		        		  node.getNameLabel().setForeground(ColorUtil.TREE_UNSELECTED_FOREGROUND_COLOR);
		        		  node.getInfoLabel().setForeground(ColorUtil.TREE_UNSELECTED_FOREGROUND_COLOR);		            	
		            }
	        	return node.getNodeView();
	        }  
	        return this;  
	}
	
}
