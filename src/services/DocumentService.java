package services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import application.modele.Auteur;
import application.modele.Document;
import application.modele.Editeur;
import application.modele.Library;
import application.modele.Support;
import application.modele.Tag;
import reactor.core.publisher.Mono;

@Component
public class DocumentService extends AbstractService<Document> {
	
	public DocumentService() {		
		this.entityClass = Document.class;
	}

	public Mono<Support[]> findAllSupports() {
		Mono<Support[]> request = client
			.get()
			.uri("/support/all")
			.accept(MediaType.APPLICATION_JSON)	
			.retrieve()
			.bodyToMono(Support[].class);				
		return request;
	}
	
	public Mono<Library[]> findAllLibraries() {
		Mono<Library[]> request = client
			.get()
			.uri("/support/all")
			.accept(MediaType.APPLICATION_JSON)	
			.retrieve()
			.bodyToMono(Library[].class);				
		return request;
	}	

	public Mono<Document[]> autoCompleteLibelle(String text) {
		Mono<Document[]> request = client
			.get()
			.uri("/document/autocomplete")
			.accept(MediaType.APPLICATION_JSON)	
			.retrieve()
			.bodyToMono(Document[].class);				
		return request;
	}

	public Mono<Editeur[]> autoCompleteEditeur(String text) {
		Mono<Editeur[]> request = client
			.get()
			.uri("/document/autocomplete")
			.accept(MediaType.APPLICATION_JSON)	
			.retrieve()
			.bodyToMono(Editeur[].class);				
		return request;
	}

	public Mono<Auteur[]> autoCompleteAuteur(String text) {
		Mono<Auteur[]> request = client
			.get()
			.uri("/document/autocomplete")
			.accept(MediaType.APPLICATION_JSON)	
			.retrieve()
			.bodyToMono(Auteur[].class);				
		return request;
	}

	public Mono<Tag[]> autoCompleteTag(String text) {
		Mono<Tag[]> request = client
			.get()
			.uri("/document/autocomplete")
			.accept(MediaType.APPLICATION_JSON)	
			.retrieve()
			.bodyToMono(Tag[].class);				
		return request;
	}
	

	public Document search(String text, String text2, String text3, String text4, String text5, Integer id,
			Integer id2) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	
}
