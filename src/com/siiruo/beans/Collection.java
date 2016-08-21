package com.siiruo.beans;

import java.util.List;

public class Collection {
	private String name;//收藏夹名称
	private List<Video> videos;//收藏夹内的视频
	public Collection() {
		super();
		
	}
	public Collection(String name) {
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
