package es.urjc.etsii.dad.campfire.configuration;


import java.util.Properties;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import org.springframework.context.annotation.Bean;

@EnableHazelcastHttpSession
public class HazelcastConfig {
    
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
