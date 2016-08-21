package com.siiruo.test;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import com.siiruo.util.ImageIconUtil;

public class SListCellRender implements ListCellRenderer<ItemPanel> {
	private ItemPanel itemPanelCell=new ItemPanel();
	private Border focusBorder=BorderFactory.createLineBorder(Color.GREEN, 2);
	private Border emptyBorder=BorderFactory.createEmptyBorder(2, 2, 2, 2);

	@Override
	public Component getListCellRendererComponent(JList<? extends ItemPanel> list, ItemPanel value, int index, boolean isSelected,
			boolean cellHasFocus) {
		itemPanelCell.setOpaque(true);
		if(isSelected){
			itemPanelCell.setBackground(Color.BLUE);
			itemPanelCell.getLblItem().setForeground(Color.WHITE);
			itemPanelCell.getBtnNewButton().setIcon(ImageIconUtil.ICON_MUTE);
			//itemPanelCell.getScrollPane().setVisible(true);
		}else{
			itemPanelCell.setBackground(list.getBackground());
			itemPanelCell.setForeground(list.getForeground());
			itemPanelCell.getLblItem().setForeground(Color.BLACK);
			itemPanelCell.getBtnNewButton().setIcon(ImageIconUtil.ICON_VOLUME);
			//itemPanelCell.getScrollPane().setVisible(false);
		}
		itemPanelCell.setBorder(cellHasFocus? focusBorder:emptyBorder);
		return itemPanelCell;
	}

}
