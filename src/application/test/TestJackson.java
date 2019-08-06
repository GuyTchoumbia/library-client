package application.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.modele.Document;

public class TestJackson {
			
	ObjectMapper om = new ObjectMapper();	
	private TestClass test = new TestClass("Jack", new ArrayList<String>(Arrays.asList("fraise Tagada", "Malabar", "Carambar")));
	private String jsonTest;
	private TestClass deserialTest;
	String urlTestJson = "http://localhost:8080/library-api/document/id/1";
	Document document;
	
	@Test
	void testSerial() {		
		try {
			jsonTest = om.writeValueAsString(test);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonTest);
		assert jsonTest != null;
		try {
			deserialTest = om.readValue(jsonTest, TestClass.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(deserialTest);
		System.out.println("bonbons :"+deserialTest.getBonbons().toString());
		System.out.println(deserialTest.getNom());
		assert deserialTest.getClass() == TestClass.class;
		assert deserialTest.getNom().equals("Jack");
		assert !deserialTest.getBonbons().isEmpty();
		
	}	
	

}
