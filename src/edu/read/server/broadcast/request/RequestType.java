package edu.read.server.broadcast.request;

public enum RequestType {
	REGISTER, ANSWERED, UNREGISTER, UNKNOWN;

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
