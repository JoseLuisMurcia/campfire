package es.urjc.etsii.dad.campfire.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import es.urjc.etsii.dad.campfire.model.Chat;
import es.urjc.etsii.dad.campfire.model.ChatClient;
import es.urjc.etsii.dad.campfire.model.ChatServer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WebsocketChatHandler extends TextWebSocketHandler {

	@Autowired
	private ChatServer server;

	@Autowired
	private ChatService chatService;

	private static final int LOBBY_ID = -3;
	private static final String CLIENT_ATTRIBUTE = "CLIENT";
	private ObjectMapper mapper = new ObjectMapper();
	private AtomicInteger clientId = new AtomicInteger(0);


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		ChatClient client = new ChatClient(clientId.incrementAndGet(), session);
		session.getAttributes().put(CLIENT_ATTRIBUTE, client);
		ObjectNode msg = mapper.createObjectNode();
		
		msg.put("event", "JOIN");
		msg.put("id", client.getClientId());
		client.getSession().sendMessage(new TextMessage(msg.toString()));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		try {
			JsonNode node = mapper.readTree(message.getPayload());
			ObjectNode msg = mapper.createObjectNode();
			ChatClient client = (ChatClient) session.getAttributes().get(CLIENT_ATTRIBUTE);

			System.out.println("-----------------SERVER MESSAGE RECEIVED--------------------------");
			System.out.println(node.get("event").asText());
			switch (node.get("event").asText()) {
			case "SET PLAYER ROOM ID":
				System.out.println("PLAYER ROOM: " + node.get("roomID").asInt());
				client.setRoomId(node.get("roomID").asInt());
				server.addChatClient(client);
				break;
			case "CHAT MESSAGE":
				msg.put("event", "CLIENT MESSAGE");
				msg.put("id", client.getClientId());
				msg.put("text", node.get("text").asText());
				server.broadcast(msg.toString(), client.getRoomId());
				break;
			case "PLAYER JOINED":
				msg.put("event", "CHAT JOIN");
				msg.put("id", client.getClientId());
				server.broadcast(msg.toString(), client.getRoomId());
				break;
			case "LOBBY JOIN":
				client.setRoomId(LOBBY_ID);
				server.addLobbyClient(client);
				break;
			case "ROOM CREATION":
				String chatName = node.get("text").asText();
				Chat chat = new Chat(chatName);
				chatService.addChat(chat);
				msg.put("event","ROOM CREATED");
				msg.put("text", chatName);
				msg.put("chatID", chat.getId());
				System.out.println("CLIENT ROOM ID: " + client.getRoomId());
				server.broadcastToLobby(msg.toString());
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
		ChatClient client = (ChatClient) session.getAttributes().get(CLIENT_ATTRIBUTE);
		if(client.getRoomId() == LOBBY_ID) server.removeLobbyClient(client);
		else { server.removeChatClient(client); }

		ObjectNode msg = mapper.createObjectNode();
		msg.put("event", "REMOVE PLAYER");
		msg.put("id", client.getClientId());
		server.broadcast(msg.toString(), client.getRoomId());
	}
}

