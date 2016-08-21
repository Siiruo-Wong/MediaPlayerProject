package com.siiruo.controller;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import com.siiruo.views.MainWindow;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class LoadTask implements Runnable , Observer{
	/**
	 * frame 主窗口
	 * task 视频播放线程类对象
	 */
	private static MainWindow frame;
	private ThreadTask task;
	private static LoadTask loadTask;
	@Override
	public void run() {
		/**
		 * 跨平台
		 */
		try {
			if (RuntimeUtil.isMac()) {
				NativeLibrary.addSearchPath(
						RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib"
						);
			}else if (RuntimeUtil.isWindows()) {
				//指定VLC内核地址
				NativeLibrary.addSearchPath(
						RuntimeUtil.getLibVlcLibraryName(), "D:\\setUpLocation\\VLCPlayer\\VLC"
						);
			}else if (RuntimeUtil.isNix()) {
				NativeLibrary.addSearchPath(
						RuntimeUtil.getLibVlcLibraryName(), "/home/linux/vlc/install/lib"
						);
			}
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
			 frame =MainWindow.getMainWindow();
//			 frame = new MainWindow();
			 frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 接收主题发来的数据
	 */
	@Override
	public void update(Observable o, Object arg) {
		String videoPath=(String) arg;
		/**
		 * 如果task不为空，则一定要终止之前的视频线程，
		 * 不然会导致视频播放错误，尤其体现在视频进度信息重复现象。
		 */
		if(task!=null){
			task.stopSwingWorker();
			task.setStop();
			task=null;
		}
		task=new ThreadTask(frame,videoPath);
		EventQueue.invokeLater(task);	
	}
	/**
	 * 获得单例,该处的单例很重要，不然即使update()做出了上述处理，但由于不在一个线程内，
	 * 仍然会导致视频播放在后台重复进行，尤其体现在视频进度信息重复现象。
	 * @return
	 */
	public static synchronized LoadTask getLoadTask(){
		if(loadTask==null){
			loadTask=new LoadTask();
		}
		return  loadTask;
	}
}