package es.urjc.etsii.dad.campfire.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import es.urjc.etsii.dad.campfire.model.ChatClient;
import es.urjc.etsii.dad.campfire.model.ChatServer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WebsocketChatHandler extends TextWebSocketHandler {

	@Autowired
	private ChatServer server;

	private static final String PLAYER_ATTRIBUTE = "PLAYER";
	private ObjectMapper mapper = new ObjectMapper();
	private AtomicInteger playerId = new AtomicInteger(0);

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		ChatClient client = new ChatClient(playerId.incrementAndGet(), session);
		session.getAttributes().put(PLAYER_ATTRIBUTE, client);
		ObjectNode msg = mapper.createObjectNode();
		server.addClient(client);
		msg.put("event", "JOIN");
		msg.put("id", client.getClientId());
		client.getSession().sendMessage(new TextMessage(msg.toString()));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		try {
			JsonNode node = mapper.readTree(message.getPayload());
			ObjectNode msg = mapper.createObjectNode();
			ChatClient client = (ChatClient) session.getAttributes().get(PLAYER_ATTRIBUTE);

			switch (node.get("event").asText()) {
			case "JOIN":
				msg.put("event", "JOIN");
				msg.put("id", client.getClientId());
				client.getSession().sendMessage(new TextMessage(msg.toString()));
				break;
			case "JOIN ROOM":
				msg.put("event", "NEW ROOM");
				msg.put("room", "GLOBAL");
				client.getSession().sendMessage(new TextMessage(msg.toString()));
				break;
			case "CHAT MESSAGE":
				msg.put("event", "CLIENT MESSAGE");
				msg.put("id", client.getClientId());
				msg.put("text", node.get("text").asText());
				server.broadcast(msg.toString());
				break;
			case "PLAYER JOINED":
				msg.put("event", "CHAT JOIN");
				msg.put("id", client.getClientId());
				server.broadcast(msg.toString());
				break;
			default:
				break;
			}

		} catch (Exception e) {
			System.err.println("Exception processing message " + message.getPayload());
			e.printStackTrace(System.err);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		ChatClient client = (ChatClient) session.getAttributes().get(PLAYER_ATTRIBUTE);
		server.removeClient(client);

		ObjectNode msg = mapper.createObjectNode();
		msg.put("event", "REMOVE PLAYER");
		msg.put("id", client.getClientId());
		server.broadcast(msg.toString());
	}
}

