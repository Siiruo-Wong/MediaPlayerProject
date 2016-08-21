package com.siiruo.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.siiruo.beans.Collection;
import com.siiruo.beans.History;
import com.siiruo.beans.Video;
import com.siiruo.controller.LoadTask;
import com.siiruo.dao.CollectionDao;
import com.siiruo.dao.DaoStaticInstance;
import com.siiruo.dao.HistoryDao;
import com.siiruo.dao.HistoryTreeDao;
import com.siiruo.dao.HistoryTreeDaoImpl;
import com.siiruo.dao.VideoDao;
import com.siiruo.model.SPopuMenuModel;
import com.siiruo.model.SiiruoTreeNode;
import com.siiruo.scomponent.SiiruoTreeCellRenderer;
import com.siiruo.thread.LoadVideos;
import com.siiruo.ui.SiiruoScrollBarUI;
import com.siiruo.ui.SiiruoTreeUI;
import com.siiruo.ui.SinaTabbedPaneUI;
import com.siiruo.util.DateUtil;
import com.siiruo.util.DimensionUtil;
import com.siiruo.util.HandlerUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.util.TreeAndTreeNodeUtil;
import com.sun.javafx.tk.quantum.OverlayWarning;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 侧边栏面板，主要显示视频信息
 * @author SIIRUO
 * @version 1.0
 */
