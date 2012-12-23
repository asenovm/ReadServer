package edu.read.server.broadcast;

import java.net.InetAddress;

public class ClientRequest {

	private final RequestReader reader;

	public ClientRequest(final RequestReader reader) {
		this.reader = reader;
	}

	public int getCode() {
		return reader.getStatusCode();
	}

	public String getMessage() {
		return reader.getMessage();
	}

	public RequestType getType() {
		return RequestType.from(getCode());
	}

	public InetAddress getAddress() {
		return reader.getAddress();
	}

	@Override
	public String toString() {
		return getCode() + ", " + getMessage();
	}

}
