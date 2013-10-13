package edu.read.server.broadcast.response;

import edu.read.server.broadcast.BroadcastServer;
import edu.read.server.broadcast.request.ClientRequest;

public class RegisterResponse extends ServerResponse {

	public RegisterResponse(final ClientRequest request) {
		super(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(final BroadcastServer server) {
		System.out.println("sender is " + request.getSender().getUid());
		server.register(request.getAddress(), request.getSender());
	}

}
