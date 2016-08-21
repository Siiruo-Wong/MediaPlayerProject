package com.siiruo.util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.siiruo.views.ControlPanel;
/**
 * 图标和图片常量类
 * @author SIIRUO
 * @version 1.0
 */
public class ImageIconUtil {
	private static Logger logger=LoggerUtil.getLogger(ImageIconUtil.class.getName());
	/**
	 * ICON_AMPLIFY 最大化播放器窗口的图标
	 */
	public static final ImageIcon ICON_AMPLIFY=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/amplify.png"));
	/**
	 * ICON_NARROW 缩放播放器窗口的图标
	 */
	public static final ImageIcon ICON_NARROW=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/narrow.png"));
	/**
	 * ICON_VOLUME 视频音量图标
	 */
	public static final ImageIcon ICON_VOLUME=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/volume.png"));
	/**
	 * ICON_MUTE 视频静音图标
	 */
	public static final ImageIcon ICON_MUTE=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/mute.png"));
	/**
	 * ICON_PLAY 视频播放图标
	 */
	public static final ImageIcon ICON_PLAY=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/play.png"));
	/**
	 * ICON_PAUSE 视频暂停图标
	 */
	public static final ImageIcon ICON_PAUSE=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/pause.png"));
	/**
	 * ICON_BACKWIND 视频快退图标
	 */
	public static final ImageIcon ICON_BACKWIND=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/backWind.png"));
	/**
	 * ICON_FASTFORWARD 视频快进图标
	 */
	public static final ImageIcon ICON_FASTFORWARD=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/fastForward.png"));
	/**
	 * ICON_STOP 视频停止图标
	 */
	public static final ImageIcon ICON_STOP=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/stop.png"));
	/**
	 * ICON_BLANK 空白的png图片
	 */
	public static final ImageIcon ICON_BLANK=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/blank.png"));
	/**
	 * COMBOBOX_ARROW_ICON ComboBox的按钮图标
	 */
	public static final ImageIcon COMBOBOX_ARROW_ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/pullDownBefore.png"));
	/**
	 *  COMBOBOX_ARROW_ICON_INTO 表示当鼠标点击组合框的按钮或经过按钮时呈现的背景图标
	 */
	public static final ImageIcon COMBOBOX_ARROW_ICON_INTO=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/pullDownAfter.png"));
	/**
	 * SKINPEELER__ICON 换肤图标
	 */
	public static final ImageIcon SKINPEELER__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/skinPeeler.png"));
	/**
	 * DEFAULT_SKIN1__ICON 皮肤1
	 */
	public static final ImageIcon DEFAULT_SKIN1__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin1.jpg"));
	/**
	 * DEFAULT_SKIN1__ICON 皮肤1被选中时
	 */
	public static final ImageIcon DEFAULT_SKIN1_SELECTED__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin1_selected.jpg"));
	/**
	 * DEFAULT_SKIN2__ICON 默认皮肤2
	 */
	public static final ImageIcon DEFAULT_SKIN2__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin2.jpg"));
	/**
	 * DEFAULT_SKIN2__ICON 皮肤2被选中时
	 */
	public static final ImageIcon DEFAULT_SKIN2_SELECTED__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin2_selected.jpg"));
	/**
	 * DEFAULT_SKIN3__ICON 默认皮肤3
	 */
	public static final ImageIcon DEFAULT_SKIN3__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin3.jpg"));
	/**
	 * DEFAULT_SKIN3__ICON 皮肤3被选中时
	 */
	public static final ImageIcon DEFAULT_SKIN3_SELECTED__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin3_selected.jpg"));
	/**
	 * DEFAULT_SKIN4__ICON 默认皮肤4
	 */
	public static final ImageIcon DEFAULT_SKIN4__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin4.jpg"));
	/**
	 * DEFAULT_SKIN4__ICON 皮肤4被选中时
	 */
	public static final ImageIcon DEFAULT_SKIN4_SELECTED__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin4_selected.jpg"));
	/**
	 * DEFAULT_SKIN5__ICON 默认皮肤5
	 */
	public static final ImageIcon DEFAULT_SKIN5__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin5.jpg"));
	/**
	 * DEFAULT_SKIN5__ICON 皮肤5被选中时
	 */
	public static final ImageIcon DEFAULT_SKIN5_SELECTED__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin5_selected.jpg"));
	/**
	 * DEFAULT_SKIN6__ICON 默认皮肤6
	 */
	public static final ImageIcon DEFAULT_SKIN6__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin6.jpg"));
	/**
	 * DEFAULT_SKIN5__ICON 皮肤5被选中时
	 */
	public static final ImageIcon DEFAULT_SKIN6_SELECTED__ICON=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/skin/images/skin6_selected.jpg"));
	
	/**
	 * OPEN_ITEM 打开项图标
	 */
	public static final ImageIcon OPEN_ITEM=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/open.png"));
	/**
	 * CLOSE_ITEM 关闭项图标
	 */
	public static final ImageIcon CLOSE_ITEM=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/close.png"));
	/**
	 * DEFAULT_SKIN 默认皮肤
	 */
	public static final Image DEFAULT_SKIN=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/defaultSkin.jpg")).getImage();
	/**
	 * URL访问标签的默认图标
	 */
	public static final ImageIcon URL_LABEL_DEFAULT=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/urlLabel.png"));
	/**
	 * URL访问标签的聚焦图标
	 */
	public static final ImageIcon URL_LABEL_ENTER=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/urlEnterLabel.png"));
	/**
	 * 搜索访问标签的默认图标
	 */
	public static final ImageIcon SEARCH_LABEL_DEFAULT=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/searchLabel.png"));
	/**
	 * 搜索访问标签的聚焦图标
	 */
	public static final ImageIcon SEARCH_LABEL_ENTER=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/searchEnterLabel.png"));
	/**
	 * 视频信息展开图标
	 */
	public static final ImageIcon TREE_EXPANDED=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/closeItem2.png"));
	/**
	 *  视频信息关闭图标
	 */
	public static final ImageIcon TREE_UNEXPANDED=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/openItem2.png"));
	/**
	 * 视频图标
	 */
	public static final ImageIcon VIDEO=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/video.png"));
	public static final ImageIcon SCROLLBAR_INCREASE=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/gun4.png"));
	public static final ImageIcon SCROLLBAR_DECREASE=new ImageIcon(ImageIconUtil.class.getResource("/com/siiruo/function/images/gun5.png"));

	/**
	 * 根据URL返回一张图片
	 * @param url
	 * @return
	 */
	public static Image getImage(URL url){
		if(url==null) return null;
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			logger.error("Reading image failed...");
			return null;
		}
		
	}
	/**
	 * 根据路径字符串返回一张图片
	 * @param path
	 * @return
	 */
	public static Image getImage(String path){
		if(path==null) return null;
		return getImage(ImageIconUtil.class.getResource(path));
	}
}