public class SidePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3447424465334111101L;
	private static JTabbedPane tabbedPane;
	private JScrollPane historyScrollPane;
	private JScrollPane collectionScrollPane;
	SiiruoTreeNode historyRoot;
	SiiruoTreeNode collectionRoot;
	JTree historyTree;
	JTree collectionTree;
	private VideoDao videoDao;
	private CollectionDao collectionDao;
	private HistoryDao historyDao;
	private VideoInfoDialog infoDialog;
	private int row=-1;
	private SiiruoTreeNode markNode=null;
	private static Logger logger=LoggerUtil.getLogger(SidePanel.class.getName());
	/**
	 * Create the panel.
	 */
	public SidePanel() {
		setBackground(new Color(64, 224, 208));
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
			}
		});
		/////////////////////////////////////////
		videoDao=DaoStaticInstance.getVideoDao();
		historyDao=DaoStaticInstance.getHistoryDao();
		collectionDao=DaoStaticInstance.getCollectionDao();
		//初始化文件信息
		//new Thread(new LoadVideos(videoDao,historyDao,collectionDao)).start();
		new LoadVideos(videoDao,historyDao,collectionDao).run();
		 historyRoot = TreeAndTreeNodeUtil.getHistoryRoot();// 定义播放记录根节点
		 collectionRoot = TreeAndTreeNodeUtil.getCollectionRoot();// 定义收藏夹根节点
		
		initHistory(historyRoot, historyDao);// 初始化播放记录
		initCollection(collectionRoot, collectionDao);// 初始化收藏夹
		historyTree = TreeAndTreeNodeUtil.getHistoryTree();// 定义播放记录树结构
		collectionTree = TreeAndTreeNodeUtil.getCollectionTree();// 定义收藏夹树结构
		
		//collectionTree.set
		historyTree.add(SPopuMenuModel.getHistoryPopupMenu());
		historyTree.add(SPopuMenuModel.getHistoryItemPopupMenu());
		historyTree.add(SPopuMenuModel.getHistoryMenuPopupMenu());
		collectionTree.add(SPopuMenuModel.getCollectionPopupMenu());
		collectionTree.add(SPopuMenuModel.getCollectionItemPopupMenu());
		collectionTree.add(SPopuMenuModel.getCollectionMenuPopupMenu());
		initTree(historyTree);// 初始化播放记录树结构
		initTree(collectionTree);// 初始化收藏夹树结构
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBackground(new Color(105, 105, 105));
		tabbedPane.setFont(new Font("宋体", Font.PLAIN, 12));
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tabbedPane.setBorder(null);
		tabbedPane.setUI(new SinaTabbedPaneUI());
		add(tabbedPane, BorderLayout.CENTER);

		historyScrollPane = new JScrollPane(historyTree);
		historyScrollPane.setBorder(null);
		historyScrollPane.getViewport().setBorder(null);
		historyScrollPane.getViewport().setOpaque(false);
		historyScrollPane.getVerticalScrollBar().setUI(new SiiruoScrollBarUI());
		historyScrollPane.getHorizontalScrollBar().setUI(new SiiruoScrollBarUI());
		tabbedPane.addTab("\u64AD\u653E\u8BB0\u5F55", null, historyScrollPane, null);

		collectionScrollPane = new JScrollPane(collectionTree);
		collectionScrollPane.setBorder(null);
		collectionScrollPane.getViewport().setBorder(null);
		collectionScrollPane.getViewport().setOpaque(false);
		collectionScrollPane.getVerticalScrollBar().setUI(new SiiruoScrollBarUI());
		collectionScrollPane.getHorizontalScrollBar().setUI(new SiiruoScrollBarUI());
		
		
		tabbedPane.addTab("\u6536\u85CF\u76EE\u5F55", null, collectionScrollPane, null);

	}
	/**
	 * 初始化播放记录
	 * @param historyRoot
	 * @param historyDao
	 */
	public void initHistory(SiiruoTreeNode historyRoot,HistoryDao historyDao){
		List<History> historyList=historyDao.getHistories();
		int len=historyList.size();
		History history;
		SiiruoTreeNode subHisRoot;
		for(int i=0;i<len;i++){
			history=historyList.get(i);
			subHisRoot=new SiiruoTreeNode(null,null,history.getName());
			List<Video> videos=history.getVideos();
			Iterator<Video> it=videos.iterator();
			String videoName;
			Video video;
			int j=0;
			while(it.hasNext()){
				videoName=it.next().getName();
				video=videoDao.getVideo(videoName);
				if(video.isVisible()){
					subHisRoot.add(new SiiruoTreeNode(ImageIconUtil.VIDEO,Integer.toString(j+1)+". ",video.getName(),
							HandlerUtil.getStringTime(video.getTime())+"/"+HandlerUtil.getStringSize(video.getSize())));
					j++;	
				}
			}
			historyRoot.add(subHisRoot);
		}

	}
	/**
	 * 初始化收藏夹
	 * @param collectionRoot
	 * @param collectionDao
	 */
	public void initCollection(SiiruoTreeNode collectionRoot,CollectionDao collectionDao){
		List<Collection> collList=collectionDao.getCollections();
		Collection coll;
		SiiruoTreeNode subCollRoot;	
		int cLen=collList.size();
		for(int i=0;i<cLen;i++){
			coll=collList.get(i);
			 subCollRoot=new SiiruoTreeNode(null,null, coll.getName());
			List<Video> videos=coll.getVideos();
			Iterator<Video> it=videos.iterator();
			String videoName;
			Video video;
			int j=0;
			while(it.hasNext()){
				videoName = it.next().getName();
				video = videoDao.getVideo(videoName);
				subCollRoot.add(new SiiruoTreeNode(ImageIconUtil.VIDEO, Integer.toString(j + 1) + ". ", video.getName(),
						HandlerUtil.getStringTime(video.getTime()) + "/" + HandlerUtil.getStringSize(video.getSize())));
				j++;	
			}
			collectionRoot.add(subCollRoot);
		}
		    
	}
	
	/**
	 * 初始化JTree对象
	 * @param tree
	 */
	public void initTree(JTree tree){
		tree.setBackground(SystemColor.scrollbar);
		tree.setFont(new Font("宋体", Font.PLAIN, 12));
		SiiruoTreeCellRenderer render = new SiiruoTreeCellRenderer();
		tree.setCellRenderer(render); // 设置单元格描述
		tree.setEditable(false); // 设置树是否可编辑
		tree.setUI(new SiiruoTreeUI());
		tree.setRootVisible(false);// 设置树的根节点是否可视
		tree.setToggleClickCount(1);// 设置单击几次展开数节点
		tree.setTransferHandler(null);
		tree.addMouseListener(new TreeMouseListener(tree,render));
		tree.addMouseMotionListener(new MouseMotionListener() {			
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = (int) e.getPoint().getX();
				int y = (int) e.getPoint().getY();
				TreePath path = tree.getPathForLocation(x, y);
				row=render.mouseRow = tree.getRowForLocation(x, y);
				if(path!=null){
							
							SiiruoTreeNode node=(SiiruoTreeNode) path.getLastPathComponent();
							if(node!=null&&(node.getParent().getChildCount()==1||node!=markNode)&&node.getLevel()==2){
									    markNode=node;
										if(infoDialog!=null)infoDialog.dispose();
										infoDialog=new VideoInfoDialog();
										Video video=videoDao.getVideo(node.getNameLabel().getText());
										infoDialog.getNameLabel().setText(video.getName());
										infoDialog.getSizeLabel().setText(HandlerUtil.getStringSize(video.getSize()));
										infoDialog.getTimeLabel().setText(HandlerUtil.getStringTime(video.getTime()));
										infoDialog.getLastLabel().setText(video.getDate());
										infoDialog.getLocationLabel().setText(video.getPath());
										Dimension scrDim=DimensionUtil.SCREEN_SIZE;
										Point point=StaticPanel.getSidePanel().getLocationOnScreen();
										int infoX=point.x+150;
										int infoY=point.y+(row+1)*22;
										if(infoX+infoDialog.getWidth()>scrDim.width){
											infoX=point.x-infoDialog.getWidth();
										}
										if(infoY+infoDialog.getHeight()>scrDim.height){
											infoY=scrDim.height-infoDialog.getHeight();
										}
										infoDialog.setLocation(infoX,infoY);
										infoDialog.show();	
							}
							if(node.getNodeView()==null){
								if(infoDialog!=null)infoDialog.dispose();
							}
							tree.repaint();

				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				
			}
		});
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println("valueChanged");
				 SiiruoTreeNode node = (SiiruoTreeNode) tree.getLastSelectedPathComponent();
	                if (node == null)
	                    return; 
	          
			}
		});
	}
