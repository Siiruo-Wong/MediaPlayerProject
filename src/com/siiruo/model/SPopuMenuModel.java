package com.siiruo.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

import com.siiruo.beans.Collection;
import com.siiruo.beans.History;
import com.siiruo.beans.Video;
import com.siiruo.dao.CollectionDao;
import com.siiruo.dao.CollectionTreeDao;
import com.siiruo.dao.DaoStaticInstance;
import com.siiruo.dao.HistoryDao;
import com.siiruo.dao.HistoryTreeDao;
import com.siiruo.dao.VideoDao;
import com.siiruo.util.TreeAndTreeNodeUtil;

public class SPopuMenuModel {
	private static JPopupMenu historyPopupMenu;
	private static JPopupMenu historyMenuPopupMenu;
	private static JPopupMenu historyItemPopupMenu;
	private static JPopupMenu collectionPopupMenu;
	private static JPopupMenu collectionMenuPopupMenu;
	private static JPopupMenu collectionItemPopupMenu;
	private static VideoDao videoDao=DaoStaticInstance.getVideoDao();
	private static HistoryDao historyDao=DaoStaticInstance.getHistoryDao();
	private static CollectionDao collectionDao=DaoStaticInstance.getCollectionDao();
	private static HistoryTreeDao historyTreeDao=DaoStaticInstance.getHistoryTreeDao();
	private static CollectionTreeDao collectionTreeDao=DaoStaticInstance.getCollectionTreeDao();
	private static JTree historyTree=TreeAndTreeNodeUtil.getHistoryTree();
	private static JTree collectionTree=TreeAndTreeNodeUtil.getCollectionTree();
	/**
	 * 在播放记录页面弹出选项菜单
	 * @return
	 */
	public static synchronized JPopupMenu getHistoryPopupMenu() {
		if (historyPopupMenu == null) {
			historyPopupMenu = new JPopupMenu();
			historyPopupMenu.setLightWeightPopupEnabled(false);
			JMenuItem selectMenuItem = new JMenuItem("选择");
			historyPopupMenu.add(selectMenuItem);
		}
		return historyPopupMenu;
	}

	/**
	 * 在播放记录页面中的具体项弹出选项菜单
	 * @return
	 */
	public static synchronized JPopupMenu getHistoryItemPopupMenu() {
			historyItemPopupMenu = new JPopupMenu();
			historyItemPopupMenu.setLightWeightPopupEnabled(false);
			JMenuItem removeMenuItem = new JMenuItem("删除");
			removeMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					//获得历史记录树被选择的条目
					SiiruoTreeNode node=(SiiruoTreeNode) historyTree.getLastSelectedPathComponent();
					SiiruoTreeNode parent=(SiiruoTreeNode) node.getParent();
					String parentName=parent.getNameLabel().getText();
					String nodeName=node.getNameLabel().getText();
					new Thread(new Runnable() {
						@Override
						public void run() {
							historyTreeDao.remove(node);
							historyDao.remove(parentName, nodeName);
							Video video=videoDao.getVideo(nodeName);
							//不在播放记录中显示而不是删除
							video.setVisible(false);
							videoDao.update(video);
						}
					}).start();

				}	
			});
			JMenu addCllectionMenu=new JMenu("添加到");
			List<Collection> collections=collectionDao.getCollections();
			Iterator<Collection> iterator=collections.iterator();
			while(iterator.hasNext()){
				JMenuItem tempItem=new JMenuItem(iterator.next().getName());
				tempItem.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						//获得历史记录树被选择的条目
						//String selectedItem=TreeAndTreeNodeUtil.getSelectItem(TreeAndTreeNodeUtil.getHistoryTree());
						SiiruoTreeNode node=(SiiruoTreeNode) historyTree.getLastSelectedPathComponent();
						String selectedVideoName=node.getNameLabel().getText();
						//获得被点击的条目
						String selectedCollectionName=tempItem.getText();
						new Thread(new Runnable() {
							@Override
							public void run() {
								Video video=videoDao.getVideo(selectedVideoName);
								if(video!=null){
									video.setCollection(new Collection(selectedCollectionName));
									collectionTreeDao.add(selectedCollectionName, video);
									collectionDao.add(selectedCollectionName, selectedVideoName);
									videoDao.update(video);
								}
								
							}
						}).start();
					}	
				});
				addCllectionMenu.add(tempItem);
			}
			historyItemPopupMenu.add(addCllectionMenu);
			historyItemPopupMenu.add(removeMenuItem);
			return historyItemPopupMenu;
	}

	/**
	 * 在收藏夹页面弹出选项菜单
	 * @return
	 */
	public static synchronized JPopupMenu getCollectionPopupMenu() {
		if(collectionPopupMenu==null){
			collectionPopupMenu=new JPopupMenu();
			collectionPopupMenu.setLightWeightPopupEnabled(false);
			JMenuItem createCollectionMenuItem = new JMenuItem("创建收藏夹");
			createCollectionMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
				//	String inputValue = JOptionPane.showInputDialog("请输入新收藏夹的名称：","新建收藏夹"); 
					String inputValue = (String) JOptionPane.showInputDialog(null,"请输入新收藏夹的名称：","新建收藏夹",JOptionPane.INFORMATION_MESSAGE,null,null,"新建收藏夹"); 
					if(inputValue==null)return;
					String collectionName=inputValue.replaceAll("\\s*", "");//去除所有的空白字符
					if(collectionName==null||collectionName.isEmpty()) return;
					new Thread(new Runnable() {
						@Override
						public void run() {
							collectionTreeDao.create(collectionName);
							collectionDao.create(collectionName);	
						}
					}).start();
				}
				
			});
			collectionPopupMenu.add(createCollectionMenuItem);
		}
		return collectionPopupMenu;
	}

	/**
	 * 在收藏夹页面具体项弹出选项菜单
	 * @return
	 */
	public static synchronized JPopupMenu getCollectionItemPopupMenu() {
		collectionItemPopupMenu=new JPopupMenu();
		collectionItemPopupMenu.setLightWeightPopupEnabled(false);
		JMenuItem removeMenuItem = new JMenuItem("删除");
		removeMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//获得历史记录树被选择的条目
				SiiruoTreeNode node=(SiiruoTreeNode) collectionTree.getLastSelectedPathComponent();
				SiiruoTreeNode parent=(SiiruoTreeNode) node.getParent();
				String parentName=parent.getNameLabel().getText();
				String nodeName=node.getNameLabel().getText();
				new Thread(new Runnable() {
					@Override
					public void run() {
						collectionTreeDao.remove(node);
						collectionDao.remove(parentName, nodeName);
						Video video=videoDao.getVideo(nodeName);
						//将视频的收藏夹设置为null而不是删除
						video.setCollection(null);
						videoDao.update(video);	
					}
				}).start();
			}
			
		});
		JMenu addCllectionMenu=new JMenu("添加到");
		List<Collection> histories=collectionDao.getCollections();
		Iterator<Collection> iterator=histories.iterator();
		while(iterator.hasNext()){
			JMenuItem tempItem=new JMenuItem(iterator.next().getName());
			tempItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					SiiruoTreeNode node=(SiiruoTreeNode) collectionTree.getLastSelectedPathComponent();
					String selectedVideoName=node.getNameLabel().getText();
					//获得被点击的条目
					String selectedCollectionName=tempItem.getText();
					new Thread(new Runnable() {
						@Override
						public void run() {
							Video video=videoDao.getVideo(selectedVideoName);
							if(video!=null){
								video.setCollection(new Collection(selectedCollectionName));								
								collectionTreeDao.add(selectedCollectionName, video);
								collectionDao.add(selectedCollectionName, selectedVideoName);
								videoDao.update(video);
							}
							
						}
					}).start();
				}
				
			});
			addCllectionMenu.add(tempItem);
		}
		collectionItemPopupMenu.add(addCllectionMenu);
		collectionItemPopupMenu.add(removeMenuItem);
		return collectionItemPopupMenu;
	}
