package com.siiruo.scomponent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class SPopupMenu extends JPopupMenu {
	
	public void addMenuItems(JMenuItem[] menuItems) {
		if (menuItems == null || menuItems.length == 0)
			return;
		int i = 0;
		int len = menuItems.length;
		for (; i < len; i++) {
			this.add(menuItems[i]);
		}
	}

	

}
