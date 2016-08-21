package com.siiruo.scomponent;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

/**
 * 重新实现了ButtonGroup 具体原因参考文章：Java技巧: 推动JButtonGroup
 * 
 * @author SIIRUO
 * @version 1.0
 */
public class SButtonGroup extends ButtonGroup {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1505859730392945632L;
	/**
	 * selectedButton 被选中的按钮的引用
	 */
	private AbstractButton selectedButton;

	/**
	 * 覆盖父方法 若按钮为空或者不在当前按钮组的话，则直接结束函数 否则 调用父类的add函数，同时判断当前按钮是否被选中
	 * 若被选中，则将selectedButton属性设置为当前按钮
	 */
	@Override
	public void add(AbstractButton button) {
		if (button == null || buttons.contains(button))
			return;
		super.add(button);
		if (getSelection() == button.getModel()) {
			selectedButton = button;
		}
		button.getModel().setGroup(this);
	}

	/**
	 * 新定义的添加方法 将一个按钮数组直接添加入当前buttonGroup组
	 * 
	 * @param buttons
	 */
	public void add(AbstractButton[] buttons) {
		// TODO Auto-generated method stub
		if (buttons == null)
			return;
		for (int i = 0; i < buttons.length; i++) {
			add(buttons[i]);
			buttons[i].getModel().setGroup(this);
		}
	}

	/**
	 * 覆盖父类的remove方法 如果当前按钮是被选中的按钮则先将其注销，然后调用父类方法进行移除
	 */
	@Override
	public void remove(AbstractButton button) {
		// TODO Auto-generated method stub
		if (button != null) {
			if (selectedButton == button)
				selectedButton = null;
			super.remove(button);
		}

	}

	/**
	 * 新定义的移除方法 将一个按钮数组依次从当前buttonGroup中移除
	 * 
	 * @param buttons
	 */
	public void remove(AbstractButton[] buttons) {
		// TODO Auto-generated method stub
		if (buttons == null)
			return;
		for (int i = 0; i < buttons.length; i++) {
			super.remove(buttons[i]);
		}

	}

	/**
	 * 新定义的选择性方法 该函数弥补了ButtonGroup的不足：可能将组外的按钮设置为选中状态
	 * 如果当前按钮为空或者不包含在当前ButtonGroup中， 则直接调用义父类函数setSelected，同时判断当前按钮是否为选中按钮
	 * 
	 * @param button
	 * @param b
	 */

	public void setSelected(AbstractButton button, boolean b) {
		// TODO Auto-generated method stub
		if (button != null && buttons.contains(button)) {
			setSelected(button.getModel(), b);
			if (getSelection() == button.getModel())
				selectedButton = button;
		}
	}

	/**
	 * 覆盖父类setSelected函数 首先判断判断当前按钮是否包含在当前ButtonGroup中
	 */
	@Override
	public void setSelected(ButtonModel m, boolean b) {
		// TODO Auto-generated method stub
		AbstractButton button = getButton(m);
		if (buttons.contains(button)) {
			super.setSelected(m, b);
		}
	}

	/**
	 * 新定义的函数 返回特定按钮的引用 该函数为用户编程提供很大的方便*****五星
	 * 
	 * @param m
	 * @return
	 */
	public AbstractButton getButton(ButtonModel m) {
		Iterator<AbstractButton> it = buttons.iterator();
		while (it.hasNext()) {
			AbstractButton button = it.next();
			if (m == button.getModel())
				return button;
		}
		return null;
	}

	/**
	 * 返回器
	 * 
	 * @return
	 */
	public AbstractButton getSelectedButton() {
		return this.selectedButton;
	}

	/**
	 * 判断当前按钮是否被选中
	 * 
	 * @param button
	 * @return
	 */
	public boolean isSelected(AbstractButton button) {
		// TODO Auto-generated method stub
		return button == selectedButton;
	}

	/**
	 * 判断当前ButtonGroup中是否包含当前按钮
	 * 
	 * @param button
	 * @return
	 */
	public boolean contains(AbstractButton button) {
		return buttons.contains(button);
	}

	/**
	 * 返回当前ButtonGroup中所有按钮的引用 该函数为用户编程提供很大的方便*****五星
	 * 
	 * @return
	 */
	public List getButtons() {
		return (List) Collections.unmodifiableList(buttons);
	}
}
