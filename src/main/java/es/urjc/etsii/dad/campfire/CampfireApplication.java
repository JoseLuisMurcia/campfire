package es.urjc.etsii.dad.campfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import es.urjc.etsii.dad.campfire.component.AvatarSocket;

@SpringBootApplication
@EnableWebSocket
public class CampfireApplication implements WebSocketConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CampfireApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) 
	{
		registry.addHandler(avatarHandler(), "/avatar-customization");
	}

	@Bean
	public AvatarSocket avatarHandler()
	{
		return new AvatarSocket();
	}


}
