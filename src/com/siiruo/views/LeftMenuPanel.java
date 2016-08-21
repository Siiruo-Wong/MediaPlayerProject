package com.siiruo.views;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.siiruo.scomponent.SMenuItem;
import com.siiruo.scomponent.SPopupMenu;
import com.siiruo.ui.S_HButtonUI;

/**
 * 文件菜单栏
 * @author SIIRUO
 * @version 1.0
 */
public class LeftMenuPanel extends JPanel {
	/**
	 * filePopupMenu 弹出式文件菜单
	 * fileButton 文件按钮
	 */
	private static final long serialVersionUID = 7961981287244285443L;
	private static JPopupMenu filePopupMenu;
	private JButton fileButton;
	/**
	 * Create the panel.
	 */
	public LeftMenuPanel() {
		setOpaque(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
			}
		});
		///////////////////////////菜单项设置///////////////////////////
		JMenuItem open=new SMenuItem("打开...");
		JMenuItem otherSave=new SMenuItem("另存为...");
		JMenuItem close=new SMenuItem("关闭...");
		open.setActionCommand("open");
		otherSave.setActionCommand("otherSave");
		close.setActionCommand("close");
		///////////////////////////文件弹出式菜单设置//////////////////////
		filePopupMenu=new SPopupMenu();
		((SPopupMenu) filePopupMenu).addMenuItems(new JMenuItem[]{open,otherSave,close});
		filePopupMenu.setOpaque(true);
		filePopupMenu.setFocusable(true);
		filePopupMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		///////////////////////////文件按钮设置/////////////////////////
		fileButton = new JButton("文件");
		fileButton.setBorder(null);
		fileButton.setOpaque(true);
		fileButton.setUI(new S_HButtonUI(new Color(51, 153, 204,0)));
		fileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fileButton.setBackground(SystemColor.inactiveCaption);
		fileButton.setIcon(new ImageIcon(LeftMenuPanel.class.getResource("/com/siiruo/function/images/fileIcon.png")));
		//fileButton.setUI(new S_CButtonUI());
		///////////////////////////为文件按钮添加鼠标响应///////////////////
		fileButton.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				filePopupMenu.setVisible(false);//fileButton失去焦点时，将filePopupMenu设置为不可见
				
			}
			@Override
			public void focusGained(FocusEvent e) {		
			}
		});
		fileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(filePopupMenu.isVisible()){
					filePopupMenu.setVisible(false);
				}else{
					/**
					 * 弹出式文件菜单显示设置包括是否可见以及显示位置
					 */
					filePopupMenu.setVisible(true);
					Point framePoint=MainWindow.getFatherFrame().getLocation();
					Point point=new Point(framePoint.x,framePoint.y+fileButton.getHeight()+getHeight());
					filePopupMenu.setLocation(point);
					filePopupMenu.show();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				fileButton.setForeground(Color.WHITE);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				fileButton.setForeground(Color.BLACK);
			}
		});
		//////////////////////////面板布局///////////////////////
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(fileButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(fileButton, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		//fileButton.setComponentPopupMenu(filePopupMenu);
	}
	/**
	 * 静态方法获得弹出式文件菜单filePopupMenu
	 * @return
	 */
	public static JPopupMenu getFilePopupMenu() {
		return filePopupMenu;
	}
	/**
	 * 静态方法设置弹出式文件菜单
	 * @param filePopupMenu
	 */
	public static void setFilePopupMenu(JPopupMenu filePopupMenu) {
		LeftMenuPanel.filePopupMenu = filePopupMenu;
	}
}
