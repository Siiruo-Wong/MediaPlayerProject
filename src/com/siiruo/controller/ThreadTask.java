package com.siiruo.controller;

import java.util.List;

import javax.swing.SwingWorker;

import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.views.MainWindow;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 播放视频的线程类
 * @author SIIRUO
 * @version 1.0
 */
public class ThreadTask implements Runnable{
	/**
	 * videoPath 视频路径
	 * frame 主窗口
	 * embeddedMediaPlayer 媒体播放器
	 * stop 线程终止标志
	 */
	private String videoPath;
	private  EmbeddedMediaPlayer embeddedMediaPlayer;
	private S_SwingWorker swingWorker;
	private boolean stop = false;
	/**
	 * Constructor
	 * @param frame 主窗口
	 * @param videoPath 视频路径
	 */
	public ThreadTask(MainWindow frame,String videoPath){
		this.videoPath=videoPath;
		//this.embeddedMediaPlayer= frame.getMediaPlayer();
		this.embeddedMediaPlayer= MediaPlayerUtil.getMediaPlayer();
	}
	/**
	 * 实现run方法
	 */
		@Override
		public void run() {
			try {
				if(stop) return;
				 embeddedMediaPlayer.playMedia(videoPath); 
				 swingWorker =new S_SwingWorker(embeddedMediaPlayer);
				 swingWorker.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * 设置终止标志，
		 * 采用这种方式的原因是
		 * 1、直接使用stop()方法来终止线程是不安全的，就像突然关闭计算机电源，而不是按正常程序关机一样，可能会产生不可预料的结果。
		 * 2、直接使用interrupt()方法，可能会导致sleep()方法将抛出一个InterruptedException。当有这个异常发生时，
		 * 	    倘若我们设置了终断状态，则终断状态也会被清除。
		 */
	public final void setStop(){
		this.stop=true;
	}
	/**
	 * 终止SwingWorker线程，
	 */
	public void stopSwingWorker(){
		System.out.println("swingWorker:"+(swingWorker==null));
		 if(swingWorker!=null) {
			 swingWorker.cancel(true);
			 swingWorker=null;
			 }
	}
}
