package com.siiruo.scomponent;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.siiruo.util.ColorUtil;
import com.siiruo.util.FontUtil;
/**
 * 自定义组合框的下拉菜单的绘制器
 * @author SIIRUO
 * @version 1.0
 * @param <T>
 */
public class IComboBoxRenderer<T> implements ListCellRenderer<T> {
	 
	 private DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

	 public IComboBoxRenderer() {
	  super();
	 }

	 public Component getListCellRendererComponent(JList list, Object value,
	   int index, boolean isSelected, boolean cellHasFocus) {

	  JLabel renderer = (JLabel)defaultCellRenderer.getListCellRendererComponent(
	    list, value, index, isSelected, cellHasFocus);
	  if(isSelected){
	   renderer.setBackground(ColorUtil.COMBOBOX_BOUNDS_COLOR);
	   renderer.setForeground(Color.WHITE);
	  }else{
	   renderer.setBackground(Color.WHITE);
	  }
	  list.setSelectionBackground(ColorUtil.COMBOBOX_COLOR);
	  list.setBorder(null);
	  renderer.setFont(FontUtil.COMBOBOX_FONT);
	  renderer.setHorizontalAlignment(JLabel.CENTER);
	  return renderer;
	 }
	}