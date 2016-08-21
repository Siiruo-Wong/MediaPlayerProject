package com.siiruo.views;

import com.siiruo.translucence.FramePanel;

public class StaticPanel {
	private static MenuPanel menuPanel;
	private static SidePanel sidePanel;
	private static ControlPanel controlPanel;
	private static BottomPanel bottomPanel;
	private static FramePanel framePanel;
	public synchronized static MenuPanel getMenuPanel(){
		if(menuPanel==null){
			menuPanel=new MenuPanel();
		}
		return menuPanel;
	}
	public synchronized static SidePanel getSidePanel(){
		if(sidePanel==null){
			sidePanel=new SidePanel();
		}
		return sidePanel;
	}
	public synchronized static ControlPanel getControlPanel(){
		if(controlPanel==null){
			controlPanel=new ControlPanel();
		}
		return controlPanel;
	}
	public synchronized static BottomPanel getBottomPanel(){
		if(bottomPanel==null){
			bottomPanel=new BottomPanel();
		}
		return bottomPanel;
	}
	public synchronized static FramePanel getFramePanel(){
		if(framePanel==null){
			framePanel = new FramePanel();
		}
		return framePanel;
	}
	

}
