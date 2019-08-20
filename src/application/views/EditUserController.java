package application.views;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import application.common.ControllerImpl;
import application.modele.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Lazy
@Controller
public class EditUserController extends ControllerImpl<User>{
	
	private User user;
	private boolean valid;
	private String errorMessage;
	
//	public EditUserController(User user) {
//		super();
//		this.user = user;
//		this.valid = false;
//		this.errorMessage = "";
//	}
	
	public EditUserController() {
		this.valid = false;
		this.errorMessage = "";
	}	
	
	@FXML TextField inputNom;
	@FXML TextField inputPrenom;
	@FXML TextField inputNumero;
	@FXML TextField inputRue;
	@FXML TextField inputCodePostal;
	@FXML TextField inputVille;
	@FXML TextField inputEmail;
	@FXML TextField inputTelephone;
	@FXML DatePicker inputDate;
	@FXML Label erreurNom;
	@FXML Label erreurPrenom;
	@FXML Label erreurNumero;
	@FXML Label erreurRue;
	@FXML Label erreurCodePostal;
	@FXML Label erreurVille;
	@FXML Label erreurEmail;
	@FXML Label erreurTelephone;
	
	@FXML
	protected void initialize() {	
		super.initialize();
		inputNom.textProperty().bindBidirectional(user.getCivil().nomProperty());
		inputPrenom.textProperty().bindBidirectional(user.getCivil().prenomProperty());
		inputRue.textProperty().bindBidirectional(user.getContact().getAdress().rueProperty());
		inputNumero.textProperty().bindBidirectional(user.getContact().getAdress().numeroProperty());
		inputCodePostal.textProperty().bindBidirectional(user.getContact().getAdress().codePostalProperty());
		inputVille.textProperty().bindBidirectional(user.getContact().getAdress().villeProperty());
		inputEmail.textProperty().bindBidirectional(user.getContact().emailProperty());
		inputTelephone.textProperty().bindBidirectional(user.getContact().phoneProperty());	
//		inputDate.valueProperty().bindBidirectional(new SimpleObjectProperty<LocalDate>(user.getCivil().getDateNaissance().toLocalDate()));
		
		// validation front		
		inputNom.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputNom.getText().equals("") || !inputNom.getText().matches("^.++$")) {
					erreurNom.setText("nom incorrect");
				}
				else erreurNom.setText("");
			}
		});		
		inputPrenom.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputPrenom.getText().equals("") || !inputPrenom.getText().matches("^.++$")) {
					erreurPrenom.setText("prenom incorrect");
				}
				else erreurPrenom.setText("");
			}
		});
		inputNumero.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputNumero.getText().equals("") || !inputNumero.getText().matches("^\\d++$")) {
					erreurNumero.setText("numero de rue incorrect");
				}
				else erreurNumero.setText("");
			}
		});
		inputRue.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputRue.getText().equals("") || !inputRue.getText().matches("^.++$")) {
					erreurRue.setText("rue incorrecte");
				}
				else erreurRue.setText("");
			}
		});
		inputCodePostal.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputCodePostal.getText().equals("") || !inputCodePostal.getText().matches("^\\d{5}$")) {
					erreurCodePostal.setText("code postal incorrect");
				}
				else erreurCodePostal.setText("");
			}
		});
		inputVille.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputVille.getText().equals("") || !inputVille.getText().matches("^.++$")) {
					erreurVille.setText("ville incorrecte");
				}
				else erreurVille.setText("");
			}
		});
		inputEmail.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputEmail.getText().equals("") || !inputEmail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
					erreurEmail.setText("email incorrect");
				}
				else erreurEmail.setText("");
			}
		});
		inputTelephone.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (inputTelephone.getText().equals("") || !inputTelephone.getText().matches("^\\d{10}$")) {
					erreurTelephone.setText("numero de telephone incorrect");
				}
				else erreurTelephone.setText("");
			}
		});
	}
	
	public boolean validate() {
		if (inputNom.getText() == null || !inputNom.getText().matches("^.++$")) {
			setValid(false);
			errorMessage += "Format de Nom incorrect\n";
		}
		else if (inputPrenom.getText() == null || !inputPrenom.getText().matches("^.++$")) {
			setValid(false);
			errorMessage += "Format de Prénom incorrect\n";
		}
		else if (inputNumero.getText() == null || !inputNumero.getText().matches("^\\d++$")) {
			setValid(false);
			errorMessage += "Format de numero de rue incorrect\n";
		}
		else if (inputRue.getText() == null || !inputRue.getText().matches("^.++$")) {
			setValid(false);
			errorMessage += "Format de rue incorrect\n";
		}
		else if (inputCodePostal.getText() == null || !inputCodePostal.getText().matches("^\\d{5}$")) {
			setValid(false);
			errorMessage += "Format de numero de Code Postal incorrect\n";
		}
		else if (inputVille.getText() == null || !inputVille.getText().matches("^.++$")) {
			setValid(false);
			errorMessage += "Format de Ville incorrect\n";
		}
		else if (inputEmail.getText() == null || !inputEmail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {		
			setValid(false);
			errorMessage += "Format d'adresse mail incorrect\n";
		}
		else if (inputTelephone.getText() == null || !inputTelephone.getText().matches("^\\d{10}$")) {
			setValid(false);
			errorMessage += "Format de numero telephone incorrect\n";
		}
		else setValid(true);
		return valid;
	}
	
	public boolean getValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public User getUser() {
		return this.user;		
	}
	
	public void setUser(User user) {
		this.user = user;		
	}	

}
