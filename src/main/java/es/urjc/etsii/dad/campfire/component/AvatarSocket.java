package es.urjc.etsii.dad.campfire.component;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import es.urjc.etsii.dad.campfire.model.Avatar;
import es.urjc.etsii.dad.campfire.model.User;
import es.urjc.etsii.dad.campfire.repository.AvatarRepository;
import es.urjc.etsii.dad.campfire.repository.UserRepository;

public class AvatarSocket extends TextWebSocketHandler {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AvatarRepository avatarRepo;

	public AvatarSocket()
	{
		System.out.println("EHHH CONSTRUCTOR BB");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) 
    throws Exception 
    {
		Optional<User> _user = userRepo.findByUsername((String) session.getAttributes().get("username"));
		Optional<Avatar> _avatarOptional = avatarRepo.findByUser(_user.get());
		Avatar _avatar;
		if(_avatarOptional.isPresent())
		{
			_avatar = _avatarOptional.get();
		}
		else
		{
			_avatar = new Avatar(_user.get(), 0, 0, 0, 0, 0);
			avatarRepo.save(_avatar);
		}
		//Llamar a la base de datos -> avatar

		ObjectNode msg = mapper.createObjectNode();
		msg.put("event", "LOAD");
		int[] _attributes = _avatar.getAttributes();
		for(int i = 0; i < _attributes.length; i++)
		{
			msg.put("attribute" + i, _attributes[i]);
		}
		session.sendMessage(new TextMessage(msg.toString()));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, 
    TextMessage message) throws Exception 
    {
		try {
			JsonNode node = mapper.readTree(message.getPayload());
			//ObjectNode msg = mapper.createObjectNode();
			//Avatar _avatar = (Avatar) session.getAttributes().get(PLAYER_ATTRIBUTE);
			//node.get("attributes").asText();
			switch (node.get("event").asText()) {
				case "SAVE":
					Optional<User> _user = userRepo.findByUsername((String) session.getAttributes().get("username"));
					Optional<Avatar> _avatarOptional = avatarRepo.findByUser(_user.get());
					Avatar _avatar = _avatarOptional.get();
					_avatar.setPrimaryColor(node.get("attribute0").asInt());
					_avatar.setSecondaryColor(node.get("attribute1").asInt());
					_avatar.setEyesColor(node.get("attribute2").asInt());
					_avatar.setHatId(node.get("attribute3").asInt());
					_avatar.setAccessoryId(node.get("attribute4").asInt());

					//Llamar a la base de datos
					avatarRepo.save(_avatar);
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

