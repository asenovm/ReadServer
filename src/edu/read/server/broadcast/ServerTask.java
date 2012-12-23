package edu.read.server.broadcast;

import java.net.Socket;

import edu.read.server.broadcast.RequestReader.ReadException;

public class ServerTask implements Runnable {

	private final Socket clientSocket;

	private final BroadcastServer server;

	public ServerTask(final BroadcastServer server, final Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			final ClientRequest request = new ClientRequest(
					RequestReader.from(clientSocket));
			final ServerResponse response = ServerResponse.from(request);
			response.execute(server);
		} catch (ReadException e) {
			e.printStackTrace();
		}
	}
}
