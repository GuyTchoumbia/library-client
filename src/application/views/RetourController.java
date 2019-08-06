package application.views;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;

import application.common.ControllerImpl;
import application.modele.UserCote;
import application.services.RetourService;
import application.utils.LoggerUtils;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

@Controller
public class RetourController extends ControllerImpl<UserCote> {
	
	RetourService retourService;	
	UserCote userCote;
	ObservableList<String> properties;	
	
	public RetourController(RetourService retourService) {
		this.retourService = retourService;
		this.properties = FXCollections.observableArrayList();
	}
	
	@FXML TextField inputCote;	
	@FXML Button search;
	@FXML Button prolonge;
	@FXML Button restitute;
	@FXML ListView<String> listProperties;
	
	@FXML
	public void initialize() {	
		listProperties.setItems(properties);
		restitute.setDisable(true);
		prolonge.setDisable(true);		
		properties.addListener(new ListChangeListener<String>() {
			@Override
			public void onChanged(Change<? extends String> c) {				
				restitute.setDisable(false);
				prolonge.setDisable(false);				
			}			
		});
		
		search.setOnAction(e -> {
			properties.clear();
			String libelle = inputCote.getText();
			if (!libelle.equals("")) {				
				retourService.findUserCoteByCoteId(libelle).subscribe(
					requestResponse -> {
						this.userCote = requestResponse;						
						setList(requestResponse); 					
					},
					error -> LoggerUtils.log.warning(error.getMessage()),
					() -> LoggerUtils.log.info("Successfull")
				);
				listProperties.setDisable(false);
			}
		});
		
		restitute.setOnAction(e -> {
			this.userCote.setDateRetour(Date.valueOf(LocalDate.now()));
			confirmAlert.showAndWait()
				.filter(response -> response == ButtonType.OK)
				.ifPresent(response -> {
					properties.clear();
					retourService.saveUserCote(userCote).subscribe(					
						requestResponse -> setList(requestResponse),				
						error -> LoggerUtils.log.warning(error.getMessage()),
						() -> LoggerUtils.log.info("Successfull")
					);
				});
		});
		
		prolonge.setOnAction(e -> {
			this.userCote.setDateEmprunt(Date.valueOf(LocalDate.now()));
			confirmAlert.showAndWait()
				.filter(response -> response == ButtonType.OK)
				.ifPresent(response -> {
					properties.clear();
					retourService.saveUserCote(userCote).subscribe(
						requestResponse -> setList(requestResponse),				
						error -> LoggerUtils.log.warning(error.getMessage()),
						() -> LoggerUtils.log.info("Successfull")
					);
				});
		});		
		
	}	
	
	public void setList(UserCote userCote) {
		properties.add("Côte : " + userCote.getCote().getLibelle());
		properties.add("Titre : " + userCote.getCote().getDocument().getLibelle());
		properties.add("Support : " + userCote.getCote().getDocument().getSupport().getLibelle());
		properties.add("Numero de Carte : " + userCote.getUser().getId().toString());
		properties.add("Date d'emprunt : " + userCote.getDateEmprunt().toString());
		properties.add("Date de Retour prevue : " + userCote.getDateEmprunt().toLocalDate().plusDays(15).toString());
	}

}
