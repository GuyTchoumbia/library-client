package application.services;

import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractService<T> {
	
	protected Class<T> entityClass;
	protected String baseUrl = "http://localhost:8080/library-api";
	protected String url;
	protected static Logger log = Logger.getLogger("HttpClient");
	protected WebClient client = WebClient.builder().baseUrl(baseUrl).filter(logRequest()).build();
	
	protected static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: "+ clientRequest.method()+ clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> System.out.println("{}={}" + name + value)));
            return Mono.just(clientRequest);
        });
    }	
	
	public Flux<T> findAll() {
		Flux<T> request = client
				.get()
				.uri("/all")
				.accept(MediaType.APPLICATION_JSON)	
				.retrieve()
				.bodyToFlux(entityClass);
		return request;
	}
	
	public Mono<String> delete(T entity) {				
		Mono<String> result = client
				.post()
				.uri("/id")						
				.accept(MediaType.APPLICATION_JSON)	
				.syncBody(entity)
				.exchange()
				.flatMap(response -> response.bodyToMono(String.class));
			return result;
	}
	
	public Mono<T> update(T entity) {		
		Mono<T> result = client
		        .post()
		        .uri("/update")		        
		        .accept(MediaType.APPLICATION_JSON)
		        .syncBody(entity)
		        .exchange()
		        .flatMap(response -> response.bodyToMono(entityClass));
		return result;
	}

	public Mono<T> findById(String id) {
		Mono<T> entity = client
				.get()
				.uri("/id/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(entityClass);
			return entity;
	}
}
