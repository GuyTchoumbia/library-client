package application.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

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
	
}
