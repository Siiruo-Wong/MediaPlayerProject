package com.siiruo.controller;

import java.util.Timer;
import java.util.TimerTask;
/**
 * 定时器类
 *该类主要是控制Controlpanel 是否显示
 * @author SIIRUO
 * @version 1.0
 */
public class ShowTimer extends Timer{
	/**
	 * timer 
	 */
	private static ShowTimer timer;
	@Override
	public void schedule(TimerTask task, long delay) {
		// TODO Auto-generated method stub
		super.schedule(task, delay);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		super.cancel();
	}

	@Override
	public int purge() {
		// TODO Auto-generated method stub
		return super.purge();
	}
	private ShowTimer(){
		
	}
	/**
	 * 
	 * @return
	 */
	public static synchronized ShowTimer getTimer(){
		if(timer==null){
			timer=new ShowTimer();
		}
		return timer;
	}

}
