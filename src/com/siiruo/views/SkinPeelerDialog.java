package com.siiruo.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Properties;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.siiruo.ui.S_HButtonUI;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.PropertiesUtil;

public class SkinPeelerDialog extends JDialog {
	private static final long serialVersionUID = -6535146460159292989L;
	private final JPanel contentPanel = new JPanel();
	private JRadioButton SkinRB1;
	private JRadioButton SkinRB2;
	private JRadioButton SkinRB3;
	private JRadioButton SkinRB4;
	private JRadioButton SkinRB5;
	private JRadioButton SkinRB6;
	private ButtonGroup group;
	private Logger logger=LoggerUtil.getLogger(SkinPeelerDialog.class.getName());
	/**
	 * Create the dialog.
	 */
	public SkinPeelerDialog() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				System.out.println("焦点获得");
			}
			public void windowLostFocus(WindowEvent arg0) {
				dispose();
				System.out.println("焦点离开");
			}
		});
		setBackground(new Color(173, 216, 230));
		setUndecorated(true);
		setBounds(100, 100, 409, 275);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new LineBorder(new Color(152, 251, 152), 2));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 3, 4, 8));
		{
			SkinRB1 = new JRadioButton("\u7C7B\u578B1");
			SkinRB1.setActionCommand("skinPeeler1");
			SkinRB1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			SkinRB1.setIcon(ImageIconUtil.DEFAULT_SKIN1__ICON);
			SkinRB1.setSelectedIcon(ImageIconUtil.DEFAULT_SKIN1_SELECTED__ICON);
			SkinRB1.setHorizontalTextPosition(SwingConstants.CENTER);
			SkinRB1.setVerticalTextPosition(SwingConstants.BOTTOM);
			contentPanel.add(SkinRB1);
		}
		{
			SkinRB2 = new JRadioButton("\u7C7B\u578B2");
			SkinRB2.setActionCommand("skinPeeler2");
			SkinRB2.setSelectedIcon(ImageIconUtil.DEFAULT_SKIN2_SELECTED__ICON);
			SkinRB2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			SkinRB2.setIcon(ImageIconUtil.DEFAULT_SKIN2__ICON);
			SkinRB2.setHorizontalTextPosition(SwingConstants.CENTER);
			SkinRB2.setVerticalTextPosition(SwingConstants.BOTTOM);
			contentPanel.add(SkinRB2);
		}
		{
			SkinRB3 = new JRadioButton("\u7C7B\u578B3");
			SkinRB3.setActionCommand("skinPeeler3");
			SkinRB3.setSelectedIcon(ImageIconUtil.DEFAULT_SKIN3_SELECTED__ICON);
			SkinRB3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			SkinRB3.setIcon(ImageIconUtil.DEFAULT_SKIN3__ICON);
			SkinRB3.setHorizontalTextPosition(SwingConstants.CENTER);
			SkinRB3.setVerticalTextPosition(SwingConstants.BOTTOM);
			contentPanel.add(SkinRB3);
		}
		{
			SkinRB4 = new JRadioButton("\u7C7B\u578B4");
			SkinRB4.setActionCommand("skinPeeler4");
			SkinRB4.setSelectedIcon(ImageIconUtil.DEFAULT_SKIN4_SELECTED__ICON);
			SkinRB4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			SkinRB4.setIcon(ImageIconUtil.DEFAULT_SKIN4__ICON);
			SkinRB4.setHorizontalTextPosition(SwingConstants.CENTER);
			SkinRB4.setVerticalTextPosition(SwingConstants.BOTTOM);
			contentPanel.add(SkinRB4);
		}
		{
			SkinRB5 = new JRadioButton("\u7C7B\u578B5");
			SkinRB5.setActionCommand("skinPeeler5");
			SkinRB5.setSelectedIcon(ImageIconUtil.DEFAULT_SKIN5_SELECTED__ICON);
			SkinRB5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			SkinRB5.setIcon(ImageIconUtil.DEFAULT_SKIN5__ICON);
			SkinRB5.setHorizontalTextPosition(SwingConstants.CENTER);
			SkinRB5.setVerticalTextPosition(SwingConstants.BOTTOM);
			contentPanel.add(SkinRB5);
		}
		{
			SkinRB6 = new JRadioButton("\u7C7B\u578B6");
			SkinRB6.setActionCommand("skinPeeler6");
			SkinRB6.setSelectedIcon(ImageIconUtil.DEFAULT_SKIN6_SELECTED__ICON);
			SkinRB6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			SkinRB6.setIcon(ImageIconUtil.DEFAULT_SKIN6__ICON);
			SkinRB6.setHorizontalTextPosition(SwingConstants.CENTER);
			SkinRB6.setVerticalTextPosition(SwingConstants.BOTTOM);
			contentPanel.add(SkinRB6);
		}
		group=new ButtonGroup();
		group.add(SkinRB1);
		group.add(SkinRB2);
		group.add(SkinRB3);
		group.add(SkinRB4);
		group.add(SkinRB5);
		group.add(SkinRB6);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.control);
			buttonPane.setBorder(new LineBorder(new Color(152, 251, 152)));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u5E94\u7528");
				okButton.setFont(new Font("华文楷体", Font.BOLD, 14));
				okButton.setActionCommand("OK");
				okButton.addMouseListener(new SkinMouseListener());
				okButton.setUI(new S_HButtonUI(new Color(176,224,230,120)));
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.setFont(new Font("华文楷体", Font.BOLD, 14));
				cancelButton.setActionCommand("CANCEL");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setUI(new S_HButtonUI(new Color(176,224,230,120)));
				cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton moreSkinButton = new JButton("\u81EA\u5B9A\u4E49...");
				moreSkinButton.setFont(new Font("华文楷体", Font.BOLD, 14));
				moreSkinButton.setActionCommand("MORE");
				moreSkinButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				moreSkinButton.setUI(new S_HButtonUI(new Color(176,224,230,120)));
				moreSkinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				buttonPane.add(moreSkinButton);
			}
		}
	}
	/**
	 * 该类既继承了Observable类也实现了MouseListener接口
	 * 因此，此类的主要作用如下：
	 * 1、作为观察者模型中的主体，当用户需要实现换肤操作时，将肤色信息"推送"给所有的观察者
	 * 		本类只添加一个观察者MainWindow。
	 * 2、作为鼠标监听器，响应用户的点击事件
	 * @author SIIRUO
	 * @version 1.0
	 */
	class SkinMouseListener extends Observable implements MouseListener{
		
		public SkinMouseListener() {
			super();
			/**
			 * 将MainWindow作为观察者添加到SkinMouseListener的vector属性中
			 * vector数据结构维护者所有的观察者
			 */
			addObserver(MainWindow.getFatherFrame());
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton button=(JButton) e.getSource();
			String command=button.getActionCommand();
			switch(command){
			case "OK":
				Ok_Click();
				break;
			case "CANCEL":
				Cancel_Click();
				break;
			case "MORE":
				 More_Click();
				break;
			default:
				break;
			}
			
		}
		/**
		 * 点击应用按钮时的响应操作
		 */
		public void Ok_Click(){
			dispose();
			Enumeration<AbstractButton> buttons=group.getElements();
			if(buttons==null||!buttons.hasMoreElements()){
				logger.error("SelectedButton is null...");
			}
			while(buttons.hasMoreElements()){
				AbstractButton button=buttons.nextElement();
				if(button.isSelected()){
					String path=".\\src\\resources\\skin.properties";
					String value="/com/siiruo/skin/images/"+button.getActionCommand()+".jpg";
					Properties pro=PropertiesUtil.getProperties(path);	
					try {
						OutputStream fos = new FileOutputStream(path);          
						pro.setProperty("defaultSkin", value);
						// 以适合使用 load 方法加载到 Properties 表中的格式，
						// 将此 Properties 表中的属性列表（键和元素对）写入输出流
						pro.store(fos, "Update '" + "defaultSkin" + "' value");
					} catch (FileNotFoundException e) {
						logger.error("无法找到要存储的目的文件");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("文件输出流异常");
					}
					/**
					 * 下面两行代码在该类作为主体角色的过程中起着至关重要的作用
					 * 其中，setChanged在状态发生改变时，是必须调用的
					 * notifyObservers将具体的信息推送给所有的观察者
					 */
					setChanged();
					notifyObservers(button.getActionCommand());
					 break ;
				}
			}
		}
		/**
		 * 点击取消按钮时的响应操作
		 */
		public void Cancel_Click(){
			dispose();
		}
		/**
		 * 点击自定义按钮时的响应操作
		 */
		public void More_Click(){
			dispose();
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
