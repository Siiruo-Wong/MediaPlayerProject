package com.siiruo.beans;

import java.util.Date;
import java.util.List;

public class History {
	private String name;//播放记录标签名称
	private List<Video> videos;//所属的视频列表
	public History() {
		super();
	}
	public History(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

}
