package services;

import java.util.List;

import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;

import application.modele.User;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

public class UserService {
	
	private String url = "http://localhost:8080/library-api/user";

	public ObservableList<User> search(String libelle, String auteur, String editeur, String isbn, String cote, String support, String library) {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
				.path("/search")
				.queryParam("document", libelle)
				.queryParam("auteur", libelle)
				.queryParam("editeur", editeur)
				.queryParam("isbn", isbn)
				.queryParam("cote", cote)
				.queryParam("support", support)
				.queryParam("library", library);
		GluonObservableList<User> users = DataProvider.retrieveList(restClient.createListDataReader(User.class));
		return users;
	}
	
	public ObservableList<User> findUserByInfo(String nom, String prenom) {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
				.path("/info")
				.queryParam("nom", nom)
				.queryParam("prenom", prenom);
		GluonObservableList<User> users = DataProvider.retrieveList(restClient.createListDataReader(User.class));
		return users;
	}
	
	public void delete(User user) {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
		        .path("id/"+user.getId());				
		GluonObservableObject<User> guser = DataProvider.retrieveObject(restClient.createObjectDataReader(User.class));	
		restClient = RestClient.create()
		        .method("POST")
		        .host(url)
		        .path("delete");				
		DataProvider.removeObject(guser, restClient.createObjectDataRemover(User.class));		
	}
	
	public void update(User user) {
		RestClient restClient = RestClient.create()
		        .method("POST")
		        .host(url)
		        .path("update");				
		DataProvider.storeObject(user, restClient.createObjectDataWriter(User.class));		
	}

	public GluonObservableObject<User> findUserById(String id) {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
				.path("/id/1");							
		GluonObservableObject<User> user = DataProvider.retrieveObject(restClient.createObjectDataReader(User.class));
		return user;		
	}

}
