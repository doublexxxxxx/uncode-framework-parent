package org.uncodeframework.core.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	
	public static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static Logger testLogger = LoggerFactory.getLogger("TestLogger");

	public static void main(String[] args) {
		logger.debug("this logback debug message!");
		
		logger.error("this logback error message!");
		
		testLogger.debug("this logback testLogger debug message!");
		
		testLogger.error("this logback testLogger error message!");
	}
}
