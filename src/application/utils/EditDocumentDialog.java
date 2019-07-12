package application.utils;

import application.modele.Document;
import application.views.EditDocumentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class EditDocumentDialog extends Dialog<Document> {		
	
	private EditDocumentController controller;
	
	public EditDocumentDialog(Document document) {
		this.initStyle(StageStyle.UTILITY);
		this.setTitle("Editer Document");
		this.setResizable(true);
		this.setHeight(800);
		this.setWidth(1000);
		ButtonType valider = new ButtonType("Valider", ButtonData.OK_DONE);
		ButtonType annuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(valider, annuler);		
		try {
			FXMLLoader loader = new FXMLLoader();		
			loader.setLocation(getClass().getResource("../views/editDocument.fxml"));
			loader.setController(new EditDocumentController(document));
			this.setController(loader.getController()); 
			GridPane editContainer = (GridPane) loader.load();
			this.getDialogPane().setContent(editContainer);						
		}
		catch (Exception e) {
			e.printStackTrace();
		}				
		this.setResultConverter(button -> {
			if (button == valider) {
				return document;
			}
			return null;		
		});
		
		final Button validate = (Button) this.getDialogPane().lookupButton(valider);
		validate.addEventFilter(ActionEvent.ACTION, event -> {
			System.out.println(getController().validate());
			if (!getController().validate()) {
				event.consume();
				Alert alert = new Alert(AlertType.WARNING, "Champs Incorrects");
				alert.showAndWait();
			}
		});
		
	}
	
	
	public EditDocumentController getController() {
		return this.controller;
	}
	
	public void setController(EditDocumentController controller) {
		this.controller = controller;
	}
}
