package com.siiruo.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Date;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.siiruo.beans.Video;
import com.siiruo.controller.LoadTask;
import com.siiruo.dao.DaoStaticInstance;
import com.siiruo.scomponent.SMenuItem;
import com.siiruo.util.ColorUtil;
import com.siiruo.util.ConstantUtil;
import com.siiruo.util.DateUtil;
import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.views.ControlPanel;
import com.siiruo.views.LeftMenuPanel;
import com.siiruo.views.OverLayer;
import com.siiruo.views.StaticPanel;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 弹出式文件菜单项的鼠标监听类
 * @author SIIRUO
 * @version 1.0
 */
public class SMenuItemListener  implements MouseListener {
	/**
	 * menuItem 弹出式文件菜单项
	 */
	private SMenuItem menuItem;
	/**
	 * Constructor
	 * @param menuItem 弹出式文件菜单项
	 */
	public SMenuItemListener(SMenuItem menuItem) {
		super();
		this.menuItem = menuItem;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		LeftMenuPanel.getFilePopupMenu().setVisible(false);
		String command=menuItem.getActionCommand();
		switch(command){
		case"open":{
			Thread open=new Thread(new OpenFile());
			open.start();
		}
			break;
		case"otherSave":
			break;
		case"close":{
			ControlPanel cp=StaticPanel.getControlPanel();
			cp.getEmbeddedMediaPlayer().stop();
			cp.getTimeInfoLabel().setText("0:0:0/0:0:0");
			cp.getProgressBar().setValue(0);
		}
			break;
		default:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		menuItem.setBackground(ColorUtil.MENUITEM_ENTER_BACKGROUND_COLOR);
		menuItem.setForeground(ColorUtil.MENUITEM_ENTER_FOREGROUND_COLOR);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		menuItem.setBackground(ColorUtil.MENUITEM_BACKGROUND_COLOR);
		menuItem.setForeground(ColorUtil.MENUITEM_FOREGROUND_COLOR);

	}
	/**
	 * 打开文件的内部类
	 * 实现了Runnable，因此它是线程任务类，完成文件选择框的显示，以及发布视频信息通知
	 * @author SIIRUO
	 * @version 1.0
	 */
	class OpenFile implements Runnable{
		@Override
		public void run() {
			JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES ); //文件夹和文件都显示 
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                "请选择视频文件", ConstantUtil.VIDEO_FILE_TYPE);//文件名过滤器
	        jfc.setFileFilter(filter);
	       jfc.showDialog(new JLabel(), "确定"); //确认项
	        File file=jfc.getSelectedFile(); //获得选择的文件
	        if(file==null){
	        	System.out.println("未选择任何文件");
	        }else {
	        	if(file.isDirectory()){  
	        		System.out.println("文件夹:"+file.getAbsolutePath());  
	        	}else if(file.isFile()){  
	        		new MediaDo().doWork(file);
	        	}   		
	        }
			
		}
		/**
		 * 替换路径
		 * @param filePath 路径字符串
		 * @param oldSeparator 旧字符
		 * @param newSeparator 新字符
		 * @return
		 */
		public String openFile(String filePath,String oldSeparator,String newSeparator){
			return filePath.replaceAll(oldSeparator,newSeparator);
		}
	}
}
