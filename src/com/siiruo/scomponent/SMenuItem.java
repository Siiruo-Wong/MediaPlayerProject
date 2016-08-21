package com.siiruo.scomponent;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JMenuItem;

import com.siiruo.listener.SMenuItemListener;
import com.siiruo.util.ColorUtil;

public class SMenuItem extends JMenuItem {
	public SMenuItem(){
		super();
		initMenuItem();
	}
	public SMenuItem(String text){
		super(text);
		initMenuItem();
	}
	public SMenuItem(Icon icon){
		super(icon);
		initMenuItem();
	}
	public SMenuItem(String text,Icon icon){
		super(text,icon);
		initMenuItem();
	}
	
	public void initMenuItem(){
		setOpaque(true);
		setBackground(ColorUtil.MENUITEM_BACKGROUND_COLOR);
		setForeground(ColorUtil.MENUITEM_FOREGROUND_COLOR);
		addMouseListener(new SMenuItemListener(this));
	}
	
}
