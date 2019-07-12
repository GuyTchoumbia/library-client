package application.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Injector {
	
	ExecutorService executorService = Executors.newCachedThreadPool();
	
	@Bean 
	public ExecutorService executorService() {
		return executorService;
	}	
	

}
