package com.mail.common;


import org.apache.log4j.Logger;
import org.testng.Reporter;


/**
 * 日志类
 * 
 *
 */

public class Log {

	
	private static Logger Log = Logger.getLogger(Log.class.getName());
	
	public static void starTestCase(String testCaseName){
		Log.info("---------     \"" + testCaseName 
				+ "\" 开始执行        ----------------");
	}
	
	public static void endTestCase(String testCaseName){
		Log.info("---------     \"" + testCaseName 
				+ "\" 测试结束        ----------------");
	}
	
	public static void info(String message){
		System.out.println(message);
		//Reporter.log(message);
		Log.info(message);
	}
	public static void error(String message){
		Log.error(message);
	}
	public static void debug(String message){
		Log.debug(message);
	}
	
}
