package com.siiruo.controller;

import java.awt.EventQueue;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import com.siiruo.views.MainWindow;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
/**
 * 程序的主入口
 * @author SIIRUO
 * @version 1.0
 */
public class MainClass {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(LoadTask.getLoadTask());
	}
}

