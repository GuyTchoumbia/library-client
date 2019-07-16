package application;
	
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Override
	public void start(Stage primaryStage) {			
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("views/main.fxml"));			
			GridPane MainContainer = (GridPane) loader.load();
			Scene MainScene = new Scene(MainContainer);
			MainScene.getStylesheets().add(Main.class.getResource("resources/bootstrap3.css").toExternalForm());
		    primaryStage.setScene(MainScene);
		    primaryStage.show();		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
