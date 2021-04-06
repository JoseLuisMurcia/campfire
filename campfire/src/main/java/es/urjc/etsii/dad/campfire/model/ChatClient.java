package es.urjc.etsii.dad.campfire.model;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ChatClient{

	private final WebSocketSession session;
	private final int clientId;
	private int roomId;

	public ChatClient(int clientId, WebSocketSession session) {
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

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}