package es.urjc.etsii.dad.campfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import es.urjc.etsii.dad.campfire.service.WebsocketChatHandler;

@EnableWebSocket
@SpringBootApplication
public class CampfireApplication implements WebSocketConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CampfireApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatHandler(), "/chat");
	}

	@Bean
	public WebsocketChatHandler chatHandler() {
		return new WebsocketChatHandler();
	}

}

