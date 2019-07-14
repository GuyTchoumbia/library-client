package application.views;

import application.common.ControllerImpl;
import application.modele.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditUserController extends ControllerImpl<User>{
	
	private User user;
	private boolean valid;
	
	public EditUserController(User user) {
		super();
		this.user = user;
		this.valid = false;
	}
	
	@FXML TextField inputNom;
	@FXML TextField inputPrenom;
	@FXML TextField inputNumero;
	@FXML TextField inputRue;
	@FXML TextField inputCodePostal;
	@FXML TextField inputVille;
	@FXML TextField inputEmail;
	@FXML TextField inputTelephone;
	@FXML Label erreurNom;
	@FXML Label erreurPrenom;
	@FXML Label erreurNumero;
	@FXML Label erreurRue;
	@FXML Label erreurCodePostal;
	@FXML Label erreurVille;
	@FXML Label erreurEmail;
	@FXML Label erreurTelephone;
	
	@FXML
	private void initialize() {
		inputNom.textProperty().bindBidirectional(user.getCivil().nomProperty());
		inputPrenom.textProperty().bindBidirectional(user.getCivil().prenomProperty());
		inputRue.textProperty().bindBidirectional(user.getContact().getAdress().rueProperty());
		inputNumero.textProperty().bindBidirectional(user.getContact().getAdress().numeroProperty());
		inputCodePostal.textProperty().bindBidirectional(user.getContact().getAdress().codePostalProperty());
		inputVille.textProperty().bindBidirectional(user.getContact().getAdress().villeProperty());
		inputEmail.textProperty().bindBidirectional(user.getContact().emailProperty());
		inputTelephone.textProperty().bindBidirectional(user.getContact().phoneProperty());		
		
		//validation front
		
		inputNom.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputNom.getText().matches("^.++$")) {
					erreurNom.setText("nom incorrect");
				}
				else erreurNom.setText("");
			}
		});
		inputPrenom.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputPrenom.getText().matches("^.++$")) {
					erreurPrenom.setText("pr�nom incorrect");
				}
				else erreurPrenom.setText("");
			}
		});
		inputNumero.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputNumero.getText().matches("^\\d++$")) {
					erreurNumero.setText("numero de rue incorrect");
				}
				else erreurNumero.setText("");
			}
		});
		inputRue.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputRue.getText().matches("^.++$")) {
					erreurRue.setText("rue incorrecte");
				}
				else erreurRue.setText("");
			}
		});
		inputCodePostal.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputCodePostal.getText().matches("^\\d{5}$")) {
					erreurCodePostal.setText("code postal incorrect");
				}
				else erreurCodePostal.setText("");
			}
		});
		inputVille.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputVille.getText().matches("^.++$")) {
					erreurVille.setText("ville incorrecte");
				}
				else erreurVille.setText("");
			}
		});
		inputEmail.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputEmail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
					erreurEmail.setText("email incorrect");
				}
				else erreurEmail.setText("");
			}
		});
		inputTelephone.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputTelephone.getText().matches("^\\d{10}$")) {
					erreurTelephone.setText("numero de telephone incorrect");
				}
				else erreurTelephone.setText("");
			}
		});
	}
	
	public boolean validate() {
		if (!inputNom.getText().matches("^.++$")) 
			setValid(false);
		else if (!inputPrenom.getText().matches("^.++$"))
			setValid(false);
		else if (!inputNumero.getText().matches("^\\d++$"))
			setValid(false);
		else if (!inputRue.getText().matches("^.++$"))
			setValid(false);
		else if (!inputCodePostal.getText().matches("^\\d{5}$"))
			setValid(false);
		else if (!inputVille.getText().matches("^.++$"))
			setValid(false);
		else if (!inputEmail.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"))
			setValid(false);
		else if (!inputTelephone.getText().matches("^\\d{10}$"))
			setValid(false);
		else setValid(true);
		return valid;
	}
	
	public boolean getValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
