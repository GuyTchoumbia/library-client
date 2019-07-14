package tasks;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;

public class GetListRequest<T> extends Task<T[]> {
	/* Generic Task used to get a list of results from the api
	 * pass the required path and entityClass(actually, list of entityClass eg Auteur[].class),
	 * bind the response property to whatever property you use to show data in the controller
	 * submit to the service or start, and that's it.
	 * additional features to develop: messages/events at each step of the process(instantiation, getting the string and serialization)
	 */
	
	@Autowired	Client client;
	Class<T[]> entityClass;	
	String path;
	ListProperty<T> response;	
	
	public GetListRequest (Class<T[]> entityClass, String path) {
		this.entityClass = entityClass;		
		this.path = path;		
		this.setOnSucceeded(e -> this.response.set(FXCollections.observableArrayList((T[]) this.getValue())));
	}

	@Override
	protected T[] call() throws Exception {
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

	public Class<T[]> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T[]> entityClass) {
		this.entityClass = entityClass;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ListProperty<T> getResponse() {
		return response;
	}	
	
}
