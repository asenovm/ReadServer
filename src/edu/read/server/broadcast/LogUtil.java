package edu.read.server.broadcast;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {

	private static final Logger logger;

	static {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(LogUtil.class);
	}

	private LogUtil() {
		// blank
	}

	public static void logError(final String tag, final String message) {
		logger.error(tag + " " + message);
	}

	public static void logInfo(final String tag, final String message) {
		logger.info(tag + " " + message);
	}
	
}
