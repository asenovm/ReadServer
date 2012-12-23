package edu.read.server.broadcast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BroadcastServer {

	private final ServerConfiguration serverConfiguration;

	private final ExecutorService executor;

	private final ConcurrentHashMap<InetAddress, String> listeners;

	public static BroadcastServer fromDefaultOptions() {
		return new BroadcastServer(
				ServerConfiguration.newDefaultConfiguration());
	}

	private BroadcastServer(final ServerConfiguration serverConfiguration) {
		this.serverConfiguration = serverConfiguration;
		executor = Executors.newCachedThreadPool();
		listeners = new ConcurrentHashMap<InetAddress, String>();
	}

	public void waitAndHandleConnection() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(
				serverConfiguration.getPortNumber());
		while (true) {
			final Socket clientSocket = serverSocket.accept();
			executor.execute(new ServerTask(this, clientSocket));
		}
	}

	public void register(final InetAddress address) {
		System.out.println("registering " + address.toString());
		listeners.put(address, "asd ");
	}

	public void sendBroadcast(final ClientRequest request) {
		System.out.println("sending broad cast with message "
				+ request.getMessage());
		for (final Map.Entry<InetAddress, String> each : listeners.entrySet()) {
			try {
				sendMessageTo(each.getKey(), request.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendMessageTo(final InetAddress to, final String message)
			throws IOException {
		final Socket clientSocket = new Socket(to, 65534);
		final OutputStream outputStream = clientSocket.getOutputStream();
		outputStream.write(message.getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
