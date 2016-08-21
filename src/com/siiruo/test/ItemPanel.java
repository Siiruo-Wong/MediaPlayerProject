package com.siiruo.test;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import com.siiruo.ui.S_HButtonUI;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;

public class ItemPanel extends JPanel {
	private JPanel panel;
	private JList list_1;
	private JButton btnNewButton_1;
	private JLabel lblItem_1;
	private JScrollPane scrollPane;

	public JLabel getLblItem() {
		return lblItem_1;
	}

	public void setLblItem(JLabel lblItem) {
		this.lblItem_1 = lblItem;
	}

	public JButton getBtnNewButton() {
		return btnNewButton_1;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton_1 = btnNewButton;
	}

	/**
	 * Create the panel.
	 */
	public ItemPanel() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 100, 0));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblItem_1 = new JLabel("Item");
		panel.add(lblItem_1);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(ItemPanel.class.getResource("/com/siiruo/control/images/volume.png")));
		panel.add(btnNewButton_1, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"dwjhf", "efhehekt", "wbfhbhrejkf", "fnjehfkr", "nkjefkr", "fnkjt", "fnikjtgkl", "fnkrjmtk", "tgyt", "tyu", "y", "tru", "u", "ytu", "uy", "iy", "ku", "iert", "w", "r4t", "df"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list_1);

	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JList getList() {
		return list_1;
	}

	public void setList(JList list) {
		this.list_1 = list;
	}

}
