package com.pinguela.retroworld.config;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationParametersManager {
	private static final String CONFIG_FILE = "retroworld-cfg.properties";
	private static Logger logger = LogManager.getLogger(ConfigurationParametersManager.class);
	private static Properties propertiesCfg = null;
	
	private ConfigurationParametersManager() {
		
	}
	
	static {
		try {
			Class configurationParametersManagerClass = ConfigurationParametersManager.class;
			ClassLoader classLoader = configurationParametersManagerClass.getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE);
			propertiesCfg = new Properties();
			propertiesCfg.load(inputStream);
			inputStream.close();

			//parameters = Collections.synchronizedMap(properties)

		}catch(Throwable t) {
			logger.fatal("Unable to load system configuration: "+t.getMessage());
			System.exit(0); // ..
		}
	}
	public static final String getValue(String parameterName) {
		
		return propertiesCfg.getProperty(parameterName);
	}
}
