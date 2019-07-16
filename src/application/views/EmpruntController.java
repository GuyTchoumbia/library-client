package application.views;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import application.common.ControllerImpl;
import application.modele.UserCote;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.UserService;

@Component
public class EmpruntController extends ControllerImpl<UserCote> {
	
	private UserService userService = new UserService();		
	
	@FXML private TableView<UserCote> empruntTable;
	@FXML private TableColumn<UserCote, String> titreColumn;
	@FXML private TableColumn<UserCote, String> supportColumn;
	@FXML private TableColumn<UserCote, String> coteColumn;
	@FXML private TableColumn<UserCote, LocalDate> dateEmpruntColumn;
	@FXML private Button searchCardButton;
	@FXML private Button empruntButton;
	@FXML private TextField inputCard;
	
	@FXML 
	private void initialize() {
		titreColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().getDocument().libelleProperty());
		supportColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().getDocument().getSupport().libelleProperty());
		coteColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().libelleProperty());
		dateEmpruntColumn.setCellValueFactory(cellData -> cellData.getValue().dateEmpruntProperty());
		
		searchCardButton.setOnAction(e -> {
			String id = inputCard.getText();
			if (!id.equals("")) {			
				userService.findById(id).subscribe(requestResponse -> empruntTable.setItems(FXCollections.observableArrayList(requestResponse.getUserCotes())));
			}
		});		
		empruntButton.setOnAction(e-> {
			// TODO une popup demandant la cote du document a emprunter
		});
	}		

}
