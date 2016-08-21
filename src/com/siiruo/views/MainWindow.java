package com.siiruo.views;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.siiruo.listener.CanvasListener;
import com.siiruo.listener.KeyControlListener;
import com.siiruo.thread.ShowOverLayer;
import com.siiruo.translucence.FramePanel;
import com.siiruo.util.ImageIconUtil;
import com.siiruo.util.LoggerUtil;
import com.siiruo.util.MediaPlayerUtil;
import com.siiruo.util.PropertiesUtil;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 该类是整个视频播放器项目的总体框架结构以及入口
 * 也作为一个观察者
 * @author SIIRUO
 * @version 1.0
 */
public class MainWindow extends JFrame implements Observer{

	/**
	 * framePanel 自定义的无边框的窗口面板
	 * contain framePanel的主要承载区
	 * headPanel framePanel的头部区域
	 * bgImage 背景图片
	 * embeddedMediaPlayerComponent 媒体播放器组件
	 * menuPanel 菜单面板
	 * sidePanel 侧边栏面板
	 * bottomPanel 底部面板
	 * containLayout 布局工具对象
	 * canvas 媒体播放器组件的实际工作的画布区
	 * logger 日志对象
	 */
	private static final long serialVersionUID = 454687645675475L;
	private static FramePanel framePanel = StaticPanel.getFramePanel();
	private static JComponent contain;//framePanel的contain区域
	private  JPanel headPanel;//framePanel的contain区域
	private Image bgImage ;
	private EmbeddedMediaPlayerComponent embeddedMediaPlayerComponent;
	private EmbeddedMediaPlayer mediaPlayer;
	private static JPanel menuPanel;
	private static JPanel sidePanel;
	private static JPanel bottomPanel;
	private static MainWindow mainWindow;
	private ContainLayout containLayout;
	private Canvas canvas;
	private Logger logger=LoggerUtil.getLogger(MainWindow.class.getName());
	
	/**
	 *构造函数
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setFocusable(true);//要想启动键盘事件则必须让该窗口聚焦
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				Dimension d=MediaPlayerUtil.getMediaPlayer().getVideoDimension();
				if(d!=null){
					System.out.println("视频的宽："+d.getWidth());
					System.out.println("视频的高："+d.getHeight());
				}else{
					System.out.println("空**************空");
				}
				
			}
			
		});
		embeddedMediaPlayerComponent=MediaPlayerUtil.getMediaPlayerComponent();//初始化媒体播放器组件		
		mediaPlayer=MediaPlayerUtil.getMediaPlayer();//获得媒体播放器
		//自定义的无边框的窗口面板初始化，这也是整个窗口界面的关键
		//framePanel = StaticPanel.getFramePanel();
		framePanel.setParent(this);//设置父组件
		framePanel.setTitle("SIIRUO");//设置头标文字
		framePanel.setEnnableMaxButton(true);//是否允许最大化	
		//bgImage = Toolkit.getDefaultToolkit().getImage("images/1107607.jpg");
		bgImage=ImageIconUtil.getImage(PropertiesUtil.getValue(".\\src\\resources\\skin.properties","defaultSkin"));
		framePanel.setBackground(bgImage==null?ImageIconUtil.DEFAULT_SKIN:bgImage);
		/**
		 * 
		 * 下述是功能面板初始化
		 */
		menuPanel=StaticPanel.getMenuPanel();
		sidePanel=StaticPanel.getSidePanel();
		bottomPanel=StaticPanel.getBottomPanel();
		/**
		 * 获得媒体播放器组件的实际工作的画布区
		 */
		canvas=MediaPlayerUtil.getCanvas();
		/**
		 * 为canvas添加鼠标事件，注意下面两行代码并不重复，
		 * 只是为了能够全部获得鼠标的点击和移动事件
		 */
		canvas.setFocusable(false);
		canvas.addMouseMotionListener(new CanvasListener(this));
		canvas.addMouseListener(new CanvasListener(this));
		canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		/**
		 * 获取头部区域
		 */
		headPanel=framePanel.getHeadPanel();
		SubHeadPanel dd=new SubHeadPanel();
		headPanel.add(dd, BorderLayout.CENTER);
		headPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocusInWindow();
			}
			
		});
		/**
		 * 获得主要承载区
		 */
		contain=framePanel.getContain();
		contain.setBorder(new LineBorder(new Color(210, 180, 140), 1, true));
		containLayout=new ContainLayout(contain,embeddedMediaPlayerComponent,menuPanel,sidePanel,bottomPanel);
	//	containLayout.setContainLayout();//布局
	//  containLayout.setLayout2();//布局
		containLayout.setLayout3();//布局
		
        setContentPane(framePanel);//将framePanel设置为内容面板
		setUndecorated(true);//无边框
		setDefaultCloseOperation(3);
		setSize(914, 538);//大小
		setLocationRelativeTo(null);//居中显示
		addKeyListener(new KeyControlListener(this,mediaPlayer));
		setVisible(true);//可见
		////////////////////////
		MediaPlayerUtil.getMediaPlayer().enableOverlay(true);
		ShowOverLayer.getShowOverLayer().start();
	}
	/**
	 * 获得自定义窗口面板
	 * @return framePanel
	 */
	public static FramePanel getFramePanel() {
		return framePanel;
	}
	/**
	 * 获得framePanel的主要承载区 
	 * @return contain
	 */
	public static JComponent getContain() {
		return contain;
	}

	/**
	 * 设置窗口背景
	 * @param bgImage
	 */
	public void setBgImage(Image bgImage) {
		this.bgImage = bgImage;
	}
	/**
	 * 获得整个界面的父窗口
	 * @return MainWindow2对象
	 */
	public static MainWindow getFatherFrame(){
		System.out.println("为空？"+(MainWindow.getFramePanel()==null));
		return (MainWindow) MainWindow.getFramePanel().getParent();
	}
	/**
	 * 实现Observer接口的update方法
	 * 接受来自主题的通知并完成相应的动作
	 * arg0：指明主题对象
	 * arg1：指明通知对象
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		Image image=ImageIconUtil.getImage(MainWindow.class.getResource("/com/siiruo/skin/images/"+arg1.toString()+".jpg"));
		MainWindow.getFramePanel().setBackground(image);//完成换肤功能
	}
	public static MainWindow getMainWindow(){
		if(mainWindow==null){
			mainWindow=new MainWindow();
		}
		return mainWindow;
	}
}
