package com.siiruo.views;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.siiruo.layout.GBC;
import com.siiruo.util.MediaPlayerUtil;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
/**
 * 该类是一个用于布局component即主组件的工具类,
 * 采用GridBagLayout布局
 * @author SIIRUO
 * @version 1.0
 */
public class ContainLayout {
	/**
	 * component 主组件
	 * playerComponent 媒体播放组件
	 * menuPanel 菜单组件
	 * sidePanel 侧边栏组件
	 * controlPanel 媒体控制组件
	 * bottomPanel 底部分享组件
	 */
	private JComponent component;
	private EmbeddedMediaPlayerComponent playerComponent;
	private JPanel menuPanel;
	private JPanel sidePanel;
	private JPanel controlPanel;
	private JPanel bottomPanel;
	/**
	 * 构造函数
	 * @param playerComponent 媒体播放组件
	 * @param menuPanel 菜单组件
	 * @param sidePanel 侧边栏组件
	 * @param controlPanel 媒体控制组件
	 * @param bottomPanel 底部分享组件
	 */
	public ContainLayout(EmbeddedMediaPlayerComponent playerComponent, JPanel menuPanel, JPanel sidePanel,JPanel bottomPanel) {
		this.playerComponent = playerComponent;
		this.menuPanel = menuPanel;
		this.sidePanel = sidePanel;
		this.bottomPanel = bottomPanel;
	}
	/**
	 * 构造函数
	 * @param component 主组件
	 * @param playerComponent 媒体播放组件
	 * @param menuPanel 菜单组件
	 * @param sidePanel 侧边栏组件
	 * @param bottomPanel  底部分享组件
	 */
	public ContainLayout(JComponent component, EmbeddedMediaPlayerComponent playerComponent, JPanel menuPanel,
			JPanel sidePanel,JPanel bottomPanel) {
		this(playerComponent,menuPanel,sidePanel,bottomPanel);
		this.component = component;
	}
	
/**
 * 具体的布局函数
 */
public void setContainLayout(){
	 
    GridBagLayout layout = new GridBagLayout();
    component.setLayout(layout); 
    component.add(menuPanel, new GBC(0,0,2,1).  
    		setFill(GBC.BOTH).setIpad(200, 10).setWeight(100, 0));  
      
    component.add(playerComponent,new GBC(0,1).  
                 setFill(GBC.BOTH).setIpad(100, 40).setWeight(100, 100));  

    component.add(sidePanel,new GBC(1,1,1,3).setFill(GBC.BOTH).setIpad(100, 40).setWeight(0, 100));  
  
    component.add(controlPanel,new GBC(0,2,1,1).  
                 setFill(GBC.BOTH).setIpad(80,5).setWeight(100, 0));  
    
    component.add(bottomPanel,new GBC(0,3,1,1).  
                  setFill(GBC.BOTH).setIpad(100, 20).setWeight(100, 0));  
}
public void setLayout2(){

    GridBagLayout layout = new GridBagLayout();
    component.setLayout(layout);
    component.add(menuPanel, new GBC(0,0,2,1).  
    		setFill(GBC.BOTH).setIpad(200, 10).setWeight(100, 0));  
      
    component.add(playerComponent,new GBC(0,1,1,1).  
                 setFill(GBC.BOTH).setIpad(100, 40).setWeight(100, 100));  

    component.add(sidePanel,new GBC(1,1,1,2).setFill(GBC.BOTH).setIpad(100, 40).setWeight(0, 100));  
 
    component.add(bottomPanel,new GBC(0,2,1,1).  
                  setFill(GBC.BOTH).setIpad(100, 20).setWeight(100, 0));  
    
}
public void setLayout3(){
	component.setLayout(new BorderLayout(0, 0));

	menuPanel.setPreferredSize(new Dimension(10, 30));
	
	component.add(menuPanel, BorderLayout.NORTH);
	
	JPanel loadPanel = new JPanel();
	component.add(loadPanel, BorderLayout.CENTER);
	loadPanel.setLayout(new BorderLayout(0, 0));
	
	
	sidePanel.setPreferredSize(new Dimension(230, 10));

	loadPanel.add(sidePanel, BorderLayout.EAST);
	
	JPanel load2Panel = new JPanel();
	loadPanel.add(load2Panel, BorderLayout.CENTER);
	load2Panel.setLayout(new BorderLayout(0, 0));
	
	bottomPanel.setPreferredSize(new Dimension(10, 30));
	load2Panel.add(bottomPanel, BorderLayout.SOUTH);

	playerComponent.setBackground(new Color(189, 183, 107));
	load2Panel.add(playerComponent, BorderLayout.CENTER);
}
}
