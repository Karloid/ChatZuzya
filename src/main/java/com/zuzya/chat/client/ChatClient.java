package com.zuzya.chat.client;

import com.zuzya.chat.server.ChatServer;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.Future;

public class ChatClient {
	public static void main(String[] args) {
		URI uri = URI.create("ws://localhost:" + ChatServer.getPort() + ChatServer.getChatPath());

		WebSocketClient client = new WebSocketClient();

		try {
			client.start();
			ChatClientSocket socket = new ChatClientSocket();

			Future<Session> fut = client.connect(socket, uri);
			Session session = fut.get();
			readUserInput(session.getRemote());
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

	private static void readUserInput(RemoteEndpoint remote) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true) {
			String input = in.next();
			if (input.toUpperCase().equals("EXIT"))
				break;
			remote.sendString(input);
		}
	}
}
