package application.utils;

import application.modele.Document;
import application.views.EditDocumentController;
import javafx.event.ActionEvent;
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
	
	public EditDocumentDialog(Document document, SpringFxmlLoader loader, EditDocumentController controller) {
		this.controller = controller;
		this.setDocument(document);
		this.initStyle(StageStyle.UTILITY);
		this.setTitle("Editer Document");
		this.setResizable(true);
		this.setHeight(800);
		this.setWidth(1000);
		ButtonType valider = new ButtonType("Valider", ButtonData.OK_DONE);
		ButtonType annuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(valider, annuler);		
		try {			
			GridPane editContainer = (GridPane) loader.load("../views/editDocument.fxml");
			this.getDialogPane().setContent(editContainer);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}				
		this.setResultConverter(button -> {
			if (button == valider) {
				return this.getDocument();
			}
			return null;		
		});
		
		final Button validate = (Button) this.getDialogPane().lookupButton(valider);
		validate.addEventFilter(ActionEvent.ACTION, event -> {			
			if (!getController().validate()) {
				event.consume();
				Alert alert = new Alert(AlertType.WARNING, "Champs Incorrects");
				alert.setContentText(getController().getErrorMessage());
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
	
	public Document getDocument() {
		return this.controller.getDocument();
	}
	
	public void setDocument(Document document) {
		this.controller.setDocument(document);
	}
}
