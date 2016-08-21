package com.siiruo.util;

import java.awt.Canvas;

import org.apache.log4j.Logger;

import com.siiruo.views.OverLayer;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MediaPlayerUtil {
	private static EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private static EmbeddedMediaPlayer mediaPlayer;
	private static Canvas canvas;
	private static OverLayer overLayer;
	private static boolean enter=false;
	private static boolean exit=true;
	private static Logger logger=LoggerUtil.getLogger(MediaPlayerUtil.class.getName());
	public synchronized static EmbeddedMediaPlayerComponent getMediaPlayerComponent(){
		if(mediaPlayerComponent==null){
			mediaPlayerComponent=new EmbeddedMediaPlayerComponent();
		}
		return mediaPlayerComponent;
	}
	public synchronized static EmbeddedMediaPlayer getMediaPlayer(){
		if(mediaPlayer==null){
			mediaPlayer=getMediaPlayerComponent().getMediaPlayer();
			mediaPlayer.setOverlay(MediaPlayerUtil.getOverLayer());
			
			System.out.println("overlayer:"+mediaPlayer.getOverlay().getClass().getName());
//			mediaPlayer.getVideoDimension();
			/**
			 * 下面两行代码尤其重要
			 * 因为，VLC播放器会截获所有在当前播放窗口的鼠标事件和键盘事件
			 * 因此，只要使用下述策略才可能获得相应的鼠标和键盘事件，以至于顺利地进行VLC播放器的二次开发
			 */
			mediaPlayer.setEnableMouseInputHandling(false);
			mediaPlayer.setEnableKeyInputHandling(false);
		
		}
		return mediaPlayer;
	}
	public synchronized static Canvas getCanvas(){
		if(canvas==null){
			canvas=getMediaPlayerComponent().getVideoSurface();
		}
		return canvas;
	}
	public  synchronized static void play(String path){
		if(mediaPlayer==null){
			mediaPlayer=getMediaPlayer();
		}
		
		mediaPlayer.playMedia(path);
	}
	public  synchronized static OverLayer getOverLayer(){
		if(overLayer==null){
			overLayer=new OverLayer();
			//overLayer.setBounds(100, 100, 450, 300);
		}
		return overLayer;
	}
	public static boolean isEnter() {
		return enter;
	}
	public static void setEnter(boolean enter) {
		MediaPlayerUtil.enter = enter;
	}
	public static boolean isExit() {
		return exit;
	}
	public static void setExit(boolean exit) {
		MediaPlayerUtil.exit = exit;
	}
	
}
