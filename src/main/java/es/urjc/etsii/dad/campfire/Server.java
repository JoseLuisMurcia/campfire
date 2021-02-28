package es.urjc.etsii.dad.campfire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Server {

	public final static boolean DEBUG_MODE = true;
	public final static boolean VERBOSE_MODE = true;

	ObjectMapper mapper = new ObjectMapper();

	private Map<String, Client> clients = new ConcurrentHashMap<>();
	private AtomicInteger numclients = new AtomicInteger();
	private ArrayList<String> messages = new ArrayList<>();
	
	public void addClient(Client client) {
		clients.put(client.getSession().getId(), client);

		int count = numclients.getAndIncrement();
	}

	public Collection<Client> getClients() {
		return clients.values();
	}

	public void removeClient(Client client) {
		clients.remove(client.getSession().getId());

		int count = this.numclients.decrementAndGet();
	}


	public void broadcast(String message) {
		for (Client client : getClients()) {
			try {
				client.getSession().sendMessage(new TextMessage(message.toString()));
			} catch (Throwable ex) {
				System.err.println("Exepttion sending message to client " + client.getSession().getId());
				ex.printStackTrace(System.err);
				this.removeClient(client);
			}
		}
	}
}
