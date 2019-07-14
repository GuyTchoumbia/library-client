package services;

import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;

import application.modele.Document;
import application.modele.Library;
import application.modele.Support;
import javafx.collections.ObservableList;

public class DocumentService {	
	
	private String url = "http://localhost:8080/library-api";

	public ObservableList<Document> search() {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
				.path("/document/all");
		GluonObservableList<Document> documents = DataProvider.retrieveList(restClient.createListDataReader(Document.class));
		return documents;
	}
	
	public void delete(Document document) {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(url)
		        .path("/document/id/")
		        .path(document.getId().toString());
		GluonObservableObject<Document> gdocument = DataProvider.retrieveObject(restClient.createObjectDataReader(Document.class));	
		restClient = RestClient.create()
		        .method("POST")
		        .host(url)
		        .path("/document/delete");				
		DataProvider.removeObject(gdocument, restClient.createObjectDataRemover(Document.class));		
	}
	
	public void update(Document document) {
		RestClient restClient = RestClient.create()
		        .method("POST")
		        .host(url)
		        .path("/document/update");				
		DataProvider.storeObject(document, restClient.createObjectDataWriter(Document.class));		
	}	
	
	public ObservableList<Library> findAllLibraries() {
		RestClient libraryClient = RestClient.create()
		        .method("GET")
		        .host(url)
				.path("/library/all");
		GluonObservableList<Library> libraries = DataProvider.retrieveList(libraryClient.createListDataReader(Library.class));
		return libraries;
	}
	
	public ObservableList<Support> findAllSupports() {
		RestClient supportClient = RestClient.create()
		        .method("GET")
		        .host(url)
				.path("/support/all");
		GluonObservableList<Support> supports = DataProvider.retrieveList(supportClient.createListDataReader(Support.class));
		return supports;
	}
	
	
	
}
