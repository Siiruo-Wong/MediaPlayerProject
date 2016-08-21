package com.siiruo.util;

public class HandlerUtil {
	private static java.text.DecimalFormat df =new java.text.DecimalFormat("#.00");  
	private static double FOUR1024=1024*1024*1024*1024d;
	private static double THREE1024=1024*1024*1024d;
	private static double TWO1024=1024*1024;
	private static double ONE1024=1024;

	/**
	 * 将长整型数据(视频时长毫秒单位)转换成字符形式
	 * @param time
	 * @return
	 */
	public static String getStringTime(long time){
		time=time/1000;
		int hours=(int) (time/3600);
		int minutes=(int) ((time%3600)/60);
		int seconds=(int) ((time%3600)%60);
		return hours+":"+minutes+":"+seconds;
	}
	/**
	 * 将字符串形式的视频时长转换为毫秒单位的长整型
	 * @param time
	 * @return
	 */
	public static long getLongTime(String time){
		time.trim();
		String[] array=time.split(":");
		int hours=Integer.parseInt(array[0]);
		int minutes=Integer.parseInt(array[1]);
		int seconds=Integer.parseInt(array[2]);
		long  total=(hours*3600+minutes*60+seconds)*1000;
		return total;
	}
	/**
	 * 将视频大小(字节)转换成指定的字符串格式
	 * @param size
	 * @return
	 */
	public  static String getStringSize(double size){
		if(size<ONE1024){
			return df.format(size)+"B";
		}else if(size<TWO1024){
			return df.format(size/ONE1024)+"KB";
		}else if(size<THREE1024){
			return df.format(size/TWO1024)+"MB";
		}else if(size<FOUR1024){
			return df.format(size/THREE1024)+"GB";
		}else{
			return df.format(size/FOUR1024)+"TB";
		}
	}
	/**
	 * 将字符串形式的视频大小转换成浮点型的
	 * @param size
	 * @return
	 */
	public  static double getDoubleSize(String size){
		String str=size.replaceAll("\\s*", "");
		String sub=str.substring(0, str.length()-2);
		char m=str.charAt(str.length()-2);
		switch(m){
		case 'K':{
			return Double.parseDouble(sub)*ONE1024;
		}
		case'M':{
			return Double.parseDouble(sub)*TWO1024;
		}
		case'G':{
			return Double.parseDouble(sub)*THREE1024;
		}
		case'T':{
			return Double.parseDouble(sub)*FOUR1024;
		}
		default:{
			sub=str.substring(0, str.length()-1);
			return Double.parseDouble(sub);
		}
		
		}
	}
}
