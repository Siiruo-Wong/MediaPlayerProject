package com.siiruo.scomponent;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import com.siiruo.ui.IComboBoxUI;
import com.siiruo.util.ColorUtil;
/**
 * 1.重载JComboBox 并且设置面板透明 设置renderer 以及设置 ui
 * @author SIIRUO
 * @version 1.0
 */
public class IComboBox<T> extends JComboBox<T>{
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 3785279877457046913L;
	private ImageIcon icon;
	private ImageIcon rolloverIcon;
	public IComboBox(){
	  super();
	  init();
	 }
//	public IComboBox(ImageIcon icon, ImageIcon rolloverIcon,boolean boundsLight){
//		  super();
//		  this.icon=icon;
//		  this.rolloverIcon=rolloverIcon;
//		  init();
//		 
//	}
	 public IComboBox(ComboBoxModel<T> model){
	  super(model);
	  init();
	 }
	 public IComboBox(T[] items){
	  super(items);
	  init();
	 }
	 public IComboBox(Vector<T> items){
	  super(items);
	  init();
	 }
	private void init(){
	  setOpaque(false);
	  //setUI(new IComboBoxUI(icon,rolloverIcon,true));
	  setUI(new IComboBoxUI());
	  setRenderer(new IComboBoxRenderer());
	  setBackground(ColorUtil.COMBOBOX_COLOR);
	 }
	 public Dimension getPreferredSize(){
	  return super.getPreferredSize();
	 }
}
