package es.urjc.etsii.dad.campfire.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

@Component
public class ChatServer {

	public final static boolean DEBUG_MODE = true;
	public final static boolean VERBOSE_MODE = true;

	ObjectMapper mapper = new ObjectMapper();

	private Map<String, ChatClient> chatClients = new ConcurrentHashMap<>();
	private Map<String, ChatClient> lobbyClients = new ConcurrentHashMap<>();
	private AtomicInteger numclients = new AtomicInteger();
	
	public void addChatClient(ChatClient client) {
		chatClients.put(client.getSession().getId(), client);

		int count = numclients.getAndIncrement();
	}

	public void addLobbyClient(ChatClient client) {
		lobbyClients.put(client.getSession().getId(), client);

		int count = numclients.getAndIncrement();
	}

	public Collection<ChatClient> getChatClients() {
		return chatClients.values();
	}

	public Collection<ChatClient> getLobbyClients() {
		return lobbyClients.values();
	}

	public void removeChatClient(ChatClient client) {
		chatClients.remove(client.getSession().getId());

		int count = this.numclients.decrementAndGet();
	}

	public void removeLobbyClient(ChatClient client) {
		lobbyClients.remove(client.getSession().getId());

		int count = this.numclients.decrementAndGet();
	}


	public void broadcast(String message, int roomId) {
		for (ChatClient client : getChatClients()) {
			try {
				if(client.getRoomId() == roomId)client.getSession().sendMessage(new TextMessage(message.toString()));
			} catch (Throwable ex) {
				System.err.println("Exception sending message to client " + client.getSession().getId());
				ex.printStackTrace(System.err);
				this.removeChatClient(client);
			}
		}
	}

	public void broadcast(String message) {
		for (ChatClient client : getChatClients()) {
			try {
				client.getSession().sendMessage(new TextMessage(message.toString()));
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
