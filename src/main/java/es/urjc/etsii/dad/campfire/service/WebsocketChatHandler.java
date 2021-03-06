package es.urjc.etsii.dad.campfire.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import es.urjc.etsii.dad.campfire.model.Chat;
import es.urjc.etsii.dad.campfire.model.ChatClient;
import es.urjc.etsii.dad.campfire.model.ChatMessage;
import es.urjc.etsii.dad.campfire.model.ChatServer;
import es.urjc.etsii.dad.campfire.repository.ChatRoomsRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WebsocketChatHandler extends TextWebSocketHandler {

	@Autowired
	private ChatServer server;

	@Autowired
	private ChatRoomsRepository chatRoomsRepository;

	private static final int LOBBY_ID = -3;
	private static final String CLIENT_ATTRIBUTE = "CLIENT";
	private ObjectMapper mapper = new ObjectMapper();
	private AtomicInteger clientId = new AtomicInteger(0);

	private boolean DEBUG = false;

	@PostConstruct
	public void init(){
		if(DEBUG == true){
			Chat chat = new Chat("ESTOPA");
			chat.addMessage(new ChatMessage("POR LA RAJA DE TU FALDA"));
			chat.addMessage(new ChatMessage("YO TUVE UN PIÑAZO CON UN SEAT PANDA"));
			chatRoomsRepository.save(chat);
		}
	}
	

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
				server.addChatClient(client);
				break;
			case "CHAT MESSAGE":
				String chatMessage = node.get("text").asText();

				System.out.println("CLIENT CHAT MESSAGE: " + chatMessage);
				System.out.println("CHAT MESSAGE ROOM ID: " + client.getRoomId());
				findChatAndAddMessage(chatMessage, client.getRoomId());
				msg.put("event", "CLIENT MESSAGE");
				msg.put("id", client.getClientId());
				msg.put("text", chatMessage);
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
				chatRoomsRepository.save(chat);
				if(DEBUG==true){
					Optional<Chat> optionalChat = chatRoomsRepository.findById((long)chat.getId());
					System.out.println("OPTIONALCHAT isPresent: " + optionalChat.isPresent());
					if(optionalChat.isPresent())
					{
						System.out.println("CHAT OBJECT: " + optionalChat.get());
						System.out.println("CHAT ID: " + optionalChat.get().getId());
					}
				}
				msg.put("event","ROOM CREATED");
				msg.put("text", chatName);
				msg.put("chatID", chat.getId());
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

	public void findChatAndAddMessage(String chatMessage, long chatId){
		Optional<Chat> optionalChat = chatRoomsRepository.findById(chatId);
		if(optionalChat.isPresent()){
			Chat chat = optionalChat.get();
			if(DEBUG == true)
			{
				System.out.println("CHAT BD NAME: " + chat.getName());
				System.out.println("CHAT BD ROOM ID: " + chat.getId());
			}
			chat.addMessage(new ChatMessage(chatMessage));
			chatRoomsRepository.save(chat);
		}
		else{
			System.out.println("CHAT WITH ID: " + chatId + " NOT FOUND");
		}
	}
}

