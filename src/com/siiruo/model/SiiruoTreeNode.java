package com.siiruo.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.siiruo.util.ColorUtil;

public class SiiruoTreeNode extends DefaultMutableTreeNode {
	/**
	 * 一级承载面板
	 */
    public JPanel rootContent;  
	/** 图片 */  
    private Icon firstIcon; //前图标 
    private Icon lastIcon;  //后图标
         
    public JLabel firstIconLabel;  //前标签
    public JLabel lastIconLabel;  //后标签
 
    
    /////////////////////////////////////
    /** 文字 */  
    private String name;
    public JLabel nameLabel;  //文字标签 
    ////////////////////////////////////
    
	/**
	 * 二级承载面板
	 */
    public JPanel nodeContent; 
	/** 图片 */  
    private Icon videoIcon;  
    /** 序号 */  
    private String index;
    /**
     * 信息
     */
    private String information;    
    public JLabel videoIconLabel;//图标标签  
    public JLabel infoLabel; //信息标签 
      
  /**
   * 一级初始化分组节点 构造函数  
   * @param firstIcon
   * @param lastIcon
   * @param name
   */
    public SiiruoTreeNode(Icon firstIcon,Icon lastIcon,String name) {  
        this.firstIcon = firstIcon;  
        this.lastIcon = lastIcon;  
        this.name = name;  
        // 初始化UI  
        initRootGUI();  
    }
      /**
       * 二级初始化分组节点 构造函数
       * @param icon
       * @param index
       * @param name
       * @param information
       */
    public SiiruoTreeNode(Icon videoIcon, String index, String name, String information) { 
    	this.videoIcon=videoIcon;
    	this.index=index;
    	this.name =name;
    	this.information=information;
        initNodeGUI();  
    }   
    /** 
     * 自定义分组UI 
     */  
    private void initRootGUI() {  
        rootContent = new JPanel(); 
      
        rootContent.setBackground(ColorUtil.TREE_ROOT_BACKGROUND_COLOR);
        rootContent.setLayout(new BorderLayout());  
        rootContent.setOpaque(true);  
        rootContent.setPreferredSize(new Dimension(230, 22));  
       // rootContent.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));  
          
        firstIconLabel = new JLabel(firstIcon);  
        firstIconLabel.setBounds(6, 5, 10, 10);  
        rootContent.add(firstIconLabel,BorderLayout.WEST);  
        nameLabel = new JLabel(name);  
        nameLabel.setBounds(23, 0, 132, 28);  
        rootContent.add(nameLabel,BorderLayout.CENTER);  
        
        lastIconLabel = new JLabel(lastIcon);  
        lastIconLabel.setBounds(6, 5, 20, 16);  
        rootContent.add(lastIconLabel,BorderLayout.EAST);    
    }
    /**
	 * 初始化
	 */
	private void initNodeGUI() {
		nodeContent = new JPanel();
		nodeContent.setBackground(ColorUtil.TREE_ITEM_BACKGROUND_COLOR);
		nodeContent.setLayout(new BorderLayout());
		nodeContent.setOpaque(true);
		nodeContent.setPreferredSize(new Dimension(230, 22));
		// nodeContent.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		videoIconLabel = new JLabel(videoIcon);
		videoIconLabel.setText(index);
		videoIconLabel.setBounds(8, 4, 39, 42);
		nodeContent.add(videoIconLabel, BorderLayout.WEST);

		nameLabel = new JLabel(name);
		nameLabel.setBounds(59, 5, 132, 19);
		nodeContent.add(nameLabel, BorderLayout.CENTER);

		infoLabel = new JLabel(information);
		infoLabel.setBounds(59, 28, 132, 17);
		nodeContent.add(infoLabel, BorderLayout.EAST);
		nodeContent.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focusLost");
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focusGained");
				
			}
		});
	}
     
  /** 
   * 将自定义UI返回给渲染器 <br/> 
   * 供渲染器调用，返回的必须是一个Component 
   * @return 
   */  
	public Component getRootView() {
		return rootContent;
	}
	public Component getNodeView() {
		return nodeContent;
	}
	public JLabel getFirstIconLabel() {
		return firstIconLabel;
	}

	public void setFirstIconLabel(JLabel firstIconLabel) {
		this.firstIconLabel = firstIconLabel;
	}

	public JLabel getLastIconLabel() {
		return lastIconLabel;
	}

	public void setLastIconLabel(JLabel lastIconLabel) {
		this.lastIconLabel = lastIconLabel;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	} 
	public JLabel getVideoIconLabel() {
		return videoIconLabel;
	}
	public void setVideoIconLabel(JLabel iconLabel) {
		this.videoIconLabel = iconLabel;
	}
	public JLabel getInfoLabel() {
		return infoLabel;
	}
	public void setInfoLabel(JLabel infoLabel) {
		this.infoLabel = infoLabel;
	}  
	
}
