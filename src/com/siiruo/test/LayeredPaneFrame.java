package com.siiruo.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.siiruo.util.MediaPlayerUtil;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import javax.swing.JLayeredPane;

public class LayeredPaneFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LayeredPaneFrame frame = new LayeredPaneFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LayeredPaneFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		 layeredPane.setBorder(BorderFactory.createTitledBorder("<html><b><i>"
		 +
		 "<u><font face='SansSerif' size='5' color='red'>layeredPane" +
		 "</font></u></i></b></html>"));
		Dimension dimension = new Dimension(700, 600);
		layeredPane.setPreferredSize(dimension);

		Point origin = new Point(0, 0);
		Rectangle rectangle = new Rectangle(origin, dimension);

		JPanel pp = new JPanel();
		pp.setLayout(new BorderLayout());
		pp.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		pp.setBackground(new Color(255, 0, 0, 150));
		pp.setBounds(rectangle);
	//	EmbeddedMediaPlayerComponent playerComponent= MediaPlayerUtil.getMediaPlayerComponent();
		JPanel playerComponent=new JPanel();
		playerComponent.setBounds(rectangle);
		playerComponent.setOpaque(false);
		layeredPane.add(pp, new Integer(1)); // the same to	 layeredPane.add(panelBg);
		layeredPane.add(playerComponent, new Integer(2));
		contentPane.add(layeredPane, BorderLayout.CENTER);
	}

}
