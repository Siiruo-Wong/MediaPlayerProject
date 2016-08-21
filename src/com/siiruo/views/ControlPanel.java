package com.siiruo.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.siiruo.listener.MaxToVideoListener;
import com.siiruo.ui.S_CButtonUI;
import com.siiruo.util.ConstantUtil;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.MediaPlayerUtil;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * 该类相对来讲已经比较复杂了，主要完成对媒体的控制操作
 * 包括：快进、快退、下一个媒体、上一个媒体、播放、暂停、停止、静音、音量控制、视频播放界面最大化以及播放时间等等
 * 
 * @author SIIRUO
 * @version 1.0
 */
public class ControlPanel extends JPanel {
	/**
	 * embeddedMediaPlayer 媒体播放器 
	 * progressBar 媒体进度条
	 * playButton 播放和暂停按钮 
	 * stopButton 停止播放按钮 
	 * backWindButton 快退按钮
	 * fastForwardButton 快进按钮
	 * buttonPanel 承载控制按钮的面板
	 * progressPanel 承载进度控制的面板 
	 * timeInfoLabel 时间信息标签 
	 * amplifyIcon 最大化图标 
	 * volumeIcon 音量图标
	 * muteIcon 静音图标 
	 * playIcon 播放图标
	 * pauseIcon 暂停图标 
	 * volumePanel 承载音量控制的面板
	 * volumeSlider 音量滑动条
	 * maxLabel 最大化图标 
	 * frame 父窗口
	 */
	private static final long serialVersionUID = -3194369824906517518L;
	private EmbeddedMediaPlayer embeddedMediaPlayer;
	private JProgressBar progressBar;
	private JButton playButton;
	private JButton stopButton;
	private JButton backWindButton;
	private JButton fastForwardButton;
	private JPanel buttonPanel;
	private JPanel progressPanel;
	private JLabel timeInfoLabel;
	private ImageIcon amplifyIcon;
	private ImageIcon volumeIcon;
	private ImageIcon muteIcon;
	private ImageIcon playIcon;
	private ImageIcon pauseIcon;
	private JPanel volumePanel;
	private JLabel volumeLabel;
	private JSlider volumeSlider;
	private JLabel maxLabel;
	private MainWindow frame;

	

