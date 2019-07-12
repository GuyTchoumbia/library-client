package application.views;

import java.util.Optional;

import application.common.ControllerImpl;
import application.modele.User;
import application.utils.EditUserDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UtilisateurController extends ControllerImpl<User>{
	
	@FXML TextField inputNom;
	@FXML TextField inputPrenom;
	@FXML TableView<User> userTable;
	@FXML TableColumn<User, Number> idColumn;
	@FXML TableColumn<User, String> nomColumn;
	@FXML TableColumn<User, String> prenomColumn;
	@FXML TableColumn<User, String> adresseColumn;
	@FXML TableColumn<User, String> emailColumn;
	@FXML TableColumn<User, String> telephoneColumn;
	@FXML Button searchButton;
	@FXML Button newButton;
	@FXML ContextMenu context;
	@FXML MenuItem nouveauMenu;
	@FXML MenuItem editMenu;
	@FXML MenuItem deleteMenu;
	
	@FXML
	private void initialize() {
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
		adresseColumn.setCellValueFactory(cellData -> cellData.getValue().getAdresse().toStringProperty()); 
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
		userTable.setEditable(true);
		userTable.setItems(list);
		
		newButton.setOnAction(e -> {
			EditUserDialog dialog = new EditUserDialog(new User());
			Optional<User> result = dialog.showAndWait();
			result.ifPresent(response -> {
				//add to database
				list.add(result.get());
			});
		});
		nouveauMenu.setOnAction(e -> {
			EditUserDialog dialog = new EditUserDialog(new User());
			Optional<User> result = dialog.showAndWait();
			result.ifPresent(response -> {
				//add to database
				list.add(result.get());
			});
		});
		editMenu.setOnAction(e -> {
			EditUserDialog dialog = new EditUserDialog(userTable.getSelectionModel().getSelectedItem());
			Optional<User> result = dialog.showAndWait();
			result.ifPresent(response -> {
				//add to database
				list.set(userTable.getSelectionModel().getSelectedIndex(), result.get());
			});
		});		
		
		deleteMenu.setOnAction(e -> {
			confirm.showAndWait()
				.filter(response -> response == ButtonType.OK)
		      	.ifPresent(response -> {
		      		//remove from database
		      		list.remove(userTable.getSelectionModel().getSelectedItem());
		      	});			
		});
		context.setOnShowing(e -> {
			if (userTable.getSelectionModel().getSelectedItem() == null) {
				editMenu.setDisable(true);
				deleteMenu.setDisable(true);
			}
			else {
				editMenu.setDisable(false);	
				deleteMenu.setDisable(false);
			}
		});
		
		
	}

}
