package services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import application.modele.UserCote;
import reactor.core.publisher.Mono;

@Component
public class RetourService extends AbstractService<UserCote> {
	
	public RetourService() {
		this.url = baseUrl;		
	}

	public Mono<UserCote> findCoteByLibelle(String libelle) {
		Mono<UserCote> result = client
			.get()
			.uri("/userCote/id/{id}, id")						
			.accept(MediaType.APPLICATION_JSON)			
			.retrieve()
			.bodyToMono(UserCote.class);
		return result;
	}

	public Mono<String> deleteUserCote(UserCote userCote) {
		Mono<String> result = client
			.post()
			.uri("/userCote/delete")						
			.accept(MediaType.APPLICATION_JSON)
			.syncBody(userCote)
			.exchange()
			.flatMap(response -> response.bodyToMono(String.class));
		return result;
	}	

}
