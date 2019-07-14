package tasks;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.concurrent.Task;

public class PutObjectRequest<T> extends Task<Integer> {

	@Autowired	Client client;
	T entity;	
	String path;	
	IntegerProperty response;
	
	public PutObjectRequest (Client client, T entity, String path) {
		this.client = client;
		this.entity = entity;		
		this.path = path;	
		this.setOnSucceeded(e -> this.response.set(this.getValue()));
	}

	@Override
	protected Integer call() throws Exception {
		Response response = client			
								.target(path)
								.request(MediaType.APPLICATION_JSON)
								.put(Entity.entity(entity, MediaType.APPLICATION_JSON));
		return response.getStatus();
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public IntegerProperty getResponse() {
		return response;
	}	

}
