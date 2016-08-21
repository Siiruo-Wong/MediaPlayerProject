package com.siiruo.testJTree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class STreeCellRenderer extends DefaultTreeCellRenderer// 继承该类
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4509714507907369966L;
	private STreeNode node;
	public static int mouseRow = -1;
	private boolean mouseEnter = false; // 通过mouseEnter判定当前鼠标是否悬停

	/**
	 * 重写
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		setFont(new Font("微软雅黑", Font.BOLD, 12));		
		node = (STreeNode) value;
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Icon icon = ((STreeNode) value).getIcon();// 从节点读取图片
		String txt = ((STreeNode) value).getText(); // 从节点读取文本
		/**
		 * 如果当前节点是根节点的孩子节点并且不是叶子节点，则为该节点设置展开图标
		 */
		if (node.getParent() == model.getRoot()||!node.isLeaf()) {
			setIcon(icon);// 设置图片
		} else {
			setIcon(null);// 设置null
		}		
		setText(txt);// 设置文本		
		if (hasFocus) {
			setBackground(new Color(255, 0, 0));
			setForeground(Color.WHITE);
		}else{
			setBackground(Color.PINK);
			setForeground(Color.BLACK);
		}		 
		/**
		 * 通过mouseRow判断鼠标是否悬停在当前行 
		 */
	     if (mouseRow == row) {
	            mouseEnter = true;
	      } else {
	            mouseEnter = false;
	     }
		return this;
	}
	/**
	 * 重写
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		/**
		 * 根据mouseRow判断鼠标是否悬停位置并执行响应的操作
		 */
		if (mouseEnter) {
			Color oldColor=g.getColor();
			g2.setColor(new Color(0,0,255,60));
			g2.fillRoundRect(0, 0, this.getWidth()-1, this.getHeight() - 1, 4, 4);
			g.setColor(oldColor);
		}
		/**
		 * 如果当前行被选中则执行响应的操作
		 */
		if (selected) {
			Color oldColor=g.getColor();
			g2.setColor(Color.decode("#7EC0EE"));
			g2.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 15, 15);
			g2.setColor(oldColor);
		} else {

		}
		super.paint(g);
	}

}