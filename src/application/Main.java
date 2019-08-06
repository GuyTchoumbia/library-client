package application;
	
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@SpringBootApplication
public class Main extends Application {
	
	private ConfigurableApplicationContext context;
	private FXMLLoader loader;
	private GridPane mainContainer;
	
	public void init() throws Exception {
        context = SpringApplication.run(Main.class); 
        loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("views/main.fxml"));	
        loader.setControllerFactory(context::getBean);
        mainContainer = (GridPane) loader.load();
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
