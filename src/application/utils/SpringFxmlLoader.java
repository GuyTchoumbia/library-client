package application.utils;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import application.Main;
import javafx.fxml.FXMLLoader;

@Lazy
@Component
public class SpringFxmlLoader extends FXMLLoader {
	
	public Object load(String url) throws IOException	{
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setControllerFactory(Main.context::getBean);
	        loader.setLocation(getClass().getResource(url));
	        return loader.load();
	    }
	    catch (IOException e) {	    
	    	e.printStackTrace();
	    }
	    return null;
	
	}	

}
