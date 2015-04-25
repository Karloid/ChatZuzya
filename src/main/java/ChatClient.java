import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class ChatClient {
	public static void main(String[] args) {
		URI uri = URI.create("ws://localhost:" + ChatServer.getPort() + ChatServer.getChatPath());

		WebSocketClient client = new WebSocketClient();

		try {
			client.start();
			ChatSocket socket = new ChatSocket();

			Future<Session> fut = client.connect(socket, uri);
			Session session = fut.get();
			session.getRemote().sendString("Hello World");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
