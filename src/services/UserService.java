package services;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import application.modele.User;
import reactor.core.publisher.Mono;

@Component
public class UserService extends AbstractService<User>{	
	
	public UserService() {
		this.url = "http://localhost:8080/library-api/user";
		this.entityClass = User.class;
		this.client =  WebClient.builder().baseUrl(url).filter(logRequest()).build();
	}	
	
	public Mono<User[]> findUserByInfo(String id, String nom) {		
		Mono<User[]> result = client
			.get()
			.uri(uriBuilder -> uriBuilder
					.path("/info")
					.queryParam("id", id)
					.queryParam("nom", nom)					
					.build()
					)
			.accept(MediaType.APPLICATION_JSON)			
			.retrieve()
			.bodyToMono(User[].class);
		return result;
	}
	
	
	public Mono<String> delete(User user) {				
		Mono<String> result = client
				.post()
				.uri("/id")						
				.accept(MediaType.APPLICATION_JSON)	
				.syncBody(user)
				.exchange()
				.flatMap(response -> response.bodyToMono(String.class));
			return result;
	}
	
	public Mono<User> update(User user) {		
		Mono<User> entity = client
		        .post()
		        .uri("/update")		        
		        .accept(MediaType.APPLICATION_JSON)
		        .syncBody(user)
		        .exchange()
		        .flatMap(response -> response.bodyToMono(User.class));
		return entity;
	}

	public Mono<User> findById(String id) {
		Mono<User> entity = client
				.get()
				.uri("/id/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(User.class);
			return entity;
	}

}
