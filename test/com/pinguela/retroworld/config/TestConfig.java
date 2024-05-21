package com.pinguela.retroworld.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestConfig {
	
	private static Logger logger = LogManager.getLogger(TestConfig.class);
	
	public TestConfig() {
		
	}
	
	public void testDBConfig() {
		String dbURL = ConfigurationParametersManager.getValue("db.url");
		logger.info("DB config url = "+dbURL);
	}
	
	public static void main(String[]args) {
		TestConfig test = new TestConfig();
		test.testDBConfig();
	}
}
