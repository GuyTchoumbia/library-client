package application.views;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import application.common.ControllerImpl;
import application.modele.User;
import application.services.UserService;
import application.utils.EditUserDialog;
import application.utils.LoggerUtils;
import application.utils.SpringFxmlLoader;
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
	
	private UserService userService;		
	private SpringFxmlLoader loader;
	private EditUserController editUserController;
	private User defaultUser;
	
	@Lazy
	public UserController(UserService userService, SpringFxmlLoader loader, EditUserController editUserController) {
		this.userService = userService;		
		this.loader = loader;
		this.editUserController = editUserController;
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
			EditUserDialog dialog = new EditUserDialog(userTable.getSelectionModel().getSelectedItem(), loader, editUserController);			
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
		EditUserDialog dialog = new EditUserDialog(defaultUser, loader, editUserController);
		Optional<User> result = dialog.showAndWait();
		return result;
	}		
	
	@Autowired
	public void setDefaultUser(User defaultUser) {
		this.defaultUser = defaultUser;
	}

}
