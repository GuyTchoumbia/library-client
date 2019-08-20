package application;
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@SpringBootApplication
public class Main extends Application {
	
	public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Lazy
	@Autowired
	private FXMLLoader springFXMLLoader;
	private GridPane mainContainer;
	
	public void init() throws Exception {
        FXMLLoader springFXMLLoader = new FXMLLoader();
		springFXMLLoader.setLocation(this.getClass().getResource("views/main.fxml"));	
		springFXMLLoader.setControllerFactory(context::getBean);
        mainContainer = (GridPane) springFXMLLoader.load();
    }
	
	@Override
	public void start(Stage primaryStage) {			
		try {	        
			Scene MainScene = new Scene(mainContainer);
			MainScene.getStylesheets().add(Main.class.getResource("bootstrap3.css").toExternalForm());
		    primaryStage.setScene(MainScene);
		    primaryStage.show();		
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
	
	public void stop() throws Exception{
        context.close();
    }
}
