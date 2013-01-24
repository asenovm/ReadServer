package edu.read.server.broadcast.response;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import edu.read.server.broadcast.LogUtil;

public class MessageSender {

	private static final String TAG = MessageSender.class.getSimpleName();

	private static final int CLIENT_PORT_LISTENING = 65534;

	/**
	 * Sends the <tt>message</tt> given to the <tt>address</tt> provided.
	 * 
	 * @param to
	 *            the address of the received of the message
	 * @param message
	 *            the message that is to be sent.
	 * @throws IOException
	 *             in case the message cannot be sent to the address specified.
	 */
	public void sendMessageTo(final InetAddress to, final JsonMessage message)
			throws IOException {
		LogUtil.logInfo(TAG, "sending message " + message.toString());
		final Socket clientSocket = new Socket(to, CLIENT_PORT_LISTENING);
		final OutputStream outputStream = clientSocket.getOutputStream();
		outputStream.write(message.toString().getBytes());
		outputStream.flush();
		outputStream.close();
	}

}
