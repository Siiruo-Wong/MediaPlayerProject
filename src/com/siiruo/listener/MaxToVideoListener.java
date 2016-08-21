package com.siiruo.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.siiruo.views.MainWindow;
/**
 * 该类主要是为最大化标签maxLabel定义的鼠标监听类
 * 虽然大可不必单独为其定义一个监听类，毕竟其只需要一个点击事件
 * 但是考虑到可扩展性，仍然采取了这种方式
 * @author SIIRUO
 * @version 1.0
 */
public class MaxToVideoListener extends MouseAdapter{
	/**
	 * frame  主窗口
	 * maxLabel 最大化标签
	 */
	private MainWindow frame;
	private JLabel maxLabel;
	/**
	 * Constructor
	 * @param frame 主窗口
	 * @param maxLabel 最大化标签
	 */
	public MaxToVideoListener(MainWindow frame,JLabel maxLabel){
		this.frame=frame;
		this.maxLabel=maxLabel;
	}
	public MaxToVideoListener(JLabel maxLabel){
		this.frame=MainWindow.getFatherFrame();
		this.maxLabel=maxLabel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		/**
		 * 一旦点击事件发生，视频播放窗口最大化或者是退出最大化状态
		 */
		System.out.println("frame:"+(frame==null));
		MaxToVideo.getMaxToVideo().doMax(frame, maxLabel);
	}
	
}
