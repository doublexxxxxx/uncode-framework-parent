package com.uncodeframework.core.log4j;

import org.apache.log4j.Logger;

public class Main {

	public static Logger log = Logger.getLogger(Main.class);
	
	public static Logger testLog = Logger.getLogger("TestLog");
	
	public static void main(String[] args) {
		
		log.debug("this log debug message!");
		
		log.error("this log error message!");
		
		testLog.debug("this testlog debug message!");
		
		testLog.error("this testlog error message!");
	}
}
