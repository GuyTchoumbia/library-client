package application.views;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import application.common.ControllerImpl;
import application.modele.Cote;
import application.modele.User;
import application.modele.UserCote;
import application.services.UserService;
import application.utils.InputCoteDialog;
import application.utils.LoggerUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@Controller
public class EmpruntController extends ControllerImpl<UserCote> {
	
	private UserService userService;
	private ObservableList<UserCote> emprunts;
	private ObservableList<UserCote> reservations;
	private User user;

	
	public EmpruntController(UserService userService) {
		this.userService = userService;
		this.list = FXCollections.observableArrayList();
		this.emprunts = FXCollections.observableArrayList();
		this.reservations = FXCollections.observableArrayList();
	}
	
	@FXML private TableView<UserCote> empruntTable;
	@FXML private TableView<UserCote> reservationTable;
	@FXML private TableColumn<UserCote, String> libelleEmpruntColumn;
	@FXML private TableColumn<UserCote, String> supportEmpruntColumn; 
	@FXML private TableColumn<UserCote, String> coteEmpruntColumn;
	@FXML private TableColumn<UserCote, Date> dateEmpruntColumn;
	@FXML private TableColumn<UserCote, Date> dateRetourColumn;
	@FXML private TableColumn<UserCote, String> libelleReservationColumn;
	@FXML private TableColumn<UserCote, String> supportReservationColumn;
	@FXML private TableColumn<UserCote, String> coteReservationColumn;
	@FXML private TableColumn<UserCote, Date> dateReservationColumn;
	@FXML private Button searchCardButton;
	@FXML private Button empruntButton;
	@FXML private TextField inputCard;
	@FXML private MenuItem ajouterMenuItem;
	@FXML private MenuItem prolongeMenuItem;
	@FXML private MenuItem restituteMenuItem;
	@FXML private MenuItem deleteReservationMenuItem;
	
	@FXML 
	protected void initialize() {
		super.initialize();
		libelleEmpruntColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().getDocument().libelleProperty());
		supportEmpruntColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().getDocument().getSupport().libelleProperty());
		coteEmpruntColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().libelleProperty());
		dateEmpruntColumn.setCellValueFactory(cellData -> cellData.getValue().dateEmpruntProperty());
		dateRetourColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(Date.valueOf(cellData.getValue().getDateEmprunt().toLocalDate().plusDays(15))));
		libelleReservationColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().getDocument().libelleProperty());
		supportReservationColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().getDocument().getSupport().libelleProperty());
		coteReservationColumn.setCellValueFactory(cellData -> cellData.getValue().getCote().libelleProperty());
		dateReservationColumn.setCellValueFactory(cellData -> cellData.getValue().dateReservationProperty());
		empruntTable.setItems(emprunts);
		reservationTable.setItems(reservations);
		
		searchCardButton.setOnAction(e -> {
			String id = inputCard.getText();
			if (!id.equals("")) {			
				userService.findById(id).subscribe(
					requestResponse -> {
						parse(requestResponse.getUserCotes());
						this.user = requestResponse;
					},
					error -> LoggerUtils.log.warning(error.getMessage()),
					() -> LoggerUtils.log.info("Successfull")
				);				
			}
		});		
		empruntButton.setOnAction(e-> {
			// TODO une popup demandant la cote du document a emprunter
		});
		
		ajouterMenuItem.setOnAction(e -> {
			Optional<Cote> result = getInputCoteDialog();
			if (result.get() != null) {
				UserCote newEmprunt = new UserCote();
				newEmprunt.setCote(result.get());
				newEmprunt.setUser(user);
				newEmprunt.setDateEmprunt(Date.valueOf(LocalDate.now()));
				userService.saveUserCote(newEmprunt).subscribe(
					response -> emprunts.add(response),
					error -> LoggerUtils.log.warning(error.getMessage()),
					() -> LoggerUtils.log.info("Success")
				);
			}
		});
		
		deleteReservationMenuItem.setOnAction(e -> {
			UserCote selectedItem = reservationTable.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				userService.deleteUserCote(selectedItem).subscribe(
					response -> LoggerUtils.log.info("Successfull"),
					error -> LoggerUtils.log.warning(error.getMessage()),
					() -> reservations.remove(selectedItem)
				);
			}
		});
		
		restituteMenuItem.setOnAction(e -> {
			UserCote selectedItem = reservationTable.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				selectedItem.setDateRetour(Date.valueOf(LocalDate.now()));
				userService.saveUserCote(selectedItem).subscribe(
					response -> LoggerUtils.log.info("Successfull"),
					error -> LoggerUtils.log.warning(error.getMessage()),
					() -> emprunts.remove(selectedItem)
				);
			}
		});
		
		prolongeMenuItem.setOnAction(e -> {
			UserCote selectedItem = empruntTable.getSelectionModel().getSelectedItem();
			Integer selectedIndex = empruntTable.getSelectionModel().getSelectedIndex();
			if (selectedItem != null) {
				selectedItem.setDateEmprunt(Date.valueOf(LocalDate.now()));
				userService.saveUserCote(selectedItem).subscribe(
					response -> LoggerUtils.log.info("Successfull"),
					error -> LoggerUtils.log.warning(error.getMessage()),
					() -> emprunts.set(selectedIndex, selectedItem)
				);
			}
		});
		
		
		
	}

	private void parse(List<UserCote> userCotes) {
		if (userCotes.size() != 0) {
			emprunts.setAll(FXCollections.observableArrayList(userCotes
				.stream()
				.filter(element -> element.getDateEmprunt() != null && element.getDateRetour() == null)
				.collect(Collectors.toList())));
			reservations.setAll(FXCollections.observableArrayList(userCotes.stream()
			   .filter(element -> element.getDateReservation() != null && element.getDateEmprunt() == null)
			   .collect(Collectors.toList())));			
		}
	}	
	
	private Optional<Cote> getInputCoteDialog() {
		InputCoteDialog dialog = new InputCoteDialog();
		Optional<Cote> result = dialog.showAndWait();
		return result;
	}

}
