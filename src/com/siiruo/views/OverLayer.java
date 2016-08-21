package com.siiruo.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
/**
 * 播放器的覆盖层
 * @author SIIRUO
 *
 */
public class OverLayer extends JFrame {
	private JPanel contentPane;//内容面板
	private JLabel informationLabel;//视频信息标签
	private JLabel loadLabel;//加载提示信息
	private JLayeredPane layeredPane;//层面板
	private JPanel controlLayer;//控制层
	private JPanel promptLayer;//提示层
	
	
	/**
	 * Constructor
	 */
	public OverLayer() {
		
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setFocusableWindowState(false);
		// com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.2f);
		contentPane = new TransulencePanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		layeredPane=new JLayeredPane();
		setLayeredPane(layeredPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				/**
				 * 当该窗口打开后初始化各层，之所以这么做的原因
				 * 1、层面板没有布局管理器，因此必须设置位置和大小，为了填充整个窗口，则必须等到该窗口完全确定之后
				 * 才能利用其位置和大小属性，否则大小始终为0。
				 * 2、考虑到线程问题，是窗口先显示在初始化，减少用户的等待时间
				 */
				initLayeredPane();
			}
		});
		addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				OverLayer overLayer=(OverLayer) e.getSource();
				/**
				 * 当该窗口变化时，也要及时地更新层面板中所有组件的大小
				 * 由于，位置始终为(0,0)，因此只更新了大小即可
				 */
				if(controlLayer!=null&&promptLayer!=null){
					controlLayer.setSize(overLayer.getSize());
					promptLayer.setSize(overLayer.getSize());
				}
			}
			
		});
		setContentPane(contentPane);
	}
	public JPanel getControlLayer() {
		return controlLayer;
	}
	public void setControlLayer(JPanel controlLayer) {
		this.controlLayer = controlLayer;
	}
	public JPanel getPromptLayer() {
		return promptLayer;
	}
	public void setPromptLayer(JPanel promptLayer) {
		this.promptLayer = promptLayer;
	}
	
	public JLabel getInformationLabel() {
		return informationLabel;
	}
	public void setInformationLabel(JLabel informationLabel) {
		this.informationLabel = informationLabel;
	}
	
	public JLabel getLoadLabel() {
		return loadLabel;
	}
	public void setLoadLabel(JLabel loadLabel) {
		this.loadLabel = loadLabel;
	}
	/**
	 * 初始化层面板
	 */
	private void initLayeredPane(){
//		layeredPane.requestFocus(false);
		//控制面板
		ControlPanel cp = StaticPanel.getControlPanel();
		cp.setOpaque(false);
		cp.setPreferredSize(new Dimension(10, 70));
		//视频信息标签
		informationLabel = new JLabel("");
		informationLabel.setForeground(Color.YELLOW);
		//控制层
		controlLayer=new JPanel();
		controlLayer.setVisible(false);
		controlLayer.setOpaque(false);
		controlLayer.setBounds(0, 0, this.getWidth(), this.getHeight());
		controlLayer.setLayout(new BorderLayout());
		controlLayer.add(informationLabel,BorderLayout.NORTH);
		controlLayer.add(cp,BorderLayout.SOUTH);
		//设置和添加控制层
		layeredPane.setLayer(controlLayer, 111);
		layeredPane.add(controlLayer);
		//提示层
		promptLayer=new JPanel();
		promptLayer.setVisible(false);
		promptLayer.setOpaque(false);
		promptLayer.setBounds(0, 0, this.getWidth(), this.getHeight());
		promptLayer.setLayout(new BorderLayout());
		
		 loadLabel=new JLabel("加载中...");
//		loadLabel.setHorizontalTextPosition(SwingConstants.CENTER);
//		loadLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		loadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loadLabel.setForeground(Color.WHITE);
		promptLayer.add(loadLabel, BorderLayout.CENTER);
		//设置和添加提示层
		layeredPane.setLayer(promptLayer, 112);
		layeredPane.add(promptLayer);
		//设置为可见状态
		layeredPane.setVisible(true);
	
	}
	/**
	 * 窗口的透明内容面板
	 * @author SIIRUO
	 *
	 */
	class  TransulencePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = -24827144626346647L;
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2=(Graphics2D) g;
			//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(new Color(255,255,255,0));
		}
		
	}


}
