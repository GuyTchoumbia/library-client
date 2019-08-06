package application.utils;

import application.modele.Cote;
import application.views.InputCoteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class InputCoteDialog extends Dialog<Cote> {
	
	private Cote cote;
	private InputCoteController controller;
	
	public InputCoteDialog() {
		this.initStyle(StageStyle.UTILITY);
		this.setTitle("Entrez une Cote");
		this.setResizable(false);
		this.setHeight(300);
		this.setWidth(600);
		ButtonType valider = new ButtonType("Valider", ButtonData.OK_DONE);
		ButtonType annuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		getDialogPane().getButtonTypes().addAll(valider, annuler);		
		try {
			FXMLLoader loader = new FXMLLoader();		
			loader.setLocation(getClass().getResource("../views/inputCote.fxml"));
			this.setController(loader.getController());
			this.getController().setCote(cote);
			GridPane editContainer = (GridPane) loader.load();
			this.getDialogPane().setContent(editContainer);						
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
				
		this.setResultConverter(button -> {
			if (button == valider && getController().getCote() != null) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Etes-vous sur?");				
				alert.showAndWait();		
				return getController().getCote();
			}
			else return null;		
		});		
	}	
	
	public InputCoteController getController() {
		return this.controller;
	}
	
	public void setController(InputCoteController controller) {
		this.controller = controller;
	}

}
