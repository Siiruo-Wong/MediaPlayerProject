package com.siiruo.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.siiruo.util.ImageIconUtil;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class testFrame extends JFrame {
	private final static int NUMBERS=4;
	private DefaultListModel<ItemPanel> listModel=new DefaultListModel<ItemPanel>();
	private JList<ItemPanel> list=new JList<ItemPanel>(listModel);
	private ListCellRenderer<ItemPanel> render=new SListCellRender();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testFrame frame = new testFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 447);
		getContentPane().setLayout(new BorderLayout(0, 0));
		for(int i=0;i<NUMBERS;i++){
			listModel.addElement(new ItemPanel());
		}
		list.setCellRenderer(render);
		list.setSelectionMode(0);
		list.setSelectedIndex(0);
		getContentPane().add(list, BorderLayout.CENTER);
		list.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				ItemPanel selectValue=list.getSelectedValue();	
				selectValue.getScrollPane().setVisible(false);
//				selectValue.getScrollPane().setRequestFocusEnabled(true);
//				selectValue.getScrollPane().setFocusable(true);
			}
		});
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ItemPanel selectValue=list.getSelectedValue();	
				selectValue.getScrollPane().setVisible(false);
				if(e.getClickCount()==2){
					System.out.println("µã»÷Á½´Î");
				}
			}

		
		});
	}

}
