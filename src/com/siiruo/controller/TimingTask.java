package com.siiruo.controller;

import java.util.TimerTask;

import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.views.StaticPanel;

/**
 * 执行ControlPanel显示与否的任务类
 * 
 * @author SIIRUO
 * @version 1.0
 */
public class TimingTask extends TimerTask {
	/**
	 * isVisible 控制面板的可见性
	 */
	private boolean isVisible;

	/**
	 * Constructor
	 * 
	 * @param isVisible
	 *            控制面板的可见性
	 */
	public TimingTask(boolean isVisible) {
		super();
		this.isVisible = isVisible;
	}

	@Override
	public void run() {
		//StaticPanel.getControlPanel().setVisible(isVisible);
		MediaPlayerUtil.getOverLayer().setVisible(isVisible);
	}

	/**
	 *  静态方法：根据isVisible创建新的任务对象
	 * 
	 * @param isVisible
	 * @return
	 */
	public static TimingTask getTimerTask(boolean isVisible) {
		return new TimingTask(isVisible);
	}
}
