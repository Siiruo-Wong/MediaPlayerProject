package com.siiruo.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;
public class SiiruoScrollBarUI extends BasicScrollBarUI {
	    @Override
	    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
	        g.translate(thumbBounds.x, thumbBounds.y);
	        g.setColor(new Color(0,255,0,100));
	        Graphics2D g2 = (Graphics2D) g;
	        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.addRenderingHints(rh);
	        // 半透明
	        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
	        g2.fillRect(0, 0, thumbBounds.width, thumbBounds.height-1);
	    }
		@Override
		protected void installDefaults() {
			super.installDefaults();
			scrollBarWidth = 12;
		}
		@Override
	    protected JButton createIncreaseButton(int orientation) {
			JButton button = new JButton();//不再设置按钮
	       // JButton button = new JButton(ImageIconUtil.SCROLLBAR_DECREASE);
	        button.setBorder(null);
	        return button;
	    }
	    @Override
	    protected JButton createDecreaseButton(int orientation) {
	       // JButton button = new JButton(ImageIconUtil.SCROLLBAR_INCREASE);
	       JButton button = new JButton();
	        button.setBorder(null);
	        return button;
	    }
	}
