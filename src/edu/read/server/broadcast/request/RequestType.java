package edu.read.server.broadcast.request;

public enum RequestType {
	/**
	 * {@value}
	 */
	REGISTER,
	/**
	 * {@value}
	 */
	ANSWERED,
	/**
	 * {@value}
	 */
	UNREGISTER,
	/**
	 * {@value}
	 */
	UNKNOWN;

	/**
	 * Retrieves the {@link RequestType} with the respective <tt>ordinal</tt>
	 * given
	 * 
	 * @param ordinal
	 *            the ordinal for which a {@link RequestType} instance is to
	 *            retrieved.
	 * @return a {@link RequestType} instance, corresponding to the
	 *         <tt>ordinal</tt> given.
	 */
	public static RequestType from(int ordinal) {
		switch (ordinal) {
		case 0:
			return REGISTER;
		case 1:
			return ANSWERED;
		case 2:
			return UNREGISTER;
		default:
			return UNKNOWN;
		}
	}

}
