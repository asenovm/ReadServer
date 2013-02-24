package edu.read.server.broadcast;

import java.util.UUID;

/**
 * A model class, holding data about the credentials of the sender of a
 * particular request to the server
 * 
 * @author martin
 * 
 */
public class Sender {

	private final String realId;

	private final String generatedId;

	/**
	 * Creates a new model object, holding information about the sender of a
	 * particular request
	 * 
	 * @param senderId
	 *            the real id of the sender that will be shown to the other
	 *            users of the system
	 */
	public Sender(final String senderId) {
		this.realId = senderId;
		generatedId = UUID.randomUUID().toString().split("-")[0];
	}

	/**
	 * Returns the real id of the server, as will be displayed to the other
	 * users of the system
	 * 
	 * @return the real id of the sender, as it will be visible to the other
	 *         users of the system.
	 */
	public String getId() {
		return realId;
	}

	/**
	 * Returns the unique id, that the server associates with the sender
	 * 
	 * @return the unique id, that the server associates with each sender
	 */
	public String getSystemId() {
		return generatedId;
	}
}
