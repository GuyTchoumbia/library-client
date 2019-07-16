package application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import services.DocumentService;
import services.RetourService;
import services.UserService;

@Configuration
@ComponentScan("application")
public class AppConfig {
	
	@Bean
	DocumentService documentService() {
		return new DocumentService();
	}
	
	@Bean
	UserService userService() {
		return new UserService();
	}
	
	@Bean
	RetourService retourService() {
		return new RetourService();
	}
	
	

}
