package application.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import application.modele.Cote;
import reactor.core.publisher.Mono;

@Service
public class CoteService extends AbstractService<Cote> {

	public Mono<Cote> findCoteByLibelle(String libelle) {
		Mono<Cote> entity = client
			.get()
			.uri("cote/libelle/{libelle}", libelle)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(Cote.class);
		return entity;
	}

}
