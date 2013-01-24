package edu.read.server.broadcast.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JsonMessage {

	public static final String KEY_STATUS_CODE = "status";

	public static final String KEY_ID = "id";

	public static final String KEY_MESSAGE = "message";

	private final JsonObject message;

	/**
	 * Creates a new json message from the parameters given.
	 * 
	 * @param sender
	 *            the id of the sender of the message
	 * @param message
	 *            the message that is to be sent
	 */
	public JsonMessage(final String sender, final String message) {
		this.message = new JsonObject();
		this.message.add(KEY_ID, new JsonPrimitive(sender));
		this.message.add(KEY_MESSAGE, new JsonPrimitive(message));
	}

	@Override
	public String toString() {
		return new Gson().toJson(message);
	}
}
