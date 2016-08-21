package com.siiruo.listener;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Observable;

import org.apache.log4j.Logger;

import com.siiruo.beans.Video;
import com.siiruo.controller.LoadTask;
import com.siiruo.dao.DaoStaticInstance;
import com.siiruo.util.DateUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.views.OverLayer;
import com.siiruo.views.StaticPanel;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 打开文件的实现类
 *  该类继承自Observable，因此它是主题(被观察者)，通知媒体播放器播放指定的视频 
 * @author SIIRUO
 *
 */
public class MediaDo extends Observable {
	private Logger logger=LoggerUtil.getLogger(MediaDo.class.getName());
	public MediaDo() {
		super();
		/**
		 * 添加观察者
		 */
		addObserver(LoadTask.getLoadTask());
	}
	/**
	 * 处理字符串路径
	 * @param path
	 */
	public void  doWork(String path){
		if(path==null) return;
		path=path.replaceAll("\\s*", "");
		if(!path.isEmpty()){
			doWork(new File(path));	
		}
	}
	/**
	 * 处理URL路径
	 * @param url
	 */
	public void doWork(URL url){
		File file=null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			logger.error("fail to cast to uri...");
			return;
		}
		doWork(file);
	}
	/**
	 * 处理具体的文件
	 * @param file
	 */
	public void doWork(File file){
		String fileName=file.getName();
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
					//System.out.println("!media.isPlaying()!media.isPlaying()!media.isPlaying()!media.isPlaying()");
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
				overLayer.getInformationLabel().setText(fileName);
			}
		}).start();
		
		setChanged();
		notifyObservers(file.getAbsolutePath());
		StaticPanel.getControlPanel().getPlayButton().setIcon(ImageIconUtil.ICON_PAUSE);
		/**
		 * 数据更新线程
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				Encoder encoder = new Encoder();
				long time=0;
				double size=0*1.0;//file.length()的单位是字节
			    try {
					MultimediaInfo meadiaInfo = encoder.getInfo(file);
					time=meadiaInfo.getDuration();
					size=file.length()*1.0;//file.length()的单位是字节
				} catch (InputFormatException e) {
					e.printStackTrace();
				} catch (EncoderException e) {
					e.printStackTrace();
				}
				Video video=new Video(fileName);
				video.setDate(DateUtil.toStringTimeDay(new Date()));
				video.setVisible(true);
				video.setOnline(false);
				video.setPath(file.getAbsolutePath());
				video.setTime(time);
				video.setSize(size);
				
				DaoStaticInstance.getHistoryTreeDao().add(video);
			    DaoStaticInstance.getVideoDao().create(video);
			    DaoStaticInstance.getVideoDao().update(video);
			    DaoStaticInstance.getHistoryDao().add("今天", fileName);
			}
		}).start();
	}
}
