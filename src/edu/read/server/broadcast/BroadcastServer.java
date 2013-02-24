package edu.read.server.broadcast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.read.server.broadcast.request.ClientRequest;
import edu.read.server.broadcast.response.JsonMessage;
import edu.read.server.broadcast.response.MessageSender;

/**
 * 
 * @author martin
 * 
 */
public class BroadcastServer {

	private static final String TAG = BroadcastServer.class.getSimpleName();

	private final ServerConfiguration serverConfiguration;

	private final ExecutorService executor;

	private final ConcurrentHashMap<InetAddress, Sender> listeners;

	private final MessageSender sender;

	/**
	 * An exception from higher level to be used for wrapping up low-level
	 * exceptions that occur while working with sockets.
	 * 
	 * @author martin
	 * 
	 */
	public static class ServerException extends Exception {
		private static final long serialVersionUID = 5880993957637491509L;

		public ServerException(Throwable cause) {
			super(cause);
		}

	}

	/**
	 * A factory method returning a default-configured server instance
	 * 
	 * @return a default-configured {@link BroadcastServer} instance
	 */
	public static BroadcastServer fromDefaultConfiguration() {
		return new BroadcastServer(
				ServerConfiguration.newDefaultConfiguration());
	}

	/**
	 * A factory method returning a {@link BroadcastServer} that has been
	 * configured with respect to the values set in the
	 * <tt>configurationFilePath</tt> given
	 * 
	 * @param configurationFilePath
	 *            the absolute path on the file system to the file that contains
	 *            the configuration options for the server
	 * @return a {@link BroadcastServer} instance, that has been configured with
	 *         respect to the values that has been set in the
	 *         <tt>configurationFilePath</tt> given
	 */
	public static BroadcastServer fromConfiguration(
			final String configurationFilePath) {
		return new BroadcastServer(
				ServerConfiguration.fromFile(configurationFilePath));
	}

	private BroadcastServer(final ServerConfiguration serverConfiguration) {
		this.serverConfiguration = serverConfiguration;
		executor = Executors.newCachedThreadPool();
		listeners = new ConcurrentHashMap<InetAddress, Sender>();
		sender = new MessageSender();
	}

	/**
	 * Infinitely waits for a new connection and spawns a new thread to handle
	 * it.
	 * 
	 * @throws ServerException
	 *             in case the remote client cannot connect for some reason.
	 */
	public void awaitAndHandleConnection() throws ServerException {
		try {
			final ServerSocket serverSocket = new ServerSocket(
					serverConfiguration.getPortNumber());
			while (true) {
				final Socket clientSocket = serverSocket.accept();
				executor.execute(new ServerTask(this, clientSocket));
			}
		} catch (IOException ex) {
			throw new ServerException(ex);
		}
	}

	/**
	 * Registers a new client within the server
	 * 
	 * @param address
	 *            the address of the remote client that is to be registered
	 * @param senderId
	 *            the id of the remote client
	 */
	public void register(final InetAddress address, final Sender sender) {
		LogUtil.logInfo(TAG, "registering " + address.toString());
		listeners.put(address, sender);
	}

	/**
	 * Retransmits the <tt>request</tt> received to all the registered clients.
	 * 
	 * @param request
	 *            the request that is to be retransmitted to all the clients
	 */
	public void sendBroadcast(final ClientRequest request) {
		for (final Entry<InetAddress, Sender> each : listeners.entrySet()) {
			final InetAddress sendToAddress = each.getKey();
			final Sender senderData = each.getValue();
			if (isSendingMessage(request, sendToAddress)) {
				try {
					sender.sendMessageTo(sendToAddress,
							getJsonMessage(senderData, request.getMessage()));
				} catch (IOException ex) {
					LogUtil.logError(TAG, ex.getMessage());
				}
			}
		}
	}

	private boolean isSendingMessage(final ClientRequest request,
			final InetAddress sendToAddress) {
		return !sendToAddress.equals(request.getAddress())
				|| serverConfiguration.isEchoServer();
	}

	private JsonMessage getJsonMessage(final Sender sender, final String message) {
		return new JsonMessage(sender, message);
	}

	/**
	 * Unregisters the <tt>address</tt> given from the server.
	 * 
	 * @param address
	 *            the address that is to be unregistered from the server.
	 */
	public void unregister(final InetAddress address) {
		LogUtil.logInfo(TAG, "unregistering " + address.toString());
		listeners.remove(address);
	}
}
