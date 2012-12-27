package edu.read.server.broadcast.response;

import edu.read.server.broadcast.BroadcastServer;
import edu.read.server.broadcast.request.ClientRequest;

public class RegisterResponse extends ServerResponse {

	public RegisterResponse(final ClientRequest request) {
		super(request);
	}

	@Override
	public void execute(final BroadcastServer server) {
		server.register(request.getAddress(), request.getSender());
	}

}
