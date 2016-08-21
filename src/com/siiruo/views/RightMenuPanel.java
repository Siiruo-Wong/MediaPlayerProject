package com.siiruo.views;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.siiruo.listener.MediaDo;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
/**
 * 右菜单面板类
 * 主要完成搜索等功能
 * @author SIIRUO
 * @version 1.0
 */
public class RightMenuPanel extends JPanel {
	/**
	 * searchLabel  搜索标签
	 * urlLabel URL标签
	 * searchTextField 搜索文本框
	 * urlTextField URL文本框
	 */
	private static final long serialVersionUID = 9046536393218639067L;
	private JLabel searchLabel;
	private JLabel urlLabel;
	private JTextField searchTextField;
	private JTextField urlTextField;
	private Logger logger=LoggerUtil.getLogger(RightMenuPanel.class.getName());
	/**
	 * Create the panel.
	 */
	public RightMenuPanel() {
		setOpaque(false);
		searchTextField = new JTextField();

		searchLabel = new JLabel("");
		searchLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		searchLabel.setHorizontalAlignment(SwingConstants.LEFT);
		searchLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchLabel.setIcon(ImageIconUtil.SEARCH_LABEL_DEFAULT);
		
		urlTextField = new JTextField();
		urlTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					new MediaDo().doWork(urlTextField.getText());
				}
			}
			
		});
		
		 urlLabel = new JLabel("");
		urlLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		urlLabel.setIcon(ImageIconUtil.URL_LABEL_DEFAULT);
		urlLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MediaDo().doWork(urlTextField.getText());
			}
		});
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0)
					.addComponent(urlTextField, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(urlLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchLabel)
					.addGap(5))
		);
		groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.TRAILING)
		.addGroup(groupLayout.createSequentialGroup()//.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(urlLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
										.addComponent(urlTextField, GroupLayout.PREFERRED_SIZE, 13, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								//.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(searchLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
										.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 17, Short.MAX_VALUE))))
				.addGap(0))
		);
		setLayout(groupLayout);

	}

}
