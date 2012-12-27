package edu.read.server.broadcast.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.read.server.broadcast.response.JsonMessage;

public class RequestReader {

	private static final int READ_BLOCK_SIZE = 512;

	private JsonObject parsedRequest;

	private InetAddress clientAddress;

	public static class ReadException extends Exception {
		private static final long serialVersionUID = -5182828295641516029L;

		public ReadException(Throwable cause) {
			super(cause);
		}
	}

	public static RequestReader from(final Socket socket) throws ReadException {
		try {
			return new RequestReader(socket);
		} catch (IOException ex) {
			throw new ReadException(ex);
		}
	}

	private RequestReader(final Socket socket) throws ReadException,
			IOException {
		this(socket.getInputStream());
		clientAddress = socket.getInetAddress();
	}

	private RequestReader(final InputStream inputStream) throws ReadException {
		try {
			final String request = new String(readFromStream(inputStream))
					.trim();
			final JsonElement element = new JsonParser().parse(new String(
					request));
			parsedRequest = element.getAsJsonObject();
		} catch (IOException ex) {
			throw new ReadException(ex);
		}
	}

	public InetAddress getAddress() {
		return clientAddress;
	}

	public String getMessage() {
		return parsedRequest.get(JsonMessage.KEY_MESSAGE).getAsString();
	}

	public String getClientId() {
		return parsedRequest.get(JsonMessage.KEY_ID).getAsString();
	}

	public int getStatusCode() {
		return parsedRequest.get(JsonMessage.KEY_STATUS_CODE).getAsInt();
	}

	private byte[] readFromStream(final InputStream inputStream)
			throws IOException {
		final byte[] buffer = new byte[READ_BLOCK_SIZE];
		byte[] result = new byte[0];
		int read = 0;
		while ((read = inputStream.read(buffer)) > 0) {
			int oldLength = result.length;
			result = Arrays.copyOf(result, oldLength + buffer.length);
			System.arraycopy(buffer, 0, result, oldLength, read);
		}
		return result;
	}

}
