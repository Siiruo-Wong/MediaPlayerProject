package com.siiruo.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.siiruo.scomponent.IComboBox;
import com.siiruo.ui.S_HButtonUI;
import com.siiruo.util.DimensionUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
/**
 * 本面板类主要是承载一些功能型的组合框、登录、注册和换肤模块
 * 进一步完善界面的头部
 * @author SIIRUO
 * @version 1.0
 */
public class SubHeadPanel extends JPanel {

	/**
	 * functionComboBox 组合框包含一些设置、关于、退出等功能
	 * registerButton 注册按钮
	 * loginButton 登录按钮
	 * skinPeelerButton 换肤按钮
	 * separator 隔线
	 */
	private static final long serialVersionUID = -8130634360810658295L;
	private JComboBox<String> functionComboBox;
	private JButton registerButton;
	private JButton loginButton;
	private JButton skinPeelerButton;
	private JSeparator separator;
	private Logger logger=LoggerUtil.getLogger(SubHeadPanel.class.getName());
	private Dimension scrDim=DimensionUtil.SCREEN_SIZE;
	/**
	 * Create the panel.
	 */
	public SubHeadPanel() {
		setOpaque(false);	
		skinPeelerButton = new JButton("");
		skinPeelerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point framePoint=MainWindow.getFatherFrame().getLocation();
				Point buttonPoint=skinPeelerButton.getLocation();
				int lx=framePoint.x+buttonPoint.x+skinPeelerButton.getWidth()+30;
				int ly=framePoint.y+buttonPoint.y+skinPeelerButton.getHeight();
				
				SkinPeelerDialog skinDialog=new SkinPeelerDialog();
				if(lx+skinDialog.getWidth()>scrDim.width){
					lx=scrDim.width-skinDialog.getWidth();
				}
				if(ly+skinDialog.getHeight()>scrDim.height){
					ly=scrDim.height-skinDialog.getHeight();
				}
				Point skinPoint=new Point(lx,ly);
				skinDialog.setLocation(skinPoint);
				skinDialog.show();
			}
		});
		skinPeelerButton.setIcon(ImageIconUtil.SKINPEELER__ICON);
		skinPeelerButton.setUI(new S_HButtonUI(new Color(255,255,255,0)));
		skinPeelerButton.setFont(new Font("宋体", Font.PLAIN, 11));
		skinPeelerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		loginButton = new JButton("\u767B\u5F55");
		loginButton.setUI(new S_HButtonUI(new Color(255,255,255,0)));
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("宋体", Font.PLAIN, 11));
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		registerButton = new JButton("\u6CE8\u518C");
		registerButton.setUI(new S_HButtonUI(new Color(255,255,255,0)));
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setFont(new Font("宋体", Font.PLAIN, 11));
		
		functionComboBox = new IComboBox<String>();
		functionComboBox.addItemListener(new FunctionComboBoxItemListener());
		functionComboBox.setLightWeightPopupEnabled(false);
		functionComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"\u8BBE\u7F6E", "\u5173\u4E8E", "\u9000\u51FA"}));
		functionComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		/**
		 * 面板布局
		 */
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(functionComboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
					.addComponent(registerButton).addGap(10)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addComponent(loginButton).addGap(0)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(skinPeelerButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(skinPeelerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(loginButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(separator)
						.addComponent(functionComboBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(0))
		);
		setLayout(groupLayout);

	}
	/**
	 * 内部类：
	 * 响应FunctionComboBox的选择
	 * @author SIIRUO
	 * @version 1.0
	 */
	class FunctionComboBoxItemListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			/**
			 * 之所以添加这个if语句，原因如下：
			 * 该事件是由item的状态发生改变时触发，由于item有两个状态Selected 和 deSelected，
			 * 所以当改变下拉列表中被选中的项的时候，则触发了两次事件，
			 * 1、第一次是上次被选中的项的状态由 Selected 变为 deSelected，即取消选择
			 * 2、 第二次是本次被选中的项的状态由 deSelected 变为 Selected，即新选中，
			 * 因此，ItemStateChanged事件中的代码执行两次。
			 */
			 if(e.getStateChange() == ItemEvent.SELECTED){
				// functionComboBox.getSelectedItem().toString();
				// System.out.println(functionComboBox.getSelectedIndex());
					int index=functionComboBox.getSelectedIndex();
					switch(index){
					case 0:{
						//弹出设置对话框
					}
						break;
					case 1:{
						//弹出关于对话框
					}
						break;
					case 2:{
						//关闭播放器
						System.exit(0); 
					}
						break;
					default:
						break;
					}
			 }
				

		}		
	}
}
