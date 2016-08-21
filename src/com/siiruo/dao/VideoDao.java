package com.siiruo.dao;

import java.util.List;

import com.siiruo.beans.Video;
/**
 * 视频处理接口
 * @author SIIRUO
 * @version 1.0
 */
public interface VideoDao {
	/**
	 * 创建一个只包含根元素<videos>的XML文档
	 */
	public  void createVideoXML();
	/**
	 * 根据Video对象创建一个新的视频标签<video>
	 * @param video
	 */
	public  void create(Video video);
	/**
	 * 删除指定名称的视频标签
	 * @param name
	 */
	public  void remove(String name);
	/**
	 * 删除指定名称的所有视频标签
	 * @param names
	 */
	public  void remove(String[] names);
	/**
	 * 删除指定名称的所有视频标签
	 * @param names
	 */
	public  void remove(List<String> names);
	/**
	 * 使用新的视频对象更新指定视频名称的标签
	 * @param name
	 * @param video
	 */
	public void update(String name,Video video);	
	/**
	 * 使用新的视频对象更新自己的视频标签
	 * @param video
	 */
	public void update(Video video);
	/**
	 * 获得指定视频名称的视频对象
	 * @param name
	 * @return
	 */
	public Video getVideo(String name);
	/**
	 * 获得所有指定其属性是否在线的所有的视频信息
	 * @param online
	 * @return
	 */
	public List<Video> getVideos(boolean online);
	/**
	 * 获得所有的视频信息
	 * @return
	 */
	public List<Video> getVideos();
}
