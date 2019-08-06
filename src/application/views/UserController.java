package application.views;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Controller;

import application.common.ControllerImpl;
import application.modele.Adress;
import application.modele.Civil;
import application.modele.Contact;
import application.modele.User;
import application.modele.UserCote;
import application.services.UserService;
import application.utils.EditUserDialog;
import application.utils.LoggerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@Controller
public class UserController extends ControllerImpl<User>{
	
	UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@FXML TextField inputId;
	@FXML TextField inputNom;
	@FXML TableView<User> userTable;
	@FXML TableColumn<User, Number> idColumn;
	@FXML TableColumn<User, String> nomColumn;
	@FXML TableColumn<User, String> prenomColumn;
	@FXML TableColumn<User, Date> dateNaissanceColumn;
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
	protected void initialize() {
		super.initialize();
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomColumn.setCellValueFactory(cellData -> cellData.getValue().getCivil().nomProperty());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().getCivil().prenomProperty());
		dateNaissanceColumn.setCellValueFactory(cellData -> cellData.getValue().getCivil().dateNaissanceProperty());
		adresseColumn.setCellValueFactory(cellData -> cellData.getValue().getContact().getAdress().toStringProperty()); 
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getContact().emailProperty());
		telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().getContact().phoneProperty());
		userTable.setEditable(true);
		userTable.setItems(list);		
		
		searchButton.setOnAction(e -> {
			String id = inputId.getText();
			String nom = inputNom.getText();
			if (!nom.equals("") || !id.equals("")) {
				userService
					.findUserByInfo(id, nom)
					.subscribe(
						data -> list.setAll(data),							
						requestError -> LoggerUtils.log.warning(requestError.getMessage()),							
						() -> LoggerUtils.log.info("Successfull")//						
					);					
			}
		});		
		
		newButton.setOnAction(e -> {
			Optional<User> result = getNewUserDialog();
			result.ifPresent(response -> {
				userService
					.update(response)
					.subscribe(
						requestResponse -> list.add(requestResponse),							
						requestError -> LoggerUtils.log.warning(requestError.getMessage()),							
						() -> LoggerUtils.log.info("Successfull")						
						);					
			});
		});
		nouveauMenu.setOnAction(e -> {
			Optional<User> result = getNewUserDialog();
			result.ifPresent(response -> {
				userService
					.update(response)
					.subscribe(
						requestResponse -> list.add(requestResponse),					
						requestError -> LoggerUtils.log.warning(requestError.getMessage()),							
						() -> LoggerUtils.log.info("Successfull")						
						);		
			});
		});
		editMenu.setOnAction(e -> {
			EditUserDialog dialog = new EditUserDialog(userTable.getSelectionModel().getSelectedItem());			
			Optional<User> result = dialog.showAndWait();
			result.ifPresent(response -> {
				userService
					.update(response)
					.subscribe(
						requestResponse -> list.set(userTable.getSelectionModel().getSelectedIndex(), requestResponse),			
						requestError -> LoggerUtils.log.warning(requestError.getMessage()),							
						() -> LoggerUtils.log.info("Successfull")						
						);						
			});
		});		
		
		deleteMenu.setOnAction(e -> {
			confirmAlert.showAndWait()
				.filter(response -> response == ButtonType.OK)
		      	.ifPresent(response -> {
		      		userService
		      			.delete(userTable.getSelectionModel().getSelectedItem())		      			
		      			.subscribe(
		      				requestResponse -> System.out.println(requestResponse),		      	
							requestError -> LoggerUtils.log.warning(requestError.getMessage()),							
							() -> LoggerUtils.log.info("Successful")							
							);		
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
	
	private Optional<User> getNewUserDialog() {
		User user = new User();
		user.setCivil(new Civil());
		user.setContact(new Contact());
		user.getContact().setAdress(new Adress());
		user.setUserCotes(new ArrayList<UserCote>());					 
		EditUserDialog dialog = new EditUserDialog(user);
		Optional<User> result = dialog.showAndWait();
		return result;
	}

}
