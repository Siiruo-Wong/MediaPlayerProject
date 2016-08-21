package com.siiruo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.siiruo.beans.Collection;
import com.siiruo.beans.Video;
import com.siiruo.dao.VideoDao;
import com.siiruo.dao.VideoDaoImpl;
import com.siiruo.util.DateUtil;

public class testVideoDaoImp2 {
	public static void main(String[] args) {
//		Video video=new Video();
//		video.setCollection(new Collection("默认"));
//		video.setDate(new Date());
//		video.setName("测试视频");
//		video.setOnline(false);
//		video.setPath("D:\\\\电影\\\\乡村123.mp4");
//		video.setSize(0);
//		video.setTime(0);
	VideoDao  vd=new VideoDaoImpl();
//		vd.remove("测试视频");
	//	vd.create(video);
//		List<String> names=new ArrayList<String>();
//		names.add("测试视频3");
//		names.add("测试视频1");
//		names.add("测试视频8");
//		names.add("测试视频5");
//		names.add("测试视频2");
//		vd.remove(names);
//		vd.remove(new String[]{"测试视频3","测试视频5","测试视频1","测试视频4","测试视频6","测试视频8"});
		//vd.remove("测试视频7");
		List<Video> videos=vd.getVideos(true);
		Iterator<Video> iterator=videos.iterator();
		Video v;
		while(iterator.hasNext()){
			v=iterator.next();
			System.out.println(v.getName());
			System.out.println(v.isOnline());
			System.out.println(v.getPath());
			System.out.println(v.getSize());
			System.out.println(v.getTime());
			System.out.println(v.getDate());
			System.out.println(v.getCollection().getName());
		}
	//	vd.update(video);
		
	}

}
