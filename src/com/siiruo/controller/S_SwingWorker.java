package com.siiruo.controller;

import javax.swing.SwingWorker;

import com.siiruo.views.ControlPanel;
import com.siiruo.views.MainWindow;
import com.siiruo.views.StaticPanel;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 自定义的SwingWorker类
 * 主要处理视频播放
 */
public class S_SwingWorker extends SwingWorker<String, Integer> {
	/**
	 *embeddedMediaPlayer 媒体播放器
	* currTime 当前媒体时间
	* totalTime  媒体总时间
	* isFirst  是否是第一次播放标示
	* totalInfo 媒体总时间信息
	* currInfo 当前媒体时间信息
	 */
	private  EmbeddedMediaPlayer embeddedMediaPlayer;
	long currTime=0;
	long totalTime=0;
	boolean isFirst=true;
	String totalInfo="";
	String currInfo="";
	/**
	 * Constructor
	 * @param embeddedMediaPlayer
	 */
	public S_SwingWorker(EmbeddedMediaPlayer embeddedMediaPlayer){
		this.embeddedMediaPlayer=embeddedMediaPlayer;
	}
	/**
	 * 
	 */
	@Override
	protected String doInBackground() throws Exception {
		float percent=0;
		StaticPanel.getControlPanel().getTimeInfoLabel().setText("");
		while(true){	
			currTime=embeddedMediaPlayer.getTime();//开始时currTime=-1
			totalTime=embeddedMediaPlayer.getLength();
			if(currTime==-1) continue;
			percent=((float)currTime/totalTime);
			publish((int)(percent*100));//	publish会将参数数据自动传给process处理
			Thread.sleep(1000);
		}
	}
	/**
	 * 
	 */
	protected void process(java.util.List<Integer> chunks) {
	for(int v:chunks){
		(StaticPanel.getControlPanel()).getProgressBar().setValue(v);
    }
	if(isFirst&&totalTime>0){
		totalTime=totalTime/1000;
		totalInfo=(totalTime/3600)+":"+((totalTime%3600)/60)+":"+((totalTime%3600)%60);
		isFirst=false;
	}
	currTime=currTime/1000;
	currInfo=(currTime/3600)+":"+((currTime%3600)/60)+":"+((currTime%3600)%60);
	//System.out.println("进度："+currInfo+"/"+totalInfo);
	(StaticPanel.getControlPanel()).getTimeInfoLabel().setText(currInfo+"/"+totalInfo);
	if(!embeddedMediaPlayer.isPlaying()&&!embeddedMediaPlayer.canPause()){
		(StaticPanel.getControlPanel()).getTimeInfoLabel().setText("0:0:0/0:0:0");
	}
	}
}
