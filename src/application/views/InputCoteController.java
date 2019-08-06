package application.views;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import application.modele.Cote;
import application.services.CoteService;
import application.utils.LoggerUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

@Controller
public class InputCoteController {
	
	private Cote cote;
	CoteService coteService;
	ObservableList<String> properties;	

	public InputCoteController(CoteService coteService) {		
		this.coteService = coteService;
		this.properties = FXCollections.observableArrayList();
	}

	@FXML ListView<String> listProperty;
	@FXML Button searchButton;
	@FXML TextField inputCote;
	
	@FXML
	public void initialize() {
		listProperty.setItems(properties);		
		searchButton.setOnAction(e -> {
			if (!inputCote.getText().equals("")) {
				coteService.findCoteByLibelle(inputCote.getText()).subscribe(
					response -> {						
						this.cote = response;
						setList(response);
					},
					error -> LoggerUtils.log.warning(error.getMessage()),
					() -> LoggerUtils.log.info("Successfull")
				);
			}
		});
	}
	
	public Cote getCote() {
		return cote;
	}

	public void setCote(Cote cote) {
		this.cote = cote;
	}
	
	private void setList(Cote cote) {
		properties.add("Côte : " + cote.getLibelle());
		properties.add("Titre : " + cote.getDocument().getLibelle());
		properties.add("Support : " + cote.getDocument().getSupport().getLibelle());
		properties.add("Bibliotheque : " + cote.getLibrary().getLibelle());
		properties.add("Auteur(s) : " + String.join(", ", cote.getDocument().getAuteurs()
			.stream()
			.map(element -> element.getLibelle())
			.collect(Collectors.toList())));
		properties.add("Editeur : " + cote.getDocument().getEditeur().getLibelle());
		properties.add("Année de publication : " + cote.getDocument().getDate());
	}
	
	

}
