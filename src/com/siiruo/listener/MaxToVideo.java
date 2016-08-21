package com.siiruo.listener;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.siiruo.controller.ShowTimer;
import com.siiruo.controller.TimingTask;
import com.siiruo.util.ConstantUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.views.MainWindow;
import com.siiruo.views.StaticPanel;
/**
 * 最大化视频播放器窗口工具单例类
 * @author SIIRUO
 * @version 1.0
 */
public class MaxToVideo {
	/**
	 * maxToVideo 单例
	 * frame  父窗口
	 * maxLabel 最大化标签
	 * timer 定时器（用于定时显示和退出控制面板）
	 * timerTask 定时任务对象
	 */
	private static MaxToVideo maxToVideo;
	private  MainWindow frame;
	private JLabel maxLabel;
	private ShowTimer timer;
	private TimingTask timerTask;
	private Logger logger=LoggerUtil.getLogger(MaxToVideo.class.getName());
	/**
	 * Constructor
	 */
	private  MaxToVideo(){
		timer=ShowTimer.getTimer();//初始化定时器
	}
	/**
	 * 最大化媒体播放器窗口
	 * @param frame 父窗口
	 * @param maxLabel 最大化标签
	 */
	public void doMax(MainWindow frame,JLabel maxLabel){
		this.maxLabel=maxLabel;
		doMax(frame);
	}
	/**
	 * 最大化媒体播放器窗口
	 * @param frame 父窗口
	 */
	public void doMax(MainWindow frame){
		this.frame=frame;
		/**
		 * 注意：传递给定时器timer的任务对象timerTask每次都必须是新的对象
		 * 即：即使该任务还没有开始执行，但是也要把它清除掉
		 * 否则或抛出llegalStateException：Task already scheduled or cancelled.异常
		 */
		
		if(frame.getExtendedState()==JFrame.MAXIMIZED_BOTH){
			maxOrNormal(JFrame.NORMAL,ImageIconUtil.ICON_AMPLIFY, true,new LineBorder(new Color(210, 180, 140), 1, true));
//			if(timerTask!=null){
//				timerTask.cancel();
//			}
		
		}else if(frame.getExtendedState()==JFrame.NORMAL){
			maxOrNormal(JFrame.MAXIMIZED_BOTH,ImageIconUtil.ICON_NARROW, false,new LineBorder(Color.BLACK, 1, true));
			
//			if(timer==null){
//				logger.info("timer has been recycled by GC...");
//				timer=ShowTimer.getTimer();
//			}
//			/**
//			 * 传递false使得controlPanel不显示
//			 */
//			timerTask=TimingTask.getTimerTask(false);	
//			//开始调度任务
//			timer.schedule(timerTask, ConstantUtil.TIMER_GAP,ConstantUtil.TIMER_GAP);
		}
	}
	/**
	 * 为最大化窗口和正常化窗口设置响应的属性，
	 * @param intMark 将当前窗口设置为响应的类型
	 * @param icon 同步最大化最小化图标
	 * @param isVisible controlPanel是否可见
	 * @param border 设置边缘
	 */
	public void maxOrNormal(int  intMark,ImageIcon icon, boolean isVisible,Border border){
		maxLabel.setIcon(icon);
		MainWindow.getFramePanel().getHeadPanel().setVisible(isVisible);
		StaticPanel.getMenuPanel().setVisible(isVisible);
		StaticPanel.getSidePanel().setVisible(isVisible);
		StaticPanel.getBottomPanel().setVisible(isVisible);
		frame.setExtendedState(intMark);
		if(intMark==JFrame.MAXIMIZED_BOTH){
			MediaPlayerUtil.getOverLayer().setSize(frame.getWidth(), frame.getHeight());
		}
		MainWindow.getContain().setBorder(border);
}
	/**
	 * 静态方法
	 * 返回单例对象
	 * @return
	 */
public static MaxToVideo getMaxToVideo(){
	if(maxToVideo==null){
		maxToVideo=new MaxToVideo();
	}
	return maxToVideo;
}
	
}
