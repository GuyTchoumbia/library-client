package tasks;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;

public class GetObjectRequest<T> extends Task<T> {

	@Autowired	Client client;
	Class<T> entityClass;	
	String path;
	ObjectProperty<T> response;	
	
	public GetObjectRequest (Client client, Class<T> entityClass, String path) {
		this.client = client;
		this.entityClass = entityClass;		
		this.path = path;		
		this.setOnSucceeded(e -> this.response.set(this.getValue()));
	}

	@Override
	protected T call() throws Exception {
		String json = client				
						.target(path)
						.request(MediaType.APPLICATION_JSON)
						.get(String.class);
		ObjectMapper om = new ObjectMapper();
		return om.readValue(json, entityClass);
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ObjectProperty<T> getResponse() {
		return response;
	}	

}
