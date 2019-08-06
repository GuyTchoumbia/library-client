package application.services;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import application.modele.UserCote;
import reactor.core.publisher.Mono;

@Service
public class RetourService extends AbstractService<UserCote> {
	
	public RetourService() {
		this.url = baseUrl;		
	}

	public Mono<UserCote> findCoteByLibelle(String libelle) {
		Mono<UserCote> result = client
			.get()
			.uri("/userCote/libelle/{libelle}", libelle)						
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

	public Mono<UserCote> findUserCoteByCoteId(String libelle) {
		Mono<UserCote> entity = client
			.get()
			.uri("/userCote/emprunt/{id}", libelle)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(UserCote.class);
		return entity;
	}

	public Mono<UserCote> saveUserCote(UserCote userCote) {
		Mono<UserCote> result = client
			.post()
			.uri("/userCote/update")						
			.accept(MediaType.APPLICATION_JSON)
			.syncBody(userCote)
			.exchange()
			.flatMap(response -> response.bodyToMono(UserCote.class));
		return result;
	}	

}