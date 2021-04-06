package es.urjc.etsii.dad.campfire.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import es.urjc.etsii.dad.campfire.model.ChatClient;

@Service
public class ChatService {
	
	ObjectMapper mapper = new ObjectMapper();

	private Map<String, ChatClient> chatClients = new ConcurrentHashMap<>();
	private Map<String, ChatClient> lobbyClients = new ConcurrentHashMap<>();
	private AtomicInteger numclients = new AtomicInteger();

	@Autowired
	private FriendListService friendService;

	public void addChatClient(ChatClient client) {
		chatClients.put(client.getSession().getId(), client);
		numclients.getAndIncrement();
	}

	public void addLobbyClient(ChatClient client) {
		lobbyClients.put(client.getSession().getId(), client);
		numclients.getAndIncrement();
	}

	public Collection<ChatClient> getChatClients() {
		return chatClients.values();
	}

	public Collection<ChatClient> getLobbyClients() {
		return lobbyClients.values();
	}

	public void removeChatClient(ChatClient client) {
		chatClients.remove(client.getSession().getId());
		this.numclients.decrementAndGet();
	}

	public void removeLobbyClient(ChatClient client) {
		lobbyClients.remove(client.getSession().getId());
		this.numclients.decrementAndGet();
	}

	public void broadcast(String message, int roomId) {
		for (ChatClient client : getChatClients()) {
			try {
				if (client.getRoomId() == roomId)
					client.getSession().sendMessage(new TextMessage(message.toString()));
			} catch (Throwable ex) {
				System.err.println("Exception sending message to client " + client.getSession().getId());
				ex.printStackTrace(System.err);
				this.removeChatClient(client);
			}
		}
	}

	public void broadcastWithFriendInfo(String msgOwner, ObjectNode msg, int roomId) {
		for (ChatClient client : getChatClients()) {
			try {
				if (client.getRoomId() == roomId) {
					String clientUsername = client.getSession().getAttributes().get("username").toString();

					if (msgOwner.equals(clientUsername)) {
						msg.put("isFriend", "self");
					} else if (friendService.checkFriendship(clientUsername, msgOwner)) {
						msg.put("isFriend", "friend");
					} else {
						msg.put("isFriend", "none");
					}

					client.getSession().sendMessage(new TextMessage(msg.toString()));
				}
			} catch (Throwable ex) {
				System.err.println("Exception sending message to client " + client.getSession().getId());
				ex.printStackTrace(System.err);
				this.removeChatClient(client);
			}
		}
	}

	public void broadcastToLobby(String message) {
		for (ChatClient client : getLobbyClients()) {
			try {
				client.getSession().sendMessage(new TextMessage(message.toString()));
			} catch (Throwable ex) {
				System.err.println("Exception sending message to client " + client.getSession().getId());
				ex.printStackTrace(System.err);
				this.removeLobbyClient(client);
			}
		}
	}
}
