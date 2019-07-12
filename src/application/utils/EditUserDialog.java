package application.utils;

import application.modele.User;
import application.views.EditUserController;
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

public class EditUserDialog extends Dialog<User> {
	
	private EditUserController controller;
	
	public EditUserDialog(User user) {
		this.initStyle(StageStyle.UTILITY);
		this.setTitle("Editer Utilisateur");
		this.setResizable(true);
		this.setHeight(800);
		this.setWidth(1200);
		ButtonType valider = new ButtonType("Valider", ButtonData.OK_DONE);
		ButtonType annuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		getDialogPane().getButtonTypes().addAll(valider, annuler);		
		try {
			FXMLLoader loader = new FXMLLoader();		
			loader.setLocation(getClass().getResource("../views/editUser.fxml"));
			loader.setController(new EditUserController(user));
			this.setController(loader.getController());
			GridPane editContainer = (GridPane) loader.load();
			this.getDialogPane().setContent(editContainer);						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		final Button validate = (Button) this.getDialogPane().lookupButton(valider);
		validate.addEventFilter(ActionEvent.ACTION, event -> {
			System.out.println(getController().validate());
			if (!getController().validate()) {
				event.consume();
				Alert alert = new Alert(AlertType.WARNING, "Champs Incorrects");
				alert.showAndWait();
			}
		});
				
		this.setResultConverter(button -> {
			if (button == valider) {
				return user;
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

}
