package com.dayuanit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	
	private final static Logger logger =LoggerFactory.getLogger(TestLog.class);
	
	public static void test() {
		logger.trace("trace test ...");
		logger.debug("debug test ...");
		logger.info("info test ...");
		logger.warn("warn test ...");
		logger.error("error test ...");
	}
	
	public static void main(String[] args) {
		
		test();
		
//		logger.trace("trace log ...");
//		logger.debug("debug log ...");
//		logger.info("info log ...");
//		logger.warn("warn log ...");
//		logger.error("error log ...");
//		
//		int num = 12;
//		String  msg = "买了12件";
//		String  xxDetil = "买了12件";
//		
//		try {
//			int a = num/0;
//		} catch(Exception e) {
//			logger.error("计算失败 {}", e.getMessage(), e);
//		}
//		
//		
//		logger.info("info log num {}, msg:{}, detil {}", num, msg, xxDetil);
	}

}
