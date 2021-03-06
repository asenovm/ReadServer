package edu.read.server.broadcast.response;

import edu.read.server.broadcast.BroadcastServer;
import edu.read.server.broadcast.request.ClientRequest;

public class UnregisterResponse extends ServerResponse {

	public UnregisterResponse(final ClientRequest request) {
		super(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(final BroadcastServer server) {
		server.unregister(request.getAddress());
	}

}
