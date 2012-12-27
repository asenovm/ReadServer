package edu.read.server.broadcast;

import java.io.IOException;

public class EventLoop {

	public static void main(String[] args) throws IOException {
		final BroadcastServer server = BroadcastServer.fromConfiguration("server.conf");
		server.awaitAndHandleConnection();
	}

}
