package application.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	// a static method class to get jsons
	
	private static ObjectMapper om = new ObjectMapper();
	
	public static String toJson(Object object) {
		String json = null;
		try {
			json = om.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static <T> T fromJson(String json, Class<T> entityClass) {
		T entity = null;
		try {
			entity = om.readValue(json, entityClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

}
