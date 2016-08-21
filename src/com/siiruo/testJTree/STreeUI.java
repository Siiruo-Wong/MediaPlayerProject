package com.siiruo.testJTree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTreeUI;

public class STreeUI extends BasicTreeUI {

	@Override
	protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
		// TODO Auto-generated method stub
		Color oldColor=g.getColor();
		g.setColor(new Color(255,100,255,200));
		super.paintVerticalLine(g, c, 0, 0, 0);
		g.setColor(oldColor);
	}

	@Override
	protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
		// TODO Auto-generated method stub
		
		Color oldColor=g.getColor();
		g.setColor(new Color(50,255,255,200));
		super.paintHorizontalLine(g, c, 0, 0, 0);
		g.setColor(oldColor);
		
	}

	@Override
	protected void paintDropLine(Graphics g) {
		// TODO Auto-generated method stub
		JTree.DropLocation loc = tree.getDropLocation();
        if (!isDropLine(loc)) {
            return;
        }

        Color c = UIManager.getColor("Tree.dropLineColor");
        if (c != null) {
            g.setColor(c);
           // Color oldColor=g.getColor();
    		//g.setColor(new Color(50,180,255,240));
            Rectangle rect = getDropLineRect(loc);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
           // g.setColor(oldColor);
        }
		 
	}

}
