package com.siiruo.test;

import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class testMenuBar extends JPanel {

	/**
	 * Create the panel.
	 */
	public testMenuBar() {
		setLayout(new BorderLayout(0, 0));
		 JMenu LoginMenu = new JMenu("系统登录");
		 JMenu UserMangeMenu = new JMenu("用户管理");
		 JMenu SchoolMangeMenu = new JMenu("学籍管理");
		 JMenu HelpMenu = new JMenu("关于");
		 JMenuItem userLoginMenu= new JMenuItem("用户登录");
		  JMenuItem exitLoginMenu= new JMenuItem("退出");
		  LoginMenu.add(userLoginMenu);
		  LoginMenu.add(exitLoginMenu);
		JMenuBar bar=new JMenuBar();
		bar.add(SchoolMangeMenu);
		bar.add(UserMangeMenu);
		bar.add(HelpMenu);
		bar.add(LoginMenu);
		//this.add(bar,BorderLayout.NORTH);
		
	}

}
