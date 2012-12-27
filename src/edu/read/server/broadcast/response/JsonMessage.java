package edu.read.server.broadcast.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JsonMessage {

	public static final String KEY_STATUS_CODE = "status";

	public static final String KEY_ID = "id";

	public static final String KEY_MESSAGE = "message";

	private final JsonObject message;

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
