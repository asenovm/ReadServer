package edu.read.server.broadcast.request;

import java.net.InetAddress;

import edu.read.server.broadcast.Sender;

public class ClientRequest {

	private final RequestReader reader;

	/**
	 * Creates a new {@link ClientRequest} instance
	 * 
	 * @param reader
	 *            the reader that is to be used for retrieving the client
	 *            request
	 */
	public ClientRequest(final RequestReader reader) {
		this.reader = reader;
	}

	/**
	 * Returns the status code of the request
	 * 
	 * @return the status code of the request
	 */
	public int getStatusCode() {
		return reader.getStatusCode();
	}

	/**
	 * Returns the request message
	 * 
	 * @return the request message
	 */
	public String getMessage() {
		return reader.getMessage();
	}

	/**
	 * Returns the {@link RequestType} corresponding to the request status code
	 * 
	 * @return the {@link RequestType} corresponding to the request status code
	 */
	public RequestType getType() {
		return RequestType.from(getStatusCode());
	}

	/**
	 * Returns the {@link InetAddress} from which the request originates
	 * 
	 * @return the {@link InetAddress} from which the request originates
	 */
	public InetAddress getAddress() {
		return reader.getAddress();
	}

	/**
	 * Returns the id of the sender of the request
	 * 
	 * @return the string representation of the id of the sender of the request
	 */
	public Sender getSender() {
		return new Sender(reader.getClientId(), reader.getClientUid());
	}

	@Override
	public String toString() {
		return getStatusCode() + ", " + getMessage();
	}

}
