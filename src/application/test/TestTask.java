package application.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;

import application.modele.Document;

public class TestTask {
	
	ExecutorService executor = Executors.newCachedThreadPool();
	String urlTest = "http://localhost:8080/library-api/test/hello";
	String urlTestJson = "http://localhost:8080/library-api/document/id/1";
	String result;
		
	
	@Test
	void testJsonSerialization() {
		String json = null;
		System.out.println(json);
		assert json != null;
	}	
	
	@Test
	void testDataFx() {
		RestClient restClient = RestClient.create()
		        .method("GET")
		        .host(urlTestJson);
		GluonObservableObject<Document> document = DataProvider.retrieveObject(restClient.createObjectDataReader(Document.class));
		System.out.println(document);
		assert document != null;		        

	}

}
