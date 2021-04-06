package es.urjc.etsii.dad.campfire;

import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import es.urjc.etsii.dad.campfire.socket.ChatSocket;
import es.urjc.etsii.dad.campfire.thrift.CrossPlatformServiceClient;
import es.urjc.etsii.dad.campfire.thrift.CrossPlatformServiceServer;
import es.urjc.etsii.dad.campfire.socket.AvatarSocket;

@EnableWebSocket
@SpringBootApplication
public class CampfireApplication implements WebSocketConfigurer {

	public static void main(String[] args) throws TTransportException {
		SpringApplication.run(CampfireApplication.class, args);
		CrossPlatformServiceServer server = new CrossPlatformServiceServer();
        server.start();
		CrossPlatformServiceClient client = new CrossPlatformServiceClient();
		client.ping();
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(avatarHandler(), "/avatar-customization")
				.addInterceptors(new HttpSessionHandshakeInterceptor());
		registry.addHandler(chatHandler(), "/chat")
				.addInterceptors(new HttpSessionHandshakeInterceptor());
	}

	@Bean
	public AvatarSocket avatarHandler() {
		return new AvatarSocket();
	}

	@Bean
	public ChatSocket chatHandler() {
		return new ChatSocket();
	}
}
