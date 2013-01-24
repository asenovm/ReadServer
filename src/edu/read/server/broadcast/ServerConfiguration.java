package edu.read.server.broadcast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerConfiguration {

	private static final String TAG = ServerConfiguration.class.getSimpleName();

	private static final boolean DEFAULT_CONFIG_ECHO_MODE = true;

	private static final int DEFAULT_CONFIG_PORT_NUMBER = 65535;

	private static final String KEY_CONFIGURATION_PORT_NUMBER = "serverPort";

	private static final String KEY_CONFIGURATION_ECHO_MODE = "echoMode";

	private final Map<String, String> configuration;

	/**
	 * Creates a new {@link ServerConfiguration} instance using the parameters
	 * specified in the file given.
	 * 
	 * @param filePath
	 *            the absolute path on the file system to the file with the
	 *            configuration options.
	 * @return the {@link ServerConfiguration} instance that has been created
	 */
	public static ServerConfiguration fromFile(final String filePath) {
		return new ServerConfiguration(filePath);
	}

	/**
	 * Creates a new {@link ServerConfiguration} instance with default
	 * configuration.
	 * 
	 * @return the {@link ServerConfiguration} instance that has been created
	 */
	public static ServerConfiguration newDefaultConfiguration() {
		return new ServerConfiguration(new String());
	}

	private ServerConfiguration(final String filePath) {
		configuration = new HashMap<String, String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filePath));
			while (scanner.hasNext()) {
				final String nextLine = scanner.next();
				final String[] words = nextLine.split("=");
				configuration.put(words[0], words[1]);
			}
		} catch (FileNotFoundException ex) {
			LogUtil.logError(TAG, ex.getMessage());
		}
	}

	/**
	 * Returns the port number that the server is to be using
	 * 
	 * @return the port number that the server is to be using
	 */
	public int getPortNumber() {
		if (configuration.containsKey(KEY_CONFIGURATION_PORT_NUMBER)) {
			return Integer.parseInt(configuration
					.get(KEY_CONFIGURATION_PORT_NUMBER));
		}
		return DEFAULT_CONFIG_PORT_NUMBER;
	}

	/**
	 * Returns whether or not the server is to be echoing the messages received
	 * even to the originator of the message.
	 * 
	 * @return whether or not the server is to be echoing the messages received
	 *         even to the originator of the message.
	 */
	public boolean isEchoServer() {
		if (configuration.containsKey(KEY_CONFIGURATION_ECHO_MODE)) {
			return Boolean.parseBoolean(configuration
					.get(KEY_CONFIGURATION_ECHO_MODE));
		}
		return DEFAULT_CONFIG_ECHO_MODE;
	}

}
