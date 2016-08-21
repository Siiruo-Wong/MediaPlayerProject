package com.siiruo.scomponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

public class STextField extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 103837871813754990L;
	private Shape shape;
	 public STextField(int size) {
	  super(size);
	  setOpaque(false); 
	 }
	 public STextField() {
		 this(15);
		 setOpaque(false); 
	 }
	 
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, getHeight(), getHeight());
		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				    RenderingHints.VALUE_ANTIALIAS_ON);//消除锯齿效应
		g2d.setColor(new Color(0,255,0,160));
		Stroke stroke=new BasicStroke(1.0f);//设置线宽为1.0
		g2d.setStroke(stroke);
		g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, getHeight(), getHeight());
	}

	@Override
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1,  getHeight(), getHeight());
		}
		return shape.contains(x, y);
	}

}
