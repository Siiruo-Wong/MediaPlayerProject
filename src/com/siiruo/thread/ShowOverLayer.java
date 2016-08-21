package com.siiruo.thread;

import com.siiruo.util.ConstantUtil;
import com.siiruo.util.MediaPlayerUtil;

public class ShowOverLayer extends Thread {
	private static ShowOverLayer showOverLayer;
	private  ShowOverLayer(Runnable target){
		super(target);
	}
	public synchronized static ShowOverLayer getShowOverLayer(){
		if(showOverLayer==null){
			showOverLayer=new ShowOverLayer(new ShowOverLayerTask());
		}
		return showOverLayer;
	}
}
class ShowOverLayerTask implements Runnable {
	@Override
	public void run() {
		while(true){
				MediaPlayerUtil.getOverLayer().setVisible(false);
			try {
				Thread.sleep(ConstantUtil.TIMER_GAP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
