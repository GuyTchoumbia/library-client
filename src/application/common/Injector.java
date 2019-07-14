package application.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Injector {
	
	@Singleton
	public ExecutorService executorService() {
		return Executors.newCachedThreadPool();
	}
	
	@Bean
	public Client client() {
		return ClientBuilder.newClient();
	}

}
