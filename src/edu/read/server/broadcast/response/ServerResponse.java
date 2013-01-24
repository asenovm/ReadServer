package edu.read.server.broadcast.response;

import edu.read.server.broadcast.BroadcastServer;
import edu.read.server.broadcast.request.ClientRequest;

public abstract class ServerResponse {

	protected final ClientRequest request;

	protected ServerResponse(final ClientRequest request) {
		this.request = request;
	}

	/**
	 * A NULL {@link ServerResponse} object
	 * 
	 * @author martin
	 * 
	 */
	public static class SimpleServerResponse extends ServerResponse {

		protected SimpleServerResponse(ClientRequest request) {
			super(request);
		}

		/**
		 * empty implementation
		 */
		@Override
		public void execute(BroadcastServer server) {
			// blank
		}

	}

	/**
	 * Creates a new response to the <tt>request</tt> given.
	 * 
	 * @param request
	 *            the request for which a response is to be initiated.
	 * @return a response for the <tt>request</tt> given.
	 */
	public static ServerResponse from(final ClientRequest request) {
		switch (request.getType()) {
		case REGISTER:
			return new RegisterResponse(request);
		case ANSWERED:
			return new AnswerResponse(request);
		case UNREGISTER:
			return new UnregisterResponse(request);
		default:
			return new SimpleServerResponse(request);
		}
	}

	/**
	 * Executes the respective response.
	 * 
	 * @param server
	 *            the {@link BroadcastServer} instance on which the response is
	 *            to be executed.
	 */
	public abstract void execute(final BroadcastServer server);
}
