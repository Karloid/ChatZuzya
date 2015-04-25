package com.zuzya.chat.server.core;

import java.util.ArrayList;
import java.util.List;

public class Engine {
	private static Engine singleton;
	private List<OnNewMessageListener> onNewMessageListeners;

	Engine() {
	}

	public synchronized static Engine getInstance() {
		if (singleton == null) {
			singleton = new Engine();
		}
		return singleton;
	}

	public void addOnNewMessageListener(OnNewMessageListener listener) {
		if (onNewMessageListeners == null) {
			onNewMessageListeners = new ArrayList<>();
		}
		onNewMessageListeners.add(listener);
	}

	public void handleNewMessage(final String message) {
	    onNewMessageListeners.stream().forEach(listener -> listener.onNewMessage(message));
	}

	public void removeOnNewMessageListener(OnNewMessageListener listener) {
		if (onNewMessageListeners != null) {
			onNewMessageListeners.remove(listener);
		}

	}
}
