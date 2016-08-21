package com.siiruo.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * 自定义的控制按钮UI
 * 具体绘制可以参考父类VOLUME_DEFAULT
 * @author SIIRUO
 * @version 1.0
 */
public class S_CButtonUI extends BasicButtonUI {

	@Override
	public void paint(Graphics g, JComponent c) {
		// TODO Auto-generated method stub
		super.paint(g, c);
		g.setColor(new Color(255,255,255,0));
		//g.fillOval(0, 0, c.getWidth(), c.getHeight());
		g.fillArc(0, 0,  c.getWidth(), c.getHeight(), 0, 360);
	}

	@Override
	protected void installDefaults(AbstractButton b) {
		// TODO Auto-generated method stub
		super.installDefaults(b);
		 LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
	}

	@Override
	protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
		// TODO Auto-generated method stub
		super.paintIcon(g, c, iconRect);
	}

	@Override
	protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
		// TODO Auto-generated method stub
		super.paintText(g, c, textRect, text);
	}

	@Override
	protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
		// TODO Auto-generated method stub
		super.paintText(g, b, textRect, text);
	}

	@Override
	protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect,
			Rectangle iconRect) {
		// TODO Auto-generated method stub
		super.paintFocus(g, b, viewRect, textRect, iconRect);
	}

	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		// TODO Auto-generated method stub
		super.paintButtonPressed(g, b);
	}
	

}
