package application.utils;

import application.modele.User;
import application.views.EditUserController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class EditUserDialog extends Dialog<User> {
	
	private EditUserController controller;
	
	public EditUserDialog(User user, SpringFxmlLoader loader, EditUserController controller) {
		this.controller = controller;
		this.setUser(user);
		this.initStyle(StageStyle.UTILITY);
		this.setTitle("Editer Utilisateur");
		this.setResizable(true);
		this.setHeight(800);
		this.setWidth(1200);
		ButtonType valider = new ButtonType("Valider", ButtonData.OK_DONE);
		ButtonType annuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		getDialogPane().getButtonTypes().addAll(valider, annuler);	
		try {			
			GridPane editContainer = (GridPane) loader.load("../views/editUser.fxml");
			this.getDialogPane().setContent(editContainer);				
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		
		final Button validate = (Button) this.getDialogPane().lookupButton(valider);
		validate.addEventFilter(ActionEvent.ACTION, event -> {
			if (!getController().validate()) {
				event.consume();
				Alert alert = new Alert(AlertType.WARNING, "Champs Incorrects");
				alert.setContentText(getController().getErrorMessage());
				alert.showAndWait();
			}
		});
				
		this.setResultConverter(button -> {
			if (button == valider) {				
				return this.getUser();				
			}
			else return null;
		});		
	}	
	
	public EditUserController getController() {
		return this.controller;
	}
	
	public void setController(EditUserController controller) {
		this.controller = controller;
	}
	
	public User getUser() {
		return this.controller.getUser();
	}
	
	public void setUser(User user) {
		this.controller.setUser(user);
	}	

}