/**
 * 在播放记录页面中的直接子项弹出选项菜单
 * @return
 */
	public static JPopupMenu getHistoryMenuPopupMenu() {
		if(historyMenuPopupMenu==null){
			historyMenuPopupMenu=new JPopupMenu();
			historyMenuPopupMenu.setLightWeightPopupEnabled(false);
			JMenuItem clearHistoriesMenuItem=new JMenuItem("清空播放记录");
			clearHistoriesMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					SiiruoTreeNode node=(SiiruoTreeNode) historyTree.getLastSelectedPathComponent();
					if(node==null) return;
					String nodeName=node.getNameLabel().getText();
					new Thread(new Runnable() {
						@Override
						public void run() {
							historyTreeDao.clear(node);
							History history=historyDao.getHistory(nodeName);
							List<Video> videos=history.getVideos();
							Iterator<Video> it=videos.iterator();
							String videoName;
							Video video;
							while(it.hasNext()){
								videoName=it.next().getName();
								video=videoDao.getVideo(videoName);
								video.setVisible(false);
								videoDao.update(video);
							}
							historyDao.clear(nodeName);
						}
					}).start();
				}
				
			});
			historyMenuPopupMenu.add(clearHistoriesMenuItem);
		}
		return historyMenuPopupMenu;
	}
/**
 *  在收藏夹页面中的直接子项弹出选项菜单
 * @return
 */
	public static JPopupMenu getCollectionMenuPopupMenu() {
		if(collectionMenuPopupMenu==null){
			collectionMenuPopupMenu=new JPopupMenu();
			collectionMenuPopupMenu.setLightWeightPopupEnabled(false);
			JMenuItem clearCollectionsMenuItem=new JMenuItem("清空收藏夹");
			clearCollectionsMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					SiiruoTreeNode node=(SiiruoTreeNode) collectionTree.getLastSelectedPathComponent();
					new Thread(new Runnable() {
						@Override
						public void run() {
							Enumeration<SiiruoTreeNode> children=node.children();
							SiiruoTreeNode child;
							Video video;
							while(children.hasMoreElements()){
								child=children.nextElement();
								video=videoDao.getVideo(child.getNameLabel().getText());
								video.setCollection(null);
								videoDao.update(video);
							}
							collectionTreeDao.clear(node);
							collectionDao.clear(node.getNameLabel().getText());
						}
					}).start();
				}
				
			});
			JMenuItem removeCollectionsMenuItem=new JMenuItem("删除收藏夹");
			removeCollectionsMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					SiiruoTreeNode node=(SiiruoTreeNode) collectionTree.getLastSelectedPathComponent();
					new Thread(new Runnable() {
						@Override
						public void run() {
							Enumeration<SiiruoTreeNode> children=node.children();
							SiiruoTreeNode child;
							Video video;
							while(children.hasMoreElements()){
								child=children.nextElement();
								video=videoDao.getVideo(child.getNameLabel().getText());
								video.setCollection(null);
								videoDao.update(video);
							}
							collectionTreeDao.remove(node);
							collectionDao.remove(node.getNameLabel().getText());
						}
					}).start();
				}
				
			});
			collectionMenuPopupMenu.add(clearCollectionsMenuItem);
			collectionMenuPopupMenu.add(removeCollectionsMenuItem);
		}
		return collectionMenuPopupMenu;
	}
}
