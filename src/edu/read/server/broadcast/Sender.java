package edu.read.server.broadcast;


/**
 * A model class, holding data about the credentials of the sender of a
 * particular request to the server
 * 
 * @author martin
 * 
 */
public class Sender {

	private final String realId;

	private final String uid;

	/**
	 * Creates a new model object, holding information about the sender of a
	 * particular request
	 * 
	 * @param senderId
	 *            the real id of the sender that will be shown to the other
	 *            users of the system
	 */
	public Sender(final String senderId, final String senderUid) {
		this.realId = senderId;
		this.uid = senderUid;
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
	public String getUid() {
		return uid;
	}
}
