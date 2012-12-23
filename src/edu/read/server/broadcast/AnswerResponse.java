package edu.read.server.broadcast;

public class AnswerResponse extends ServerResponse {

	public AnswerResponse(final ClientRequest request) {
		super(request);
	}

	@Override
	public void execute(final BroadcastServer server) {
		server.sendBroadcast(request);
	}

}
