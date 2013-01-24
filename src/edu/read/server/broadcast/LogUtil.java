package edu.read.server.broadcast;

import java.util.Calendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * A utility class to aid logging
 * 
 * @author martin
 * 
 */
public class LogUtil {

	private static final Logger logger;

	static {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger(LogUtil.class);
	}

	private LogUtil() {
		// blank
	}

	/**
	 * Logs the error message given into the file that has been specified in the
	 * log 4 java properties file.
	 * 
	 * @param tag
	 *            the tag of the file where the message originates from
	 * @param message
	 *            the message that is to be logged.
	 */
	public static void logError(final String tag, final String message) {
		logger.error(getTime() + " " + tag + " " + message);
	}

	/**
	 * Logs the info message given into the file that has been specified in the
	 * log 4 java properties file
	 * 
	 * @param tag
	 *            the tag of the file where the message originates from
	 * @param message
	 *            the message that is to be logged.
	 */
	public static void logInfo(final String tag, final String message) {
		logger.info(getTime() + " " + tag + " " + message);
	}

	private static String getTime() {
		final Calendar currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
		return currentCalendar.getTime().toString();
	}

}
