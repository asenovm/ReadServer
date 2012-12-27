package edu.read.server.broadcast.response;

import edu.read.server.broadcast.BroadcastServer;
import edu.read.server.broadcast.request.ClientRequest;

public abstract class ServerResponse {

	protected final ClientRequest request;

	protected ServerResponse(final ClientRequest request) {
		this.request = request;
	}

	public static ServerResponse from(final ClientRequest request) {
		switch (request.getType()) {
		case REGISTER:
			return new RegisterResponse(request);
		case ANSWERED:
			return new AnswerResponse(request);
		case UNREGISTER:
			return new UnregisterResponse(request);
		}
		return null;
	}

	public abstract void execute(final BroadcastServer server);
}
