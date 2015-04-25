package com.zuzya.chat.server;

import com.zuzya.chat.server.core.Engine;
import com.zuzya.chat.server.core.OnNewMessageListener;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;

public class ChatServerSocket extends WebSocketAdapter implements OnNewMessageListener {

	private Engine engine;

	@Override
	public void onWebSocketConnect(Session sess) {
		super.onWebSocketConnect(sess);
		System.out.println("Socket Connected: " + sess);
		engine = Engine.getInstance();
		engine.addOnNewMessageListener(this);
		sendString("-> Welcome to Zuzya");
	}

	@Override
	public void onWebSocketText(String message) {
		super.onWebSocketText(message);
		System.out.println("Server received TEXT message: " + message);
		engine.handleNewMessage(message);
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		super.onWebSocketClose(statusCode, reason);
		System.out.println("Socket Closed: +[" + statusCode + "] " + reason);
		engine.removeOnNewMessageListener(this);
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		super.onWebSocketError(cause);
		cause.printStackTrace(System.err);
		engine.removeOnNewMessageListener(this);
	}

	private void sendString(String string) {
		try {
			getRemote().sendString(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onNewMessage(String message) {
		sendString(message);
	}
}
