package edu.read.server.broadcast;

import java.io.IOException;
import java.net.Socket;

import edu.read.server.broadcast.request.ClientRequest;
import edu.read.server.broadcast.request.RequestReader;
import edu.read.server.broadcast.request.RequestReader.ReadException;
import edu.read.server.broadcast.response.ServerResponse;

public class ServerTask implements Runnable {

	private static final String TAG = ServerTask.class.getSimpleName();

	private final Socket clientSocket;

	private final BroadcastServer server;

	/**
	 * Creates a new {@link ServerTask} instance using the parameters given
	 * 
	 * @param server
	 *            the {@link BroadcastServer} instance that is to be used for
	 *            executing the server responses
	 * @param clientSocket
	 *            the socket from which the request originates.
	 */
	public ServerTask(final BroadcastServer server, final Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.server = server;
	}

	/**
	 * Parses the client request and fires the respective response.
	 */
	@Override
	public void run() {
		try {
			final ClientRequest request = new ClientRequest(
					RequestReader.from(clientSocket));
			final ServerResponse response = ServerResponse.from(request);
			response.execute(server);
			clientSocket.close();
		} catch (ReadException ex) {
			LogUtil.logError(TAG, ex.getMessage());
		} catch (IOException ex) {
			LogUtil.logError(TAG, ex.getMessage());
		}
	}
}
