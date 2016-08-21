package com.siiruo.dao;

import java.util.List;

import com.siiruo.beans.History;
import com.siiruo.beans.Video;
/**
 * 历史播放记录处理接口
 * @author SIIRUO
 * @version 1.0
 */
public interface HistoryDao {
	/**
	 * 创建一个包含根元素<histories>和子元素<history name="今天">、<history name="昨天">和<history name="更久">
	 * 的空XML文档
	 */
	public void createHistoryXML();
	/**
	 * 在根元素<histories>下创建子元素<history name="name">
	 * @param name
	 */
	public void create(String name);
	/**
	 * 根据History对象，在根元素<histories>下创建子元素标签<history>
	 * @param history
	 */
	public void create(History history);
	/**
	 * 在指定历史标签中，新增一个视频信息
	 * @param name
	 * @param vname
	 */
	public void add(String name,String vname);
	/**
	 * 在指定历史标签中，新增一组视频信息
	 * @param name
	 * @param vnames
	 */
	public void add(String name,String[] vnames);
	/**
	 * 在指定历史标签中，新增一组视频信息
	 * @param name
	 * @param vnames
	 */
	public void add(String name,List<String> vnames);
	/**
	 * 删除指定的历史标签
	 * @param name
	 */
	public void remove(String name);
	/**
	 * 在指定的历史标签中删除指定的一个视频信息
	 * @param name
	 * @param vname
	 */
	public void remove(String name,String vname);
	/**
	 * 在指定的历史标签中删除指定的一组视频信息
	 * @param name
	 * @param vnames
	 */
	public void remove(String name,String[] vnames);
	/**
	 * 在指定的历史标签中删除指定的一组视频信息
	 * @param name
	 * @param vnames
	 */
	public void remove(String name,List<String> vnames);
	/**
	 * 清空指定的历史标签
	 * @param name
	 */
	public void clear(String name);
	/**
	 * 根据指定的History对象，更新指定的历史标签
	 * @param name
	 * @param history
	 */
	public void update(String name,History history);
	/**
	 * 根据指定的History对象，更新自身
	 * @param history
	 */
	public void update(History history);
	/**
	 * 使用一个指定的视频信息完全更新指定的历史标签
	 * @param name
	 * @param vname
	 */
	public void update(String name,String vname);
	/**
	 * 使用一组指定的视频信息完全更新指定的历史标签
	 * @param name
	 * @param vnames
	 */
	public void update(String name,String[] vnames);
	/**
	 * 使用一组指定的视频信息完全更新指定的历史标签
	 * @param name
	 * @param vnames
	 */
	public void update(String name,List<String> vnames);
	/**
	 * 获取指定标签名的播放历史记录
	 * @param name
	 * @return
	 */
	public History getHistory(String name);
	/**
	 * 获取所有的历史播放记录信息
	 * @return
	 */
	public List<History> getHistories();
}
