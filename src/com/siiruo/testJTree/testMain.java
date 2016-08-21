package com.siiruo.testJTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;

public class testMain extends JFrame {

	private JPanel contentPane;
	private Logger logger=LoggerUtil.getLogger(testMain.class.getName());
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testMain frame = new testMain();
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
	public testMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(107, 142, 35));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		STreeNode root1=new STreeNode(ImageIconUtil.OPEN_ITEM,"高中同学");
		STreeNode root2=new STreeNode(ImageIconUtil.OPEN_ITEM,"初中同学");
		    
		    root1.add(new STreeNode(null,"雅君"));
		    root1.add(new STreeNode(null,"伟旭"));
		    root1.add(new STreeNode(null,"宜群"));
		    root2.add(new STreeNode(null,"彬强"));
		    root2.add(new STreeNode(null,"小强"));
		    
		    STreeNode Root=new STreeNode(null,null);//定义根节点
		    Root.add(root1);//定义二级节点
		    Root.add(root2);//定义二级节点
		    
		    final JTree tree = new JTree(Root);//定义树   
		    tree.setFont(new Font("宋体", Font.PLAIN, 12));
		    tree.setOpaque(false);
		    STreeCellRenderer render=new STreeCellRenderer();
		    
		    render.setBackgroundNonSelectionColor(new Color(0,0,0,0));
	        render.setBackgroundSelectionColor(new Color(255,255,255,250));
		    
		    tree.setCellRenderer(render); //设置单元格描述    
		    tree.setEditable(false); //设置树是否可编辑
		    tree.setUI(new STreeUI());
		    tree.setRootVisible(false);//设置树的根节点是否可视
		    tree.setToggleClickCount(1);//设置单击几次展开数节点
		 
		 DefaultTreeCellRenderer cellRenderer=(DefaultTreeCellRenderer)tree.getCellRenderer();//获取该树的Renderer
		// cellRenderer.setClosedIcon(ImageIconUtil.ICON_VOLUME);//关闭打开图标
		// cellRenderer.setOpenIcon(ImageIconUtil.ICON_VOLUME);//设置展开图标
		    
		 //测试事件
		    tree.addMouseListener(new MouseAdapter()
		    {
		     public void mouseClicked(MouseEvent e)
		     {
		      if(e.getClickCount()==2)//双击节点
		      {
		       TreePath path=tree.getSelectionPath();//获取选中节点路径
		       STreeNode node=null;
			try {
				node = (STreeNode)path.getLastPathComponent();
			} catch (java.lang.NullPointerException e1) {
				// TODO Auto-generated catch block
				logger.error("JTree node is null");
				return;
			}
		       if(node.isLeaf())//如果该节点是叶子节点
		       {
		           DefaultTreeModel model=(DefaultTreeModel)tree.getModel();//获取该树的模型
		           model.removeNodeFromParent(node);//从本树删除该节点     
		           node.setIcon(ImageIconUtil.OPEN_ITEM);//修改该节点的图片
		           node.setText("双击");//修改该节点的文本
		           tree.repaint();//重绘更新树
		           System.out.println(node.getText());
		       }
		    
		       
		      }
		     }
		    });
		    tree.addTreeExpansionListener(new TreeExpansionListener() {
				
				@Override
				public void treeExpanded(TreeExpansionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("treeExpanded");
					TreePath path=tree.getSelectionPath();//获取选中节点路径
				    STreeNode node=(STreeNode)path.getLastPathComponent();//通过路径将指针指向该节点
				    node.setIcon(ImageIconUtil.CLOSE_ITEM);
				}
				
				@Override
				public void treeCollapsed(TreeExpansionEvent event) {
					// TODO Auto-generated method stub
					System.out.println("treeCollapsed");
					TreePath path=tree.getSelectionPath();//获取选中节点路径
				    STreeNode node=(STreeNode)path.getLastPathComponent();//通过路径将指针指向该节点
				    node.setIcon(ImageIconUtil.OPEN_ITEM);
					
				}
			});
		    /**
		     *由于 不能直接在DefaultTreeCellRenderer中监听到鼠标事件，
		     *因此需要在JTree中监听，然后将需要传递的值传递给DefaultTreeCellRenderer
		     */
		    tree.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                int x = (int) e.getPoint().getX();
	                int y = (int) e.getPoint().getY();
	              //  TreePath path = tree.getPathForLocation(x, y);
	                tree.getComponentAt(x, y).repaint();
	                render.mouseRow = tree.getRowForLocation(x, y);
	              tree.repaint();
	            }
	        });
		    JScrollPane jsp=new JScrollPane(tree);
		    jsp.setOpaque(false);
		    jsp.getViewport().setOpaque(false);
		    getContentPane().add(jsp,BorderLayout.CENTER);
		    }

}
