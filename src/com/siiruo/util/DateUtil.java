package com.siiruo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {  
    private static final String DATE_FORMAT_Y_M_D="yyyy-MM-dd";
    private static final String DATE_FORMAT_Y_M_D_H_M_S="yyyy-MM-dd HH:mm:ss";
//    private static DateUtil dateUtil;
//    
//    public static synchronized DateUtil getInstance(){
//    	if(dateUtil==null) {
//    		dateUtil=new DateUtil();
//    	}
//    	return dateUtil;
//    }
    /** 
     * 返回unix时间戳 (1970年至今的秒数) 
     * @return 
     */ 
    public static long getUnixStamp(){  
            return System.currentTimeMillis()/1000;  
    }  
    /** 
     * 得到今天的日期 
     * @return 
     */ 
    public static  String getTodayDate(){  
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_Y_M_D);  
            String date = sdf.format(new Date());  
            return date;  
    }  
    /** 
     * 相对于今天的日期，offse是指相对于今天的偏移量，负数则偏左，正数则偏右
     * @return 
     */ 
    public static String getSomeDate(int offset) {  
            Calendar calendar = Calendar.getInstance();    
            calendar.add(Calendar.DATE,offset);  
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_Y_M_D);  
            String yestoday = sdf.format(calendar.getTime());  
            return yestoday;  
    }  
    /** 
     * 得到昨天的日期 
     * @return 
     */ 
    public static String getYestoryDate() {  
            return getSomeDate(-1);  
    }  
    
    /** 
     * 得到明天的日期 
     * @return 
     */ 
    public static String getTomorrowDate() {  
            return getSomeDate(1);  
    }  
   
       
    /** 
     * 时间戳转化为时间格式 
     * @param timeStamp 
     * @return 
     */ 
    public static String timeStampToStr(long timeStamp) {  
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_Y_M_D_H_M_S);  
            String date = sdf.format(timeStamp * 1000);  
            return date;  
    }  
       
    /** 
     * 得到日期   yyyy-MM-dd 
     * @param timeStamp  时间戳 
     * @return 
     */ 
    public static String formatDate(long timeStamp) {     
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_Y_M_D);  
            String date = sdf.format(timeStamp*1000);  
            return date;  
    }  
       
    /** 
     * 得到时间  HH:mm:ss 
     * @param timeStamp   时间戳 
     * @return 
     */ 
    public static String getTime(long timeStamp) {    
            String time = null;  
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_Y_M_D_H_M_S);  
            String date = sdf.format(timeStamp * 1000);  
            String[] split = date.split("\\s");  
            if ( split.length > 1 ){  
                    time = split[1];  
            }  
            return time;  
    }  
       
    /** 
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前 
     *  
     * @param timeStamp 
     * @return 
     */ 
    public static String convertTimeToFormat(long timeStamp) {  
            long curTime =System.currentTimeMillis() / (long) 1000 ;  
            long time = curTime - timeStamp;  
            if (time < 60 && time >= 0) {  
                    return "刚刚";  
            } else if (time >= 60 && time < 3600) {  
                    return time / 60 + "分钟前";  
            } else if (time >= 3600 && time < 3600 * 24) {  
                    return time / 3600 + "小时前";  
            } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {  
                    return time / 3600 / 24 + "天前";  
            } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {  
                    return time / 3600 / 24 / 30 + "个月前";  
            } else if (time >= 3600 * 24 * 30 * 12) {  
                    return time / 3600 / 24 / 30 / 12 + "年前";  
            } else {  
                    return "刚刚";  
            }  
    }  
       
    /** 
     * 将一个时间戳转换成提示性时间字符串，(多少分钟) 
     *  
     * @param timeStamp 
     * @return 
     */ 
    public static String timeStampToFormat(long timeStamp) {  
            long curTime =System.currentTimeMillis() / (long) 1000 ;  
            long time = curTime - timeStamp;  
            return time/60 + "";  
    } 
    /**
     * 将给定的日期转换成默认的字符串形式
     * @param date
     * @return
     */
    public static String toStringDay(Date date){ 
    	 return toStringDay(date,DateUtil.DATE_FORMAT_Y_M_D);
    }
    /**
     * 
     * @param date
     * @return
     */
    public static String toStringTimeDay(Date date){ 
    	//System.out.println("时间："+toStringDay(date,DateUtil.DATE_FORMAT_Y_M_D_H_M_S));
   	 return toStringDay(date,DateUtil.DATE_FORMAT_Y_M_D_H_M_S);
   }
    
    /**
     * 将给定的字符串形式日期转换成默认日期
     * @param date
     * @return
     */
    public static Date toDateDay(String strDate){ 
    	 SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_Y_M_D);  
    	 Date date =null;
         try {
		date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
    	 return date;
    }

    /**
     * 将给定的日期转换成指定的字符串形式
     * @param date
     * @return
     */
    public static String toStringDay(Date date,String stringFormat){
    	SimpleDateFormat sdf;
		try {
			sdf = new SimpleDateFormat(stringFormat);
		} catch (Exception e) {
			return null;
		}  
        String stringDay = sdf.format(date);  
   	 	return stringDay;
    }
 
}