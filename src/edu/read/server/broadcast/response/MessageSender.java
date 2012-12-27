package edu.read.server.broadcast.response;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import edu.read.server.broadcast.LogUtil;

public class MessageSender {
	
	private static final String TAG = MessageSender.class.getSimpleName();

	public void sendMessageTo(final InetAddress to, final JsonMessage message)
			throws IOException {
		final Socket clientSocket = new Socket(to, 65534);
		final OutputStream outputStream = clientSocket.getOutputStream();
		System.out.println("writing " + message.toString());
		LogUtil.logInfo(TAG, "sending message " + message.toString() + "\n");
		outputStream.write(message.toString().getBytes());
		outputStream.flush();
		outputStream.close();
	}

}
