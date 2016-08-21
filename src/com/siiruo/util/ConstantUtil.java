package com.siiruo.util;
/**
 * 视频控制常量类
 * @author SIIRUO
 * @version 1.0
 */
public class ConstantUtil {
	/**
	 * PROGRESS_GAP 视频跳跃步长
	 * 包括快进和快退视频
	 */
	public static final int PROGRESS_GAP=5000;
	/**
	 * VOLUME_GAP 音量跳跃步长
	 * 包括增加和减低音量
	 */
	public static final int VOLUME_GAP=2;
	/**
	 * TIMER_GAP 控制面板定时消失和显示的时间步长
	 */
	public static final int TIMER_GAP=5000;
	/**
	 * VOLUME_DEFAULT 视频音量默认大小
	 */
	public static final int VOLUME_DEFAULT=50;
	/**
	 * 视频文件类型数组
	 */
	public static final String[] VIDEO_FILE_TYPE={
			"AVI","MPEG","MOV","ASF","WMV","RM","RMVB","MP4","3GP","MPG","FLV","MKV","VOB"
	};

}