	/**
	 * Create the panel.
	 */
	public ControlPanel() {
		setBackground(Color.DARK_GRAY);
		amplifyIcon = ImageIconUtil.ICON_AMPLIFY;
		volumeIcon = ImageIconUtil.ICON_VOLUME;
		muteIcon = ImageIconUtil.ICON_MUTE;
		playIcon = ImageIconUtil.ICON_PLAY;
		pauseIcon = ImageIconUtil.ICON_PAUSE;

		// 获得父窗口
		//frame =MainWindow.getFatherFrame();
		// 获得媒体播放器
		this.embeddedMediaPlayer =MediaPlayerUtil.getMediaPlayer();
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBackground(new Color(173, 216, 230));

		progressPanel = new JPanel();
		progressPanel.setOpaque(false);
		progressPanel.setMaximumSize(new Dimension(32767, 25));
		//progressPanel.setBackground(new Color(176, 196, 222));

		volumePanel = new JPanel();
		volumePanel.setOpaque(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(progressPanel, GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(volumePanel, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(0)
										.addComponent(progressPanel, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(volumePanel, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
										.addContainerGap()));

		volumeLabel = new JLabel("");
		// 当鼠标进入volumeLabel时，将鼠标设置为手型
		volumeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		/**
		 * 为volumeLabel添加鼠标响应事件
		 */
		volumeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 * 判断视频是否静音，若是则释放静音，若不是则静音 同时修改音量图标
				 */
				if (embeddedMediaPlayer.isMute()) {
					embeddedMediaPlayer.mute(false);
					//embeddedMediaPlayer.setVolume(volumeSlider.getValue());
					volumeLabel.setIcon(volumeIcon);

				} else {
					embeddedMediaPlayer.mute(true);
					//embeddedMediaPlayer.setVolume(0);
					volumeLabel.setIcon(muteIcon);
				}
			}
		});
		volumeLabel.setIcon(volumeIcon);
		volumeSlider = new JSlider();
		volumeSlider.setFocusable(true);
		volumeSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		volumeSlider.setValue(ConstantUtil.VOLUME_DEFAULT);
		volumeSlider.setMaximum(120);
		/**
		 * 为volumeSlider添加状态变化事件
		 */
		volumeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// 修改媒体音量
				embeddedMediaPlayer.setVolume(volumeSlider.getValue());
			}
		});
		GroupLayout gl_volumePanel = new GroupLayout(volumePanel);
		gl_volumePanel
				.setHorizontalGroup(gl_volumePanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_volumePanel.createSequentialGroup().addContainerGap(33, Short.MAX_VALUE)
								.addComponent(volumeLabel).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		gl_volumePanel.setVerticalGroup(gl_volumePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_volumePanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_volumePanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(volumeLabel))
						.addContainerGap(4, Short.MAX_VALUE)));
		volumePanel.setLayout(gl_volumePanel);

		timeInfoLabel = new JLabel("0:0:0/0:0:0");

		timeInfoLabel.setForeground(Color.YELLOW);
		timeInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);

		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(10, 10));
		progressBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		progressBar.addMouseListener(new ProgressBarListener());
		progressBar.setBorder(null);
		progressBar.setForeground(Color.BLUE);
		progressBar.setBackground(new Color(119, 136, 153));
		progressBar.setStringPainted(true);

		maxLabel = new JLabel("");
		maxLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maxLabel.setHorizontalAlignment(SwingConstants.CENTER);
		maxLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		maxLabel.setIcon(amplifyIcon);
		maxLabel.setName("siiruo");
		/**
		 * 为最大化标签添加鼠标响应事件
		 */
		maxLabel.addMouseListener(new MaxToVideoListener(maxLabel));
		maxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		GroupLayout gl_progressPanel = new GroupLayout(progressPanel);
		gl_progressPanel
				.setHorizontalGroup(gl_progressPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_progressPanel.createSequentialGroup().addContainerGap()
								.addComponent(timeInfoLabel, GroupLayout.PREFERRED_SIZE, 106,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(maxLabel).addGap(11)));
		gl_progressPanel.setVerticalGroup(gl_progressPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_progressPanel.createSequentialGroup().addGap(0)
						.addGroup(gl_progressPanel.createParallelGroup(Alignment.BASELINE).addComponent(timeInfoLabel)
								.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(maxLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(3)));
		progressPanel.setLayout(gl_progressPanel);

		backWindButton = new JButton("");
		backWindButton.setBorder(null);
		backWindButton.setUI(new S_CButtonUI());// 设置按钮的UI
		backWindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backWindButton.setRequestFocusEnabled(false);
		backWindButton.setFocusable(true);
		backWindButton.setBackground(new Color(95, 158, 160));
		backWindButton.setIcon(ImageIconUtil.ICON_BACKWIND);
		backWindButton.setActionCommand("backWind");// 设置事件标识
		/**
		 * 为快退按钮backWindButton添加鼠标响应事件
		 */
		backWindButton.addMouseListener(new ControlButtonListener());
		backWindButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		backWindButton.setHorizontalTextPosition(SwingConstants.CENTER);

		playButton = new JButton("");
		playButton.setUI(new S_CButtonUI());// 设置按钮的UI
		playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		playButton.setFocusable(true);
		playButton.setBackground(new Color(95, 158, 160));
		playButton.setIcon(ImageIconUtil.ICON_PLAY);
		playButton.setRequestFocusEnabled(false);
		playButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		playButton.setHorizontalTextPosition(SwingConstants.CENTER);
		playButton.setActionCommand("play");
		/**
		 * 为播放按钮playButton添加鼠标响应事件
		 */
		playButton.addMouseListener(new ControlButtonListener());

		fastForwardButton = new JButton("");
		fastForwardButton.setUI(new S_CButtonUI());// 设置按钮的UI
		fastForwardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fastForwardButton.setRequestFocusEnabled(false);
		fastForwardButton.setFocusable(true);
		fastForwardButton.setBackground(new Color(95, 158, 160));
		fastForwardButton.setIcon(ImageIconUtil.ICON_FASTFORWARD);
		fastForwardButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		fastForwardButton.setHorizontalTextPosition(SwingConstants.CENTER);
		fastForwardButton.setActionCommand("fastForward");
		/**
		 * 为快进按钮fastForwardButton添加鼠标响应事件
		 */
		fastForwardButton.addMouseListener(new ControlButtonListener());

		stopButton = new JButton("");
		stopButton.setUI(new S_CButtonUI());// 设置按钮的UI
		stopButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stopButton.setBackground(new Color(95, 158, 160));
		stopButton.setIcon(ImageIconUtil.ICON_STOP);
		stopButton.setRequestFocusEnabled(false);
		stopButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
		stopButton.setActionCommand("stop");
		/**
		 * 为停止按钮stopButton添加鼠标响应事件
		 */
		stopButton.addMouseListener(new ControlButtonListener());

		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonPanel.setFocusable(true);
		buttonPanel.add(backWindButton);
		buttonPanel.add(playButton);
		buttonPanel.add(fastForwardButton);
		buttonPanel.add(stopButton);
		groupLayout.setHonorsVisibility(false);
		setLayout(groupLayout);

	}
