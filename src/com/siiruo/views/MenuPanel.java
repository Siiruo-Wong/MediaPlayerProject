package com.siiruo.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setLayout(new BorderLayout(0, 0));
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
			}
			
		});
		setBackground(new Color(255,255,255,100));
		JPanel leftPane = new LeftMenuPanel();
		add(leftPane, BorderLayout.WEST);
		JPanel rightPane = new RightMenuPanel();
		add(rightPane, BorderLayout.CENTER);
	}

}
