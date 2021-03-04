package es.urjc.etsii.dad.campfire.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import es.urjc.etsii.dad.campfire.model.Avatar;

public class AvatarSocket extends TextWebSocketHandler {

	private ObjectMapper mapper = new ObjectMapper();

	private List<Avatar> avatars = new ArrayList<Avatar>();

	private AtomicInteger avatarId = new AtomicInteger(0); 

	public AvatarSocket()
	{
		System.out.println("EHHH CONSTRUCTOR BB");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) 
    throws Exception 
    {
		System.out.println("***********************************************************************" +
		"**********************************************************");
		for(String _string : session.getAttributes().keySet())
		{
			System.out.println(_string);
		}
		//Llamar a la base de datos -> avatar
		int id =  avatarId.incrementAndGet();
		Avatar _avatar = new Avatar(id, 2, 1, 0, 1, 0);
		_avatar.setSession(session);
		avatars.add(_avatar);
		ObjectNode msg = mapper.createObjectNode();
		msg.put("event", "LOAD");
		int[] _attributes = _avatar.getAttributes();
		for(int i = 0; i < _attributes.length; i++)
		{
			msg.put("attribute" + i, _attributes[i]);
		}
		msg.put("id", id);
		_avatar.getSession().sendMessage(new TextMessage(msg.toString()));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, 
    TextMessage message) throws Exception 
    {
		try {
			JsonNode node = mapper.readTree(message.getPayload());
			ObjectNode msg = mapper.createObjectNode();
			//Avatar _avatar = (Avatar) session.getAttributes().get(PLAYER_ATTRIBUTE);
			node.get("attributes").asText();
			switch (node.get("event").asText()) {
				case "SAVE":
					int id = msg.get("id").asInt();
					avatars.get(id).setPrimaryColor(msg.get("attribute0").asInt());
					avatars.get(id).setSecondaryColor(msg.get("attribute1").asInt());
					avatars.get(id).setEyesColor(msg.get("attribute2").asInt());
					avatars.get(id).setHatId(msg.get("attribute3").asInt());
					avatars.get(id).setAccessoryId(msg.get("attribute4").asInt());
					System.out.println("ATTRIBUTES -> " + avatars.get(id).getAttributes()[0]);
					//Llamar a la base de datos
					break;
			}

		} catch (Exception e) {
			System.err.println("Exception processing message " + message.getPayload());
			e.printStackTrace(System.err);
		}
	}

	//@Override
	//public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	//	Client client = (Client) session.getAttributes().get(PLAYER_ATTRIBUTE);
	//	server.removeClient(client);
//
	//	ObjectNode msg = mapper.createObjectNode();
	//	msg.put("event", "REMOVE PLAYER");
	//	msg.put("id", client.getClientId());
	//	server.broadcast(msg.toString());
	//}
}