/////////////////////////setter and getter start //////////////////////////////////
	public EmbeddedMediaPlayer getEmbeddedMediaPlayer() {
		return embeddedMediaPlayer;
	}

	public void setEmbeddedMediaPlayer(EmbeddedMediaPlayer embeddedMediaPlayer) {
		this.embeddedMediaPlayer = embeddedMediaPlayer;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public JLabel getTimeInfoLabel() {
		return timeInfoLabel;
	}

	public void setTimeInfoLabel(JLabel timeInfoLabel) {
		this.timeInfoLabel = timeInfoLabel;
	}

	public JLabel getMaxLabel() {
		return maxLabel;
	}

	public void setMaxLabel(JLabel maxLabel) {
		this.maxLabel = maxLabel;
	}

	public JSlider getVolumeSlider() {
		return volumeSlider;
	}

	public void setVolumeSlider(JSlider volumeSlider) {
		this.volumeSlider = volumeSlider;
	}
///////////////////////////setter and getter end ////////////////////////////
	/**
	 * 进度条响应事件的内部类
	 * 
	 * @author SIIRUO
	 * @version 1.0
	 */
	class ProgressBarListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int xValue = e.getX();// 获得鼠标在进度条上位置大小
			float pro = ((float) xValue / progressBar.getWidth());// 当前鼠标位置相对于进度条的大小（百分比）
			embeddedMediaPlayer.setTime((long) (pro * embeddedMediaPlayer.getLength()));
		}
	}

	/**
	 * 控制按钮的鼠标响应事件的内部类
	 * 
	 * @author SIIRUO
	 * @version 1.0
	 */
	class ControlButtonListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JButton tempButton = (JButton) e.getSource();// 获得鼠标事件源类型
			String command = tempButton.getActionCommand();// 获得按钮标识
			switch (command) {
			case "backWind":
				backWindControl();
				break;
			case "play":
				playControl();
				break;
			case "fastForward":
				fastForwardControl();
				break;
			case "stop":
				stopControl();
				break;
			default:
				break;

			}
		}

		/**
		 * 媒体快退
		 */
		public void backWindControl() {
			System.out.println("后退");
			if ((embeddedMediaPlayer.getTime() - ConstantUtil.PROGRESS_GAP) > 0
					&& embeddedMediaPlayer.getTime() < embeddedMediaPlayer.getLength()) {
				embeddedMediaPlayer.setTime(embeddedMediaPlayer.getTime() - ConstantUtil.PROGRESS_GAP);
			}
		}

		/**
		 * 媒体快进
		 */
		public void fastForwardControl() {
			System.out.println("前进");
			if (embeddedMediaPlayer.getTime() > 0
					&& (embeddedMediaPlayer.getTime() + ConstantUtil.PROGRESS_GAP) < embeddedMediaPlayer.getLength()) {
				embeddedMediaPlayer.setTime(embeddedMediaPlayer.getTime() + ConstantUtil.PROGRESS_GAP);
			}
		}

		/**
		 * 媒体播放
		 */
		public void playControl() {
			if (embeddedMediaPlayer.isPlaying()) {
				embeddedMediaPlayer.pause();
				playButton.setIcon(playIcon);
			} else if(embeddedMediaPlayer.isPlayable()){
				embeddedMediaPlayer.play();
				playButton.setIcon(pauseIcon);
			}else{
				
			}
		}

		/**
		 * 媒体停止
		 */
		public void stopControl() {
			if (embeddedMediaPlayer.isPlaying() || embeddedMediaPlayer.isPlayable()) {
				embeddedMediaPlayer.stop();
				progressBar.setValue(0);
				timeInfoLabel.setText("0:0:0/0:0:0");
				MediaPlayerUtil.getOverLayer().getInformationLabel().setText("");
				playButton.setIcon(playIcon);
				MediaPlayerUtil.getOverLayer().getInformationLabel().setText("");

			}
		}
	}

		
}
