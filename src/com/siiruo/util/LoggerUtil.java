package com.siiruo.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 日志工具类
 * 
 * @author SIIRUO
 * @version 1.0
 */
public class LoggerUtil {
	/**
	 * logger 日志对象
	 */
	private static Logger logger;

	/**
	 * 静态方法返回日志对象
	 * @param name 需要加载日志对象的类名
	 * @return logger
	 */
	public static Logger getLogger(String name) {
		logger = Logger.getLogger(name);
		/**
		 * 读取使用Java的特性文件编写的配置文件
		 */
		PropertyConfigurator.configure(".\\src\\resources\\log4j.properties");
		return logger;
	}

}
