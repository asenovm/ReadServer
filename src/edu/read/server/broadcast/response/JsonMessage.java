package edu.read.server.broadcast.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import edu.read.server.broadcast.Sender;

/**
 * A model class for the messages that are being exchanged between the server
 * and its clients
 * 
 * @author martin
 * 
 */
public class JsonMessage {

	/**
	 * {@value}
	 */
	public static final String KEY_STATUS_CODE = "status";

	/**
	 * {@value}
	 */
	public static final String KEY_ID = "id";

	/**
	 * {@value}
	 */
	public static final String KEY_SYSTEM_ID = "systemId";

	/**
	 * {@value}
	 */
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
	public JsonMessage(final Sender sender, final String message) {
		this.message = new JsonObject();

		final String senderId = sender.getId();
		final String systemId = sender.getSystemId();

		this.message.add(KEY_ID, new JsonPrimitive(senderId));
		this.message.add(KEY_SYSTEM_ID, new JsonPrimitive(systemId));
		this.message.add(KEY_MESSAGE, new JsonPrimitive(message));
	}

	@Override
	public String toString() {
		return new Gson().toJson(message);
	}
}
