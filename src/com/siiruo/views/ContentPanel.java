package com.siiruo.views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * 这个面板主要是嵌入在FramePanel中的containPanel中
 * 
 * @author SIIRUO
 * @version 1.0
 */
public class ContentPanel extends JPanel {
	/**
	 * videoPanel 视频面板，主要存放播放视频 slidepPanel 侧边栏面板，主要存放视频信息列表 menuPanel
	 * 菜单面板，主要存放打开、保存等交互信息 bottomPanel 底部面板，主要存放分享等社交信息 controlPanel 媒体控制面板，主要
	 * 完成对媒体的控制功能
	 */
	private static final long serialVersionUID = 1355464577L;
	private JPanel videoPanel;
	private JPanel slidepPanel;
	private JPanel menuPanel;
	private JPanel bottomPanel;
	private JPanel controlPanel;

	/**
	 * 构造函数 Create the panel.
	 */
	public ContentPanel() {
		setBackground(new Color(0, 0, 0));
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(128, 128, 0));
		videoPanel = new JPanel();
		videoPanel.setBackground(new Color(95, 158, 160));
		slidepPanel = new JPanel();
		slidepPanel.setBackground(new Color(100, 149, 237));
		bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(112, 128, 144));

		controlPanel = new JPanel();
		controlPanel.setBackground(new Color(173, 216, 230));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(menuPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450,
						Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
								.addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
								.addComponent(videoPanel, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
						.addGap(0).addComponent(slidepPanel, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(menuPanel, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup().addGap(0)
														.addComponent(videoPanel, GroupLayout.DEFAULT_SIZE, 195,
																Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 47,
														GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(bottomPanel,
												GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
								.addComponent(slidepPanel, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))));
		setLayout(groupLayout);
	}

	/**
	 * 返回videoPanel
	 * 
	 * @return videoPanel
	 */
	public JPanel getVideoPanel() {
		return videoPanel;
	}

	/**
	 * 设置videoPanel
	 * 
	 * @param videoPanel
	 */
	public void setVideoPanel(JPanel videoPanel) {
		this.videoPanel = videoPanel;
	}

	/**
	 * 返回 slidepPanel
	 * 
	 * @return slidepPanel
	 */
	public JPanel getSlidepPanel() {
		return slidepPanel;
	}

	/**
	 * 设置 slidepPanel
	 * 
	 * @param slidepPanel
	 */
	public void setSlidepPanel(JPanel slidepPanel) {
		this.slidepPanel = slidepPanel;
	}

	/**
	 * 返回menuPanel
	 * 
	 * @return menuPanel
	 */
	public JPanel getMenuPanel() {
		return menuPanel;
	}

	/**
	 * 设置 menuPanel
	 * 
	 * @param menuPanel
	 */
	public void setMenuPanel(JPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	/**
	 * 返回bottomPanel
	 * 
	 * @return bottomPanel
	 */
	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	/**
	 * 设置bottomPanel
	 * 
	 * @param bottomPanel
	 */
	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}
}
