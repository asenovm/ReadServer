package edu.read.server.broadcast;

public class RegisterResponse extends ServerResponse {

	public RegisterResponse(final ClientRequest request) {
		super(request);
	}

	@Override
	public void execute(final BroadcastServer server) {
		server.register(request.getAddress());
	}

}
