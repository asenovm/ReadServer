package edu.read.server.broadcast.response;

import edu.read.server.broadcast.BroadcastServer;
import edu.read.server.broadcast.request.ClientRequest;

public class AnswerResponse extends ServerResponse {

	public AnswerResponse(final ClientRequest request) {
		super(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(final BroadcastServer server) {
		server.sendBroadcast(request);
	}

}
