package es.urjc.etsii.dad.campfire;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class Client{

	private final WebSocketSession session;
	private final int clientId;

	public Client(int clientId, WebSocketSession session) {
		this.clientId = clientId;
		this.session = session;
	}

	public int getClientId() {
		return this.clientId;
	}

	public WebSocketSession getSession() {
		return this.session;
	}

	public void sendMessage(String msg) throws Exception {
		this.session.sendMessage(new TextMessage(msg));
	}

}