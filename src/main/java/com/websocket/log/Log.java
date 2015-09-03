package com.websocket.log;

import org.apache.log4j.Logger;

public class Log {
	
	private Log() {
		
	}
	
	public static void debug(String msg) {
		Logger logger = getLogger();
		logger.debug(msg);
	}
	
	public static void info(String msg) {
		Logger logger = getLogger();
		logger.info(msg);
	}
	
	public static void trace(String msg) {
		Logger logger = getLogger();
		logger.trace(msg);
	}
	
	public static void fatal(String msg) {
		Logger logger = getLogger();
		logger.fatal(msg);
	}
	
	public static void warn(String msg) {
		Logger logger = getLogger();
		logger.warn(msg);
	}
	
	public static void error(String msg,Throwable e) {
		Logger logger = getLogger();
		 if (msg != null && msg.trim().length() > 0) {
	            logger.error(msg, e);
	        } else {
	            logger.error(e.toString(), e);
	        }
	}
	
	private static Logger getLogger() {
		try {
			String className = Thread.currentThread().getStackTrace()[3].getClassName();
			Class<?> clazz = Class.forName(className);
			return Logger.getLogger(clazz);
		}
		catch(Exception e) {
			Logger localLogger = Logger.getLogger(Log.class);
			localLogger.error("Error while retrieving the logger object.");
		}
		return Logger.getRootLogger();
	}
}
