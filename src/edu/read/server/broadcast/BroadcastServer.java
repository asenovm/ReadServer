package edu.read.server.broadcast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.read.server.broadcast.request.ClientRequest;
import edu.read.server.broadcast.response.JsonMessage;
import edu.read.server.broadcast.response.MessageSender;

public class BroadcastServer {

	private static final String TAG = BroadcastServer.class.getSimpleName();

	private final ServerConfiguration serverConfiguration;

	private final ExecutorService executor;

	private final ConcurrentHashMap<InetAddress, String> listeners;

	private final MessageSender sender;

	public static BroadcastServer fromDefaultConfiguration() {
		return new BroadcastServer(
				ServerConfiguration.newDefaultConfiguration());
	}

	public static BroadcastServer fromConfiguration(
			final String configurationFilePath) {
		return new BroadcastServer(
				ServerConfiguration.fromFile(configurationFilePath));
	}

	private BroadcastServer(final ServerConfiguration serverConfiguration) {
		this.serverConfiguration = serverConfiguration;
		executor = Executors.newCachedThreadPool();
		listeners = new ConcurrentHashMap<InetAddress, String>();
		sender = new MessageSender();
	}

	public void awaitAndHandleConnection() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(
				serverConfiguration.getPortNumber());
		while (true) {
			final Socket clientSocket = serverSocket.accept();
			executor.execute(new ServerTask(this, clientSocket));
		}
	}

	public void register(final InetAddress address, final String senderId) {
		LogUtil.logInfo(TAG, "registering " + address.toString());
		listeners.put(address, senderId);
	}

	public void sendBroadcast(final ClientRequest request) {
		for (final Map.Entry<InetAddress, String> each : listeners.entrySet()) {
			final InetAddress sendToAddress = each.getKey();
			if (isSendingMessage(request, sendToAddress)) {
				try {
					sender.sendMessageTo(sendToAddress, getJsonMessage(request));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isSendingMessage(final ClientRequest request,
			final InetAddress sendToAddress) {
		return !sendToAddress.equals(request.getAddress())
				|| (sendToAddress.equals(request.getAddress()) && serverConfiguration
						.isEchoServer());
	}

	private JsonMessage getJsonMessage(final ClientRequest request) {
		return new JsonMessage(request.getSender(), request.getMessage());
	}

	public void unregister(final InetAddress address) {
		LogUtil.logInfo(TAG, "unregistering " + address.toString());
		listeners.remove(address);
	}
}
