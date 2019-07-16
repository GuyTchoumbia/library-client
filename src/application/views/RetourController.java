package application.views;


import java.time.LocalDate;

import application.common.ControllerImpl;
import application.modele.Cote;
import application.modele.Document;
import application.modele.Support;
import application.modele.User;
import application.modele.UserCote;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.RetourService;

public class RetourController extends ControllerImpl<UserCote> {
	
	RetourService retourService;	
	UserCote userCote;
	
	public RetourController() {
		this.retourService = new RetourService();		
	}
	
	
	
	
	@FXML TextField inputCote;
	@FXML Label cote;
	@FXML Label libelle;
	@FXML Label support;
	@FXML Label id;
	@FXML Label dateEmprunt;
	@FXML Label dateRetour;
	@FXML Label message;
	@FXML Button search;
	@FXML Button restitute;
	
	@FXML
	public void initialize() {		
		restitute.setDisable(true);		
		search.setOnAction(e -> {
			String libelle = inputCote.getText();
			if (!libelle.equals("")) {
				retourService.findCoteByLibelle(libelle).subscribe(
						requestResponse -> userCote = requestResponse,
						error -> popError(error.getMessage()),
						() -> {
							setUserCote(userCote);
							restitute.setDisable(false);
							System.out.println("Successfull");
						}
						);
			}				
		});
		
		restitute.setOnAction(e -> {
			confirmAlert.showAndWait();
			retourService.deleteUserCote(userCote).subscribe(
				response -> message.setText(response),
				error -> popError(error.getMessage()),
				() -> {
					System.out.println("Successfull");
					this.initialize();
				});
		});
		
	}
	
	private void setUserCote(UserCote userCote) {
		cote.setText(userCote.getCote().getLibelle());
		libelle.setText(userCote.getCote().getDocument().getLibelle());
		support.setText(userCote.getCote().getDocument().getSupport().getLibelle());
		id.setText(userCote.getUser().idProperty().toString());
		dateEmprunt.setText(userCote.getDateEmprunt().toString());
		dateRetour.setText(userCote.getDateEmprunt().plusDays(15).toString());
	}	

}
