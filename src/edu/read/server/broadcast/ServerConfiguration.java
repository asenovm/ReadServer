package edu.read.server.broadcast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerConfiguration {

	private static final String TAG = ServerConfiguration.class.getSimpleName();

	private static final boolean DEFAULT_CONFIG_ECHO_MODE = false;

	private static final int DEFAULT_CONFIG_PORT_NUMBER = 65535;

	private static final String KEY_CONFIGURATION_PORT_NUMBER = "serverPort";

	private static final String KEY_CONFIGURATION_ECHO_MODE = "echoMode";

	private final Map<String, String> configuration;

	public static ServerConfiguration fromFile(final String filePath) {
		return new ServerConfiguration(filePath);
	}

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

	public int getPortNumber() {
		if (configuration.containsKey(KEY_CONFIGURATION_PORT_NUMBER)) {
			return Integer.parseInt(configuration
					.get(KEY_CONFIGURATION_PORT_NUMBER));
		}
		return DEFAULT_CONFIG_PORT_NUMBER;
	}

	public boolean isEchoServer() {
		if (configuration.containsKey(KEY_CONFIGURATION_ECHO_MODE)) {
			return Boolean.parseBoolean(configuration
					.get(KEY_CONFIGURATION_ECHO_MODE));
		}
		return DEFAULT_CONFIG_ECHO_MODE;
	}

}
