package application.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import application.modele.User;
import application.modele.UserCote;
import reactor.core.publisher.Mono;

@Service
public class UserService extends AbstractService<User>{	
	
	public UserService() {
		this.url = "http://localhost:8080/library-api";
		this.entityClass = User.class;
		this.client =  WebClient.builder().baseUrl(url).filter(logRequest()).build();
	}	
	
	public Mono<User[]> findUserByInfo(String id, String nom) {		
		Mono<User[]> result = client
			.get()
			.uri(uriBuilder -> uriBuilder
					.path("/user/info")
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
				.uri("/user/id")						
				.accept(MediaType.APPLICATION_JSON)	
				.syncBody(user)
				.exchange()
				.flatMap(response -> response.bodyToMono(String.class));
			return result;
	}
	
	public Mono<User> update(User user) {		
		Mono<User> entity = client
		        .post()
		        .uri("/user/update")		        
		        .accept(MediaType.APPLICATION_JSON)
		        .syncBody(user)
		        .exchange()
		        .flatMap(response -> response.bodyToMono(User.class));
		return entity;
	}

	public Mono<User> findById(String id) {
		Mono<User> entity = client
			.get()
			.uri("/user/id/{id}", id)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(User.class);
		return entity;
	}

	public Mono<UserCote> saveUserCote(UserCote userCote) {
		Mono<UserCote> entity = client
			.post()
			.uri("/userCote/update/")
			.accept(MediaType.APPLICATION_JSON)
	        .syncBody(userCote)
	        .exchange()
	        .flatMap(response -> response.bodyToMono(UserCote.class));
		return entity;
	}

	public Mono<UserCote> deleteUserCote(UserCote userCote) {
		Mono<UserCote> entity = client
			.post()
			.uri("/userCote/update/")
			.accept(MediaType.APPLICATION_JSON)
		    .syncBody(userCote)
		    .exchange()
		    .flatMap(response -> response.bodyToMono(UserCote.class));
		return entity;
	}
}