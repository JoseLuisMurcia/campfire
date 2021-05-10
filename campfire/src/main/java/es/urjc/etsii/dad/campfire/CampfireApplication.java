package es.urjc.etsii.dad.campfire;


import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.WebFilter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import es.urjc.etsii.dad.campfire.socket.ChatSocket;
import es.urjc.etsii.dad.campfire.socket.AvatarSocket;

import java.util.Collections;
import java.util.Properties;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.HazelcastInstance;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.WebFilterChainProxy;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@EnableWebSocket
@SpringBootApplication
@EnableHazelcastHttpSession
public class CampfireApplication implements WebSocketConfigurer {

	public static void main(String[] args) throws TTransportException {
		SpringApplication.run(CampfireApplication.class, args);
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

	@Bean
	public Config config(){
		return new Config();
	}

	@Bean
	public com.hazelcast.web.WebFilter webFilter(HazelcastInstance hazelcastInstance) {

		Properties properties = new Properties();
		properties.put("instance-name", hazelcastInstance.getName());
		properties.put("sticky-session", "false");
		return new com.hazelcast.web.WebFilter(properties);
	}

	
}
