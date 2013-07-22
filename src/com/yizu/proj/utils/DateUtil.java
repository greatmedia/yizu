package com.yizu.proj.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private final static String  partten ="yyyy-MM-dd HH:mm:ss";
	public static String getCurrentTime() {
		DateFormat df=new SimpleDateFormat(partten);
		return df.format(new Date());
	}
	/***
	 * 将long时间转换成默认格式
	 * @param 
	 * @return
	 */
	public static String formatLongTime(Long time){
		String dateString =null;
		DateFormat df=new SimpleDateFormat(partten);
		dateString = df.format(new Date(time));        return dateString;
	}
	/**
	 * 将long时间转换成指定的模式
	 * @param time
	 * @param partten
	 * @return
	 */
	public static String formatLongTime(Long time,String partten){
		String dateString =null;
		DateFormat df=new SimpleDateFormat(partten);
		if(time==null)return "";
		dateString = df.format(new Date(time));
		return dateString;
	}
	/**
	 * 获得最小时间:将时间格式为“0000-00-00”格式的字符串转换成当天最大时间“0000-00-00 00:00:00”
	 * @param dateStr
	 * @return
	 */
	public static String getMinDate(String dateStr){
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatLongTime(date.getTime());
	}
	/**
	 * 获得最大时间:将时间格式为“0000-00-00”格式的字符串转换成当天最大时间“0000-00-00 23:59:59”
	 * @param dateStr
	 * @return
	 */
	public static String getMaxDate(String dateStr){
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatLongTime(date.getTime()+86400000-1);
	}
/**
	 * 将时间格式化为本地样式
	 * @param date
	 * @param partten
	 * @return
	 */
	public static String  formatDate(Date date,String partten){
	    DateFormat df=new SimpleDateFormat(partten);
	    String dateStr=df.format(date);
		return dateStr;
	}
	/**
	 * 获取比当前日期早多少天或者晚多少天的日期 例如 前五天 －5 后五天 5
	 * 
	 * @param days
	 * @param format
	 *            返回日期的格式
	 * @return 格式化好的字符串
	 */
	public static String DateBefAft(int days, String format) {
		//
		if (format == null || "".equals(format)){
			format = "yyyy-MM-dd";
		}
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		now.add(Calendar.DAY_OF_YEAR, -days);
		return formatter.format(now.getTime());
	}
	
	/**
	 * 接受YYYY-MM-DD的日期字符串参数,返回两个日期相差的天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static long getDistDates(String start, String end) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
		return getDistDates(startDate, endDate);
	}
	
	/**
	 * 返回两个日期相差的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static long getDistDates(Date startDate, Date endDate) {
		long totalDate = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timestart = calendar.getTimeInMillis();
		calendar.setTime(endDate);
		long timeend = calendar.getTimeInMillis();
		totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
		return totalDate;
	}
	/**
	 * 将Date转化成时间戳
	 * 返回秒数
	 */
	public static long getDateTime(Date d){
		return d.getTime();
	}
}
