package es.urjc.etsii.dad.campfire.socket;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import es.urjc.etsii.dad.campfire.service.ChatRoomsService;
import es.urjc.etsii.dad.campfire.service.ChatService;
import es.urjc.etsii.dad.campfire.thrift.CrossPlatformServiceClient;
import es.urjc.etsii.dad.campfire.entity.Chat;
import es.urjc.etsii.dad.campfire.model.ChatClient;
import es.urjc.etsii.dad.campfire.entity.ChatMessage;
import es.urjc.etsii.dad.campfire.entity.User;
import es.urjc.etsii.dad.campfire.repository.UserRepository;

public class ChatSocket extends TextWebSocketHandler {

	@Autowired
	private ChatService chatServer;

	@Autowired
	private ChatRoomsService chatRoomsService;

	@Autowired
	private UserRepository userRepository;

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
				client.setRoomId(node.get("roomID").asInt());
				chatServer.addChatClient(client);
				break;
			case "CHAT MESSAGE":
				CrossPlatformServiceClient filterClient = new CrossPlatformServiceClient();
				String chatMessage = node.get("text").asText();
				String filteredMessage = filterClient.sendMessage(chatMessage);
				String messageOwner = session.getAttributes().get("username").toString();
				findChatAndAddMessage(filteredMessage, client.getRoomId(), messageOwner);
				msg.put("event", "CLIENT MESSAGE");
				msg.put("id", client.getClientId());
				msg.put("text", messageOwner + ": " + filteredMessage);
				// server.broadcast(msg.toString(), client.getRoomId());
				chatServer.broadcastWithFriendInfo(messageOwner, msg, client.getRoomId());
				break;
			case "PLAYER JOINED":
				msg.put("event", "CHAT JOIN");
				msg.put("id", client.getClientId());
				msg.put("enteringuser", session.getAttributes().get("username").toString());
				chatServer.broadcast(msg.toString(), client.getRoomId());
				break;
			case "LOBBY JOIN":
				client.setRoomId(LOBBY_ID);
				chatServer.addLobbyClient(client);
				break;
			case "ROOM CREATION":
				String chatName = node.get("text").asText();
				Chat chat = new Chat(chatName);
				chatRoomsService.save(chat);
				msg.put("event", "ROOM CREATED");
				msg.put("text", chatName);
				msg.put("chatID", chat.getId());
				chatServer.broadcastToLobby(msg.toString());
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
		if (client.getRoomId() == LOBBY_ID)
			chatServer.removeLobbyClient(client);
		else {
			chatServer.removeChatClient(client);
		}

		ObjectNode msg = mapper.createObjectNode();
		msg.put("event", "REMOVE PLAYER");
		msg.put("id", client.getClientId());
		msg.put("exitinguser", session.getAttributes().get("username").toString());
		chatServer.broadcast(msg.toString(), client.getRoomId());
	}

	public void findChatAndAddMessage(String chatMessage, long chatId, String userName) {
		Optional<Chat> optionalChat = chatRoomsService.findById(chatId);
		User messageOwner = userRepository.findByUsername(userName).get();
		if (optionalChat.isPresent()) {
			Chat chat = optionalChat.get();
			chat.addMessage(new ChatMessage(chatMessage, messageOwner));
			chatRoomsService.save(chat);
		} else {
			System.out.println("CHAT WITH ID: " + chatId + " NOT FOUND");
		}
	}
}
