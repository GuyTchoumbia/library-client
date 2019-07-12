package application.views;

import java.util.Date;

import application.common.ControllerImpl;
import application.modele.UserCote;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmpruntController extends ControllerImpl<UserCote> {
	
	@FXML private TableView<UserCote> empruntTable;
	@FXML private TableColumn<UserCote, String> titreColumn;
	@FXML private TableColumn<UserCote, String> supportColumn;
	@FXML private TableColumn<UserCote, Number> coteColumn;
	@FXML private TableColumn<UserCote, Date> dateEmpruntColumn;
	@FXML private TableColumn<UserCote, Date> dateRetourColumn;
	@FXML private Button searchCardButton;
	@FXML private Button empruntButton;
	@FXML private TextField inputCard;
	
	@FXML 
	private void initialize() {
		titreColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().libelleProperty());
		supportColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().getDocument().getSupport().libelleProperty());
		coteColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().idProperty());
		dateEmpruntColumn.setCellValueFactory(cellData -> cellData.getValue().dateEmprunt());
		dateRetourColumn.setCellValueFactory(cellData -> cellData.getValue().dateRetour());
		
		searchCardButton.setOnAction(e -> {
			if (!inputCard.getText().equals("")) {
				//recherche user par id
				//si trouve, obtient les objets UserCote associ� a l'utilisateur et les affiche dans la table
				//active le bouton "emprunt"
			}
		});
		
		empruntButton.setOnAction(e-> {
			//popup?
		});
	}

}
