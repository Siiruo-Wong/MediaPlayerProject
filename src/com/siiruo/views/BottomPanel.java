package com.siiruo.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
/**
 * 该类是一个继承JPanel的自定义面板,处在整个界面中的最底部，
 * 用于承载新浪微博、QQ、微信、人人等社交应用的分享功能，以及其他模块
 * @author SIIRUO
 * @version 1.0
 * 
 */
public class BottomPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BottomPanel() {
		//将面板设置为透明，以突显整个页面的背景图片
		setOpaque(true);
		setBackground(new Color(85, 107, 47));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
			}
			
		});
	}
	

}
