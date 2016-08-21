package com.siiruo.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import com.siiruo.util.ColorUtil;
import com.siiruo.util.FontUtil;
import com.siiruo.util.ImageIconUtil;
/**
 * 重新定义组合框的UI
 * @author SIIRUO
 * @version 1.0
 */
public class IComboBoxUI extends BasicComboBoxUI {
	 private JButton arrow;
	 private ImageIcon icon;
	 private ImageIcon rolloverIcon;
	 private boolean boundsLight = false;
	 private static final int ARCWIDTH = 15;
	 private static final int ARCHEIGHT = 15;
	 public IComboBoxUI() {
	  super();
	 }

	 public IComboBoxUI(ImageIcon icon, ImageIcon rolloverIcon,boolean boundsLight) {
		super();
		this.arrow = new JButton();
	    this.icon=icon;
		this.rolloverIcon=rolloverIcon;
		this.boundsLight = boundsLight;
	}

	protected JButton createArrowButton() {
		if(arrow ==null ){
			 this.arrow = new JButton();
			 this.icon=ImageIconUtil.COMBOBOX_ARROW_ICON;
			 this.rolloverIcon=ImageIconUtil.COMBOBOX_ARROW_ICON_INTO;
		}
		arrow.setIcon(icon);
		arrow.setRolloverIcon(rolloverIcon);
		arrow.setRolloverEnabled(true);
		arrow.setBorder(null);
		arrow.setBackground(ColorUtil.COMBOBOX_ARROW_BACKGROUND_COLOR);
		arrow.setOpaque(false);
		arrow.setContentAreaFilled(false);
	  return arrow;
	 }
	 /**
	  * 绘制组合框的外形
	  */
	 public void paint(Graphics g, JComponent c) {
	  hasFocus = comboBox.hasFocus();
	  Graphics2D g2 = (Graphics2D)g;
	  if (!comboBox.isEditable()) {
	   Rectangle r = rectangleForCurrentValue();
	   	//重点:JComboBox的textfield 的绘制 并不是靠Renderer来控制
	   	//它会通过paintCurrentValueBackground来绘制背景
	   	//然后通过paintCurrentValue();去绘制JComboBox里显示的值
	   //paintCurrentValueBackground(g2, r, hasFocus);//绘制组合框的背景
	   //paintCurrentValue(g2, r, hasFocus);//绘制组合框的文本域中的值
	  }
	  
	  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	    RenderingHints.VALUE_ANTIALIAS_ON);
	  //组合框的外围宽度
	  int width = (int) this.getPreferredSize(c).getWidth()
	    + arrow.getWidth() - 2;
	  int height = 0;
	  int heightOffset = 0;
	  if (comboBox.isPopupVisible()) {
	   heightOffset = 5;
	   height = (int) this.getPreferredSize(c).getHeight();
	   arrow.setIcon(rolloverIcon);
	  } else {
	   heightOffset = 0;
	   height = (int) this.getPreferredSize(c).getHeight() - 1;
	   arrow.setIcon(icon);
	  }
	  if (comboBox.isFocusable()) {
	   g2.setColor(ColorUtil.COMBOBOX_ARROW_FOCUS_COLOR);
	  }
	 // g2.drawRoundRect(0, 0, width, height + heightOffset,ARCWIDTH,ARCHEIGHT);//绘制组合框的外围
	 }
/**
 * 绘制组合框的文本域中的值
 */
	 public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
	  Font oldFont = comboBox.getFont();
	  comboBox.setFont(FontUtil.COMBOBOX_FONT);
	  
	  super.paintCurrentValue(g, bounds, hasFocus);
	  comboBox.setFont(oldFont);
	 }

	 public Dimension getPreferredSize(JComponent c) {
	  return super.getPreferredSize(c);
	 }

	 public boolean isBoundsLight() {
	  return boundsLight;
	 }

	 public void setBoundsLight(boolean boundsLight) {
	  this.boundsLight = boundsLight;
	 }

	 protected ComboPopup createPopup() {
	  return new LargerComboPopup(comboBox);
	 }
	 //////////////////////////////////////////////
	 /**
	  * 内部类：主要解决由于下拉框中的条目太长而不能完全显示的问题
	  * （根据最长的条目确定下拉框的宽度）
	  * @author SIIRUO
	  * @version 1.0
	  */
	 class LargerComboPopup extends BasicComboPopup {
	        /**
		 * 
		 */
		private static final long serialVersionUID = -3804732150248037511L;

			public LargerComboPopup(JComboBox comboBox) {
	            super(comboBox);
	        }
	  
	        public void show() {
	            int selectedIndex = comboBox.getSelectedIndex();
	            if (selectedIndex == -1) {
	                list.clearSelection();
	            } else {
	                list.setSelectedIndex(selectedIndex);
	                list.ensureIndexIsVisible(selectedIndex);
	            }
	  
	            Insets insets = getInsets();
	            Dimension listDim = list.getPreferredSize();
	            boolean hasScrollBar = scroller.getViewport().getViewSize().height != listDim.height;
	            if (hasScrollBar) {
	                JScrollBar scrollBar = scroller.getVerticalScrollBar();
	                listDim.width += scrollBar.getPreferredSize().getWidth();
	            }
	  
	            int width = Math.max(listDim.width, comboBox.getWidth() - (insets.right + insets.left));
	            int height = getPopupHeightForRowCount(comboBox.getMaximumRowCount());
	            Rectangle popupBounds = computePopupBounds(0, comboBox.getHeight(), width, height);
	  
	            Dimension scrollSize = popupBounds.getSize();
	            scroller.setMaximumSize(scrollSize);
	            scroller.setPreferredSize(scrollSize);
	            scroller.setMinimumSize(scrollSize);
	  
	            list.revalidate();
	            show(comboBox, popupBounds.x, popupBounds.y);
	        }
	 	   /**
	 	    * 重载paintBorder方法 绘制下拉框
	 	    */
	 	   public void paintBorder(Graphics g){
	 	    Graphics2D g2 = (Graphics2D) g;
	 	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 	      RenderingHints.VALUE_ANTIALIAS_ON);
	 	    g2.setColor(new Color(150, 207, 254));
	 	    g2.drawRoundRect(0,-arrow.getHeight(),getWidth()-1,getHeight()+arrow.getHeight()-1,10,10);
	 	   }
	 	   /**
	 	    * 重载createScroller方法 绘制下拉框的滚动条
	 	    */
//	 	  protected JScrollPane createScroller() {
//	 		    JScrollPane sp = new JScrollPane(list,
//	 		      ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//	 		      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//	 		    sp.setHorizontalScrollBar(null);
//	 		    return sp;
//	 		   }
	    }
	 //////////////////////////////////////////////
	 
	}
