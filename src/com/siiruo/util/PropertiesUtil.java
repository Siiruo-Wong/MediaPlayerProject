package com.siiruo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置文件工具类
 * @author SIIRUO
 * @version 1.0
 */
public class PropertiesUtil {
	private static Logger logger=LoggerUtil.getLogger(PropertiesUtil.class.getName());
	/**
	 * 根据配置文件的地址和键名，返回对应的值
	 * @param path 配置文件的地址
	 * @param key 键
	 * @return 对应的值
	 */
	public static String getValue(String path, String key){
		return getProperties(path).getProperty(key);
	}
	/**
	 * 根据配置文件的地址返回一个Properties对象
	 * @param path
	 * @return
	 */
	
	public static Properties getProperties(String path){
		Properties pro=getInstance();
		try {
			pro.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			logger.error("file is not found...");
			return null;
		} catch (IOException e) {
			logger.error("the process of loading file encounters an IOException...");
			return null;
		}
		return pro;
	}
	/**
	 * 返回一个无参的、新的Properties
	 * @return
	 */
	public static   Properties getInstance(){	
		return new Properties();
	}
}
