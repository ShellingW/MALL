package com.dayuanit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date add(Date date, int minute) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	public static Date sub(Date date, int minute) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -minute);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		Date date = DateUtil.sub(new Date(), 30);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
	}

}
