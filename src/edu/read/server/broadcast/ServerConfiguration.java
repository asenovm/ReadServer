package edu.read.server.broadcast;

public class ServerConfiguration {

	private static final int DEFAULT_CONFIG_PORT_NUMBER = 65535;

	private final int portNumber;

	public static ServerConfiguration newDefaultConfiguration() {
		return new ServerConfiguration(DEFAULT_CONFIG_PORT_NUMBER);
	}

	private ServerConfiguration(int portNumber) {
		this.portNumber = portNumber;
	}

	public int getPortNumber() {
		return portNumber;
	}

}
