package com.siiruo.util;

import java.awt.Color;
import java.awt.SystemColor;
/**
 * 颜色常量类
 * @author SIIRUO
 * @version 1.0
 */
public class ColorUtil {

	/**
	 * COMBOBOX_COLOR 表示组合框中文本区域的背景颜色
	 */
	public static final Color COMBOBOX_COLOR = Color.BLUE;
	/**
	 * COMBOBOX_BOUNDS_COLOR 表示当鼠标经过组合框的下拉菜单的条目时，而呈现的背景颜色
	 */
	public static final Color COMBOBOX_BOUNDS_COLOR = Color.GREEN;
	/**
	 * COMBOBOX_ARROW_BACKGROUND_COLOR 组合框按钮背景颜色
	 */
	public static final Color COMBOBOX_ARROW_BACKGROUND_COLOR = Color.YELLOW;
	/**
	 * COMBOBOX_ARROW_FOCUS_COLOR 组合框聚焦时的外围线框的颜色
	 */
	public static final Color COMBOBOX_ARROW_FOCUS_COLOR = Color.ORANGE;
	/**
	 *进入 菜单项时的背景色
	 */
	public static final Color MENUITEM_ENTER_BACKGROUND_COLOR = SystemColor.activeCaption;
	/**
	 *进入 菜单项时的前景色
	 */
	public static final Color MENUITEM_ENTER_FOREGROUND_COLOR = Color.WHITE;
	/**
	 * 菜单项默认的前景色
	 */
	public static final Color MENUITEM_FOREGROUND_COLOR = Color.BLACK;
	/**
	 *菜单项默认的背景色
	 */
	public static final Color MENUITEM_BACKGROUND_COLOR = SystemColor.control;
	/**
	 * 树结构节点被选中时的前景色
	 */
	public static final Color TREE_SELECTED_FOREGROUND_COLOR = Color.WHITE;
	/**
	 * 树结构节点未被选中时的前景色(默认颜色)
	 */
	public static final Color TREE_UNSELECTED_FOREGROUND_COLOR =Color.BLACK;
	/**
	 * 鼠标进入节点时的背景色
	 */
	public static final Color TREE_ENTER_ROOT_BACKGROUND_COLOR = Color.BLUE;
	public static final Color TREE_ENTER_ITEM_BACKGROUND_COLOR = Color.BLUE;
	/**
	 * 第二级节点的背景色
	 */
	public static final Color TREE_ROOT_BACKGROUND_COLOR = new Color(0,255,0,30);
	/**
	 * 第三级节点的背景色
	 */
	public static final Color TREE_ITEM_BACKGROUND_COLOR = new Color(0,0,255,30);
	
	
}
