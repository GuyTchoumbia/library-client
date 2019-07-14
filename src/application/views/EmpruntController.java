package application.views;

import java.util.Date;

import com.gluonhq.connect.GluonObservableObject;

import application.common.ControllerImpl;
import application.modele.User;
import application.modele.UserCote;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.UserService;

public class EmpruntController extends ControllerImpl<UserCote> {
	
	UserService userService = new UserService();
	
	@FXML private TableView<UserCote> empruntTable;
	@FXML private TableColumn<UserCote, String> titreColumn;
	@FXML private TableColumn<UserCote, String> supportColumn;
	@FXML private TableColumn<UserCote, String> coteColumn;
	@FXML private TableColumn<UserCote, Date> dateEmpruntColumn;
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
			System.out.println(id);
			if (!id.equals("")) {
				GluonObservableObject<User> user = userService.findUserById(id);
				user.addListener((obs, ov, nv) -> {
				    if (nv != null && user.get() != null) {
				        empruntTable.setItems(nv.userCotesProperty());
				    }
				});
			}
		});
		
		empruntButton.setOnAction(e-> {
			//popup?
		});
	}

}
