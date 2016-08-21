package com.siiruo.listener;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

import com.siiruo.util.ConstantUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.views.ControlPanel;
import com.siiruo.views.MainWindow;
import com.siiruo.views.StaticPanel;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 最大化、音量、进度等事件的监听类，
 * 该类在整个键盘控制视频的过程中的意义比较重大，
 * @author SIIRUO
 * @version 1.0
 */
public class KeyControlListener extends KeyAdapter {
	/**
	 * controlPanel 控制面板
	 * mediaPlayer 媒体播放器
	 * volumeSlider 音量滑动条
	 * mainWindow 父窗口
	 */
	private ControlPanel controlPanel;
	private EmbeddedMediaPlayer mediaPlayer;
	private JSlider volumeSlider;
	private MainWindow mainWindow;
	/**
	 * Constructor 
	 */
	public KeyControlListener(){
		
	}
	/**
	 * Constructor
	 * @param mainWindow 父窗口
	 * @param mediaPlayer 媒体播放器
	 */
	public KeyControlListener(MainWindow mainWindow, EmbeddedMediaPlayer mediaPlayer) {
		super();
		this.mainWindow=mainWindow;
		this.controlPanel = StaticPanel.getControlPanel();
		this.mediaPlayer = mediaPlayer;
		this.volumeSlider=controlPanel.getVolumeSlider();
	}
	/**
	 * Constructor
	 * @param mainWindow 父窗口
	 * @param controlPanel 控制面板
	 * @param mediaPlayer  媒体播放器
	 */
	public KeyControlListener(MainWindow mainWindow, ControlPanel controlPanel,EmbeddedMediaPlayer mediaPlayer) {
		super();
		this.mainWindow=mainWindow;
		this.controlPanel =controlPanel;
		this.mediaPlayer = mediaPlayer;
		this.volumeSlider=controlPanel.getVolumeSlider();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP){
			increaseVolume();
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			decreaseVolume();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			backWindControl();
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			fastForwardControl();
		}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
			playOrPauseControl(e);
		}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			maxAndNormalControl(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyTyped(e);
	}
	/**
	 * 增减音量
	 */
	public void increaseVolume(){		
		if(volumeSlider.getValue()<=(volumeSlider.getMaximum()-ConstantUtil.VOLUME_GAP)){
			volumeSlider.setValue(volumeSlider.getValue()+ConstantUtil.VOLUME_GAP);
			mediaPlayer.setVolume(volumeSlider.getValue());
		}else{
			volumeSlider.setValue(volumeSlider.getMaximum());
			mediaPlayer.setVolume(volumeSlider.getValue());
		}
			
	}
	/**
	 * 降低音量
	 */
	public void decreaseVolume(){
		if(volumeSlider.getValue()>=(volumeSlider.getMinimum()+ConstantUtil.VOLUME_GAP)){
			volumeSlider.setValue(volumeSlider.getValue()-ConstantUtil.VOLUME_GAP);
			mediaPlayer.setVolume(volumeSlider.getValue());
		}else{
			volumeSlider.setValue(volumeSlider.getMinimum());
			mediaPlayer.setVolume(volumeSlider.getValue());
		}
	}
	/**
	 * 快退视频
	 */
	public void backWindControl(){
		if((mediaPlayer.getTime()-ConstantUtil.PROGRESS_GAP)>0&&mediaPlayer.getTime()<mediaPlayer.getLength()){
			mediaPlayer.setTime(mediaPlayer.getTime()-ConstantUtil.PROGRESS_GAP);
		}
	}
	/**
	 * 快进视频
	 */
	public void fastForwardControl(){
		if(mediaPlayer.getTime()>0&&(mediaPlayer.getTime()+ConstantUtil.PROGRESS_GAP)<mediaPlayer.getLength()){
			mediaPlayer.setTime(mediaPlayer.getTime()+ConstantUtil.PROGRESS_GAP);
		}
	}
	/**
	 * 视频播放和暂停控制
	 * @param e 键盘事件对象
	 */
	public void playOrPauseControl(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause();
				controlPanel.getPlayButton().setIcon(ImageIconUtil.ICON_PLAY);
			}else{
				mediaPlayer.play();
				controlPanel.getPlayButton().setIcon(ImageIconUtil.ICON_PAUSE);
			}	
		}
	}
	/**
	 * 视频播放窗口最大化和正常化控制
	 * @param e 键盘事件对象
	 */
	public void maxAndNormalControl(KeyEvent e){
		if((e.getKeyCode()==KeyEvent.VK_ESCAPE)
				&&(mainWindow.getExtendedState()==JFrame.MAXIMIZED_BOTH)){
			mainWindow.setExtendedState(JFrame.NORMAL);
			MainWindow.getContain().setBorder(new LineBorder(new Color(210, 180, 140), 1, true));
			(StaticPanel.getControlPanel()).getMaxLabel().setIcon(ImageIconUtil.ICON_AMPLIFY);
			MainWindow.getFramePanel().getHeadPanel().setVisible(true);
			StaticPanel.getMenuPanel().setVisible(true);
			StaticPanel.getSidePanel().setVisible(true);
			StaticPanel.getBottomPanel().setVisible(true);
			MainWindow.getContain().setBorder(new LineBorder(new Color(210, 180, 140), 1, true));
		}
	}
}
