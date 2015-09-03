package com.websocket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConversion {

	public static String getCurrentDateAndTime(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		return sdf.format(date);
	}
	public static String getCurrentDate(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	
	public static String getCurrentTime(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		return sdf.format(date);
	}
	
}