/**
 * 树的鼠标监听类
 * @author SIIRUO
 * @version 1.0
 */
class TreeMouseListener extends Observable implements MouseListener{
	private JTree tree;
	private SiiruoTreeCellRenderer render;
	private HistoryTreeDao historyTreeDao;
	TreeMouseListener(JTree tree,SiiruoTreeCellRenderer render){
		super();
		this.tree=tree;
		this.render=render;
		this.historyTreeDao=new HistoryTreeDaoImpl();
		addObserver(LoadTask.getLoadTask());
	}
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2&&row!=-1)// 双击节点
			{
				TreePath path = tree.getSelectionPath();// 获取选中节点路径
				SiiruoTreeNode node = null;
				try {
					node = (SiiruoTreeNode) path.getLastPathComponent();
				} catch (java.lang.NullPointerException e1) {
					logger.error("JTree node is null");
					return;
				}
				if (node.isLeaf())// 如果该节点是叶子节点
				{
					String name=node.getNameLabel().getText();
					Video video=videoDao.getVideo(name);
					if(video!=null){
						String videoPath=video.getPath();
						if(videoPath==null||videoPath.isEmpty()){
							logger.error("the path of the video file isn't exist...");
						}else{
							/**
			        		 * 获得媒体播放器和覆盖层，并将显示层先设置为可见状态
			        		 */
							EmbeddedMediaPlayer media=MediaPlayerUtil.getMediaPlayer();
							OverLayer overLayer=MediaPlayerUtil.getOverLayer();
							overLayer.getPromptLayer().setVisible(true);
							overLayer.getLoadLabel().setText("加载中...");
							overLayer.setVisible(true);
							/**
							 * 表示线程，如果在10秒内无响应，则提示加载失败
							 */
							Thread mark=new Thread(new Runnable() {
								@Override
								public void run() {
									try {
										Thread.sleep(10000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
								}
							});
							mark.start();
							/**
							 * 加载处理线程
							 */
							new Thread(new Runnable() {
								@Override
								public void run() {
									while(!media.isPlaying()&&mark.isAlive()){
										System.out.println("!media.isPlaying()!media.isPlaying()!media.isPlaying()!media.isPlaying()");
									}
									overLayer.getPromptLayer().setVisible(false);
									overLayer.getControlLayer().setVisible(true);
									if(!media.isPlaying()){ 
										overLayer.getLoadLabel().setText("加载失败");
										try {
											Thread.sleep(5000);
										} catch (InterruptedException e) {	
											e.printStackTrace();
										}
										return;
									}
									overLayer.getInformationLabel().setText(name);
								}
							}).start();
						
							 	setChanged();
							 	notifyObservers(videoPath);
							 	StaticPanel.getControlPanel().getPlayButton().setIcon(ImageIconUtil.ICON_PAUSE);
							 	/**
							 	 * 数据更新
							 	 */
								video.setDate(DateUtil.toStringTimeDay(new Date()));
								videoDao.update(video);
								historyDao.add("今天", name);
								historyTreeDao.add(historyRoot.getFirstChild(), video);	
								
						}
					}else{
						logger.error("The video file to be found isn't exist");
					}
					
				}
			}
//			if(row==-1){
//				MediaPlayerUtil.getMediaPlayer().enableOverlay(true);
//				MediaPlayerUtil.getMediaPlayer().getOverlay().show();
//			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@SuppressWarnings("unused")
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("mouse Relesased");
			TreePath path;// 获取选中节点路径
			//STreeNode node;
			SiiruoTreeNode node;
			if(e.isPopupTrigger()){
				if(tabbedPane.getSelectedIndex()==0){	//播放记录弹出菜单项
					//如果row=-1,则说明当前鼠标处在空白处，如果是则弹出选择框并直接退出函数
					if(row==-1){
						SPopuMenuModel.getHistoryPopupMenu().show(e.getComponent(),e.getX(),e.getY());
						return;
					}
					path = tree.getSelectionPath();// 获取选中节点路径
					//path=null则说明当前鼠标处在树上但是没有做出任何选择
					if(path==null){
						SPopuMenuModel.getHistoryPopupMenu().show(e.getComponent(),e.getX(),e.getY());
					}else {
						node = (SiiruoTreeNode) path.getLastPathComponent();
						//node!=null则说明当前有子树项被选中，下述判断选中的子树项类型
						if(node!=null){
							//判断node是否是根节点的直接子节点，否则判断是否是叶子节点
							if(node.getParent()==node.getRoot()){
								SPopuMenuModel.getHistoryMenuPopupMenu().show(e.getComponent(),e.getX(),e.getY());
							}else if(node.isLeaf()){
								SPopuMenuModel.getHistoryItemPopupMenu().show(e.getComponent(),e.getX(),e.getY());
							}
							
						}
					}
				}else if(tabbedPane.getSelectedIndex()==1){	//收藏夹弹出菜单项
					if(row==-1){
						SPopuMenuModel.getCollectionPopupMenu().show(e.getComponent(),e.getX(),e.getY());
						return;
					}
					 path = tree.getSelectionPath();
					if(path==null){
						SPopuMenuModel.getCollectionPopupMenu().show(e.getComponent(),e.getX(),e.getY());
					}else {
						node = (SiiruoTreeNode) path.getLastPathComponent();
						if(node!=null){						
							if(node.getParent()==node.getRoot()){
								SPopuMenuModel.getCollectionMenuPopupMenu().show(e.getComponent(),e.getX(),e.getY());
							}else if(node.isLeaf()){
								SPopuMenuModel.getCollectionItemPopupMenu().show(e.getComponent(),e.getX(),e.getY());
							}
						}
						
					}
					System.out.println("trigger");
				}
				
			}
			 
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(infoDialog!=null){
				infoDialog.dispose();
			}
		}
		/**
		 * 判断节点node下是否包含值为name的子节点
		 * @param node
		 * @param name
		 * @return
		 */
		public boolean isExistVideo(TreeNode node,String name){
			SiiruoTreeNode snode=(SiiruoTreeNode) node;
			SiiruoTreeNode temp;
			Enumeration<SiiruoTreeNode> enumeration=node.children();
			while(enumeration.hasMoreElements()){
				temp=enumeration.nextElement();
				if(temp.getNameLabel().getText().equals(name)) return true;
			}		
			return false;
		}
	}
}
