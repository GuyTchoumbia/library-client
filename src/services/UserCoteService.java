package services;

import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;

import application.modele.UserCote;
import javafx.collections.ObservableList;

public class UserCoteService {
	
	private String url = "http://localhost:8080/library-api/userCote";

	public ObservableList<UserCote> search() {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
				.path("/all");
		GluonObservableList<UserCote> userCotes = DataProvider.retrieveList(restClient.createListDataReader(UserCote.class));
		return userCotes;
	}
	
	public void delete(UserCote userCote) {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
		        .path("id/"+userCote.getId());				
		GluonObservableObject<UserCote> gUserCote = DataProvider.retrieveObject(restClient.createObjectDataReader(UserCote.class));	
		restClient = RestClient.create()
		        .method("POST")
		        .host(url)
		        .path("delete");				
		DataProvider.removeObject(gUserCote, restClient.createObjectDataRemover(UserCote.class));		
	}
	
	public void update(UserCote userCote) {
		RestClient restClient = RestClient.create()
		        .method("POST")
		        .host(url)
		        .path("update");				
		DataProvider.storeObject(userCote, restClient.createObjectDataWriter(UserCote.class));		
	}

}
