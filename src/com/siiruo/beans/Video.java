package com.siiruo.beans;

import java.util.Date;
/**
 * 视频JavaBean
 * @author SIIRUO
 * @version 1.0
 */
public class Video {
	
	private String name;//视频名称
	private String path;//视频路径
	private long time;//视频时长
	private double size;//视频大小
	private String date;//最后一次播放时间
	private boolean online=false;//是否是在线视频
	private boolean visible=true;//是否在播放记录中显示（默认显示）
	private Collection collection;//所属收藏夹
	public Video(String name) {
		super();
		this.name = name;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Video() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}


}
