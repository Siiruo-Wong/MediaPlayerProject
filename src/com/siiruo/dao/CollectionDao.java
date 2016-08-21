package com.siiruo.dao;

import java.util.List;

import com.siiruo.beans.Collection;
import com.siiruo.beans.Video;
/**
 * 收藏夹处理接口
 * @author SIIRUO
 * @version 1.0
 */
public interface CollectionDao {
	/**
	 * 创建一个只包含根元素<collections>的XML文档
	 */
	public  void createCollectionXML();
	/**
	 * 在根元素<collections>下创建一个指定名称的空的子元素<collection>
	 * @param name
	 */
	public void create(String name);
	/**
	 * 在根元素<collections>下创建一个指定Collection对象的子元素<collection>
	 * @param collection
	 */
	public void create(Collection collection);
	/**
	 * 在指定的收藏夹标签中新增一个视频信息
	 * @param name
	 * @param vname
	 */
	public void add(String name,String vname);
	/**
	 * 在指定的收藏夹的标签中新加入一组视频信息
	 * @param name
	 * @param videos
	 */
	public void add(String name,String[] vnames);
	/**
	 * 在指定的收藏夹的标签中新加入一组视频信息
	 * @param name
	 * @param videos
	 */
	public void add(String name,List<String> vnames);
	/**
	 * 彻底删除指定名称的收藏夹标签
	 * @param name
	 */
	public void remove(String name);
	/**
	 * 在指定的收藏夹标签中删除指定的视频
	 * @param name
	 * @param vname
	 */
	public void remove(String name,String vname);
	/**
	 * 在指定的收藏夹标签中删除指定的一组视频
	 * @param name
	 * @param vnames
	 */
	public void remove(String name,String[] vnames);
	/**
	 * 在指定的收藏夹标签中删除指定的一组视频
	 * @param name
	 * @param vnames
	 */
	public void remove(String name,List<String> vnames);
	/**
	 * 清空指定名称的收藏夹标签
	 * @param name
	 */
	public void clear(String name);
	/**
	 * 使用新的Collection对象更新指定名称的收藏夹标签
	 * @param name
	 * @param collection
	 */
	
	public void update(String name,Collection collection);
	/**
	 * 使用一个视频信息，完全更新指定的收藏夹标签
	 * @param name
	 * @param vname
	 */
	public void update(String name,String vname);
	/**
	 * 使用组视频信息，完全更新指定的收藏夹标签
	 * @param name
	 * @param vnames
	 */
	public void update(String name,String[] vnames);
	/**
	 *使用组视频信息，完全更新指定的收藏夹标签 
	 * @param name
	 * @param vnames
	 */
	public void update(String name,List<String> vnames);
	/**
	 * 使用新的Collection对象更新自身
	 * @param collection
	 */
	public void update(Collection collection);
	/**
	 * 获得指定名称的收藏夹
	 * @param name
	 * @return
	 */
	public Collection getCollection(String name);
	/**
	 * 获得所有的收藏夹
	 * @return
	 */
	public List<Collection> getCollections();
}
