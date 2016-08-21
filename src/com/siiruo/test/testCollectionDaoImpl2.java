package com.siiruo.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.siiruo.beans.Collection;
import com.siiruo.beans.Video;
import com.siiruo.dao.CollectionDao;
import com.siiruo.dao.CollectionDaoImpl;

public class testCollectionDaoImpl2 {
	public static void main(String[] args) {
		CollectionDao cd=new CollectionDaoImpl();
		//cd.create("经典");
//		List<Video> vs=new ArrayList<Video>();
//		Video video1=new Video();
//		video1.setName("测试视频1");
//		Video video2=new Video();
//		video2.setName("测试视频2");
//		Video video3=new Video();
//		video3.setName("测试视频3");
//		Video video4=new Video();
//		video4.setName("测试视频7");
//		vs.add(video1);
//		vs.add(video2);
//		vs.add(video3);
//		vs.add(video4);	
		List<String> strs=new ArrayList<String>();
		strs.add("测试视频3");
		strs.add("测试视频2");
		strs.add("测试视频4");
	//	cd.remove("默认", new String[]{"测试视频3","测试视频4","测试视频2","测试视频5"});
		//cd.update("经典",new String[]{"测试视频3","测试视频4","测试视频2","测试视频5"});
//		Collection coll=new Collection("默认");
//		coll.setVideos(vs);
		
//		Video video5=new Video();
//		video5.setName("测试视频5");
		//cd.add("经典", "测试视频6");
		List<Collection> list=cd.getCollections();
		Iterator<Collection> itcoll=list.iterator();
		Collection collection;
		while(itcoll.hasNext()){
			collection=itcoll.next();
			System.out.println(collection.getName());
			List<Video> videos=collection.getVideos();
			Iterator<Video> it=videos.iterator();
			while(it.hasNext()){
				System.out.println(it.next().getName());
			}
		}
//		Collection coll=cd.getCollection("默认");
//		System.out.println(coll.getName());
//		List<Video> videos=coll.getVideos();
//		Iterator<Video> it=videos.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next().getName());
//		}
	}

}
