package com.siiruo.thread;

import java.util.ArrayList;
import java.util.List;

import com.siiruo.beans.Video;
import com.siiruo.dao.CollectionDao;
import com.siiruo.dao.CollectionDaoImpl;
import com.siiruo.dao.HistoryDao;
import com.siiruo.dao.HistoryDaoImpl;
import com.siiruo.dao.VideoDao;
import com.siiruo.dao.VideoDaoImpl;
import com.siiruo.util.DateUtil;
public class LoadVideos implements Runnable {
	private VideoDao videoDao;
	private HistoryDao historyDao;
	private CollectionDao collectionDao;
	//private DateUtil dateUtil=DateUtil.getInstance();
	public LoadVideos(VideoDao videoDao, HistoryDao historyDao, CollectionDao collectionDao) {
		super();
		this.videoDao = videoDao;
		this.historyDao = historyDao;
		this.collectionDao = collectionDao;
	}
	public LoadVideos(){
		super();
		this.videoDao = new VideoDaoImpl();
		this.historyDao = new HistoryDaoImpl();
		this.collectionDao = new CollectionDaoImpl();
	}
	

	@Override
	public  void run() {		
			List<Video> videos=videoDao.getVideos();
			if(videos==null||videos.size()==0)return;
			int len=videos.size();
			//System.out.println("长度："+len);
			String today=DateUtil.getTodayDate();
		//	System.out.println("today:"+today);
			String yesterday=DateUtil.getYestoryDate();
		//	System.out.println("yesterday:"+yesterday);
			String lastestDay;
			List<String> todayVideos=new ArrayList<String>();
			List<String> yesterdayVideos=new ArrayList<String>();
			List<String> longVideos=new ArrayList<String>();
			for(int i=0;i<len;i++){
				lastestDay=videos.get(i).getDate();
				lastestDay=lastestDay.split(" ")[0];
				if(lastestDay.equals(today)){
					todayVideos.add(videos.get(i).getName());
				}else if(lastestDay.equals(yesterday)){
					yesterdayVideos.add(videos.get(i).getName());
				}else{
					longVideos.add(videos.get(i).getName());
				}
			}
			historyDao.update("今天", todayVideos);
			historyDao.update("昨天", yesterdayVideos);
			historyDao.update("更久", longVideos);
		

	}

}
