package com.siiruo.test;

import java.util.Iterator;
import java.util.List;

import com.siiruo.beans.History;
import com.siiruo.beans.Video;
import com.siiruo.dao.HistoryDao;
import com.siiruo.dao.HistoryDaoImpl;

public class testHistoryDaoImpl2 {
	public static void main(String[] args) {
		HistoryDao hd=new HistoryDaoImpl();
//		String[] strs=new String[]{
//				"测试视频3","测试视频4","测试视频5"
//		};
//		
//		hd.add("今天", Arrays.asList(strs));
		//hd.remove("今天",strs);
//		Video v1=new Video("下午视频1");
//		Video v2=new Video("视频2");
//		Video v3=new Video("下午视频3");
//		Video v4=new Video("下午视频4");
//		Video v5=new Video("下午视频5");
//		Video v6=new Video("下午视频6");
//		
//		List<Video> list=new ArrayList<Video>();
//		list.add(v1);
//		list.add(v2);
//		list.add(v3);
//		list.add(v4);
//		list.add(v5);
//		list.add(v6);
//		History hi=new History("天");
//		hi.setVideos(list);
//		//hd.update(hi);
//		hd.update("天", "oooo");
		List<History> hiList=hd.getHistories();
		Iterator<History> hiit=hiList.iterator();
		History hi;
		List<Video> vs;
		Iterator<Video> it;
		while(hiit.hasNext()){
			hi=hiit.next();
			System.out.println(hi.getName());
			 vs=hi.getVideos();
			it=vs.iterator();
			while(it.hasNext()){
				System.out.println(it.next().getName());
			}
		}
		
//		History history=hd.getHistory(" ");
//		System.out.println(history.getName());
//		List<Video> vs=history.getVideos();
//		//System.out.println(vs.size());
//		Iterator<Video> it=vs.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next().getName());
//		}
	}
}
