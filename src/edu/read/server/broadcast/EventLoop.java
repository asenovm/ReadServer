package edu.read.server.broadcast;

import java.io.IOException;

import edu.read.server.broadcast.BroadcastServer.ServerException;

/**
 * The main loop of the application.
 * 
 * @author martin
 * 
 */
public class EventLoop {

	/**
	 * {@value}
	 */
	private static final String TAG = EventLoop.class.getSimpleName();

	/**
	 * The path to the configuration file of the server - {@value}
	 */
	private static final String FILE_PATH_CONFIGURATION = "server.conf";

	public static void main(String[] args) throws IOException {
		final BroadcastServer server = BroadcastServer
				.fromConfiguration(FILE_PATH_CONFIGURATION);
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
		ex.printStackTrace();
		LogUtil.logError(TAG, "fatal " + ex.getCause());
	}

}
