package edu.read.server.broadcast;

import java.io.IOException;

import edu.read.server.broadcast.BroadcastServer.ServerException;

public class EventLoop {

	private static final String TAG = EventLoop.class.getSimpleName();

	private static final String CONFIGURATION_FILE = "server.conf";

	public static void main(String[] args) throws IOException {
		final BroadcastServer server = BroadcastServer
				.fromConfiguration(CONFIGURATION_FILE);
		tryAwaitConnection(server);
	}

	private static void tryAwaitConnection(final BroadcastServer server) {
		try {
			server.awaitAndHandleConnection();
		} catch (ServerException ex) {
			reportError(ex);
		}
	}

	private static void reportError(ServerException ex) {
		LogUtil.logError(TAG, "fatal " + ex.getCause());
	}

}
