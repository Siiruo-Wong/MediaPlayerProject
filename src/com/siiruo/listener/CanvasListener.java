package com.siiruo.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.siiruo.controller.OverLayerTimer;
import com.siiruo.controller.ShowTimer;
import com.siiruo.controller.TimingTask;
import com.siiruo.util.ConstantUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.views.ControlPanel;
import com.siiruo.views.MainWindow;
import com.siiruo.views.StaticPanel;
/**
 * 画布Canvas鼠标监听类
 * @author SIIRUO
 * @version 1.0
 */
public class CanvasListener extends MouseAdapter 
							implements MouseMotionListener,MouseListener{
	/**
	 * frame 父窗口
	 */
	private MainWindow frame;
	private Logger logger=LoggerUtil.getLogger(CanvasListener.class.getName());
	/**
	 * Constructor
	 */
	public CanvasListener(){
		
	}
	/**
	 * Constructor
	 * @param frame
	 */
	public CanvasListener(MainWindow frame){
		this.frame=frame;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	
		switch(e.getClickCount()){
		case 2:
		{	
			// 双击最大化或者恢复窗口大小
			MaxToVideo.getMaxToVideo().doMax(frame, StaticPanel.getControlPanel().getMaxLabel());
			//单击暂停或者播放视频
			if(MediaPlayerUtil.getMediaPlayer().isPlaying()){
				MediaPlayerUtil.getMediaPlayer().pause();
			}else {
				MediaPlayerUtil.getMediaPlayer().play();
			}		
		}
			break;
		case 1:
		{
			//单击暂停或者播放视频
			if(MediaPlayerUtil.getMediaPlayer().isPlaying()){
				MediaPlayerUtil.getMediaPlayer().pause();
			}else {
				MediaPlayerUtil.getMediaPlayer().play();
			}	
		}
			break;
		default :
			break;
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		frame.requestFocusInWindow();
		super.mousePressed(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseReleased(e);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Canvas******mouseEntered");
		MediaPlayerUtil.setEnter(true);
		MediaPlayerUtil.setExit(false);
	//	MediaPlayerUtil.getOverLayer().setVisible(true);
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Canvas******mouseExited");
		MediaPlayerUtil.setEnter(false);
		MediaPlayerUtil.setExit(true);
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		super.mouseWheelMoved(e);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseDragged(e);
	}
	/**
	 * 一旦鼠标移动将Controlpanel设置为可视状态
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("Canvas******mouseMoved");
		MediaPlayerUtil.getOverLayer().setVisible(true);
	}
	
}
