package application.views;

import java.sql.SQLException;

import application.common.ControllerImpl;
import application.modele.Auteur;
import application.modele.Document;
import application.modele.Tag;
import application.modele.Support;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.TextField;

public class EditDocumentController extends ControllerImpl<Document>{
		
	private Document document;	
	private boolean valid;
	/*
	 * this controller has a constructor with parameter to pass the data of the object from window to window
	 */	
	public EditDocumentController(Document document) {
		super();
		this.document = document;
		this.valid = false;
	}
		
	public EditDocumentController() {
		super();
		this.document = new Document();
	}

	@FXML private TextField inputTitre;
	@FXML private TextField inputDate; 
	@FXML private ComboBox<Support> inputSupport;
	@FXML private ListView<Auteur> listAuteurs;	
	@FXML private TextField inputEditeur;
	@FXML private ListView<Tag> listGenres;
	@FXML private Button editAuteurs;
	@FXML private Button editEditeurs;
	@FXML private Button editGenres;	
	@FXML private Label erreurTitre;
	@FXML private Label erreurDate;
	@FXML private Label erreurEditeur;
	
	@FXML 
	private void initialize() {
		inputTitre.textProperty().bindBidirectional(document.libelleProperty());
		inputDate.textProperty().bindBidirectional(document.dateProperty());
		inputEditeur.textProperty().bindBidirectional(document.getEditeur().libelleProperty());
		listAuteurs.itemsProperty().bindBidirectional(document.auteursProperty());
		listGenres.itemsProperty().bindBidirectional(document.tagsProperty());
		
		
//		inputTitre.textProperty().addListener(new AutoCompleteTextFieldChangeListener<Document>(inputTitre, service, Document.class));
//		inputEditeur.textProperty().addListener(new AutoCompleteTextFieldChangeListener<Editeur>(inputEditeur, service, Editeur.class));
		
//		listAuteurs.setCellFactory(listView -> {
//			EditableListCell<Auteur> listCellAuteur = new EditableListCell<Auteur>();
//			listCellAuteur.getField().textProperty().addListener(new AutoCompleteTextFieldChangeListener<Auteur>(listCellAuteur.getField(), service, Auteur.class));
//			return listCellAuteur;
//		});
//			
//		listThemes.setCellFactory(listView -> {
//			EditableListCell<Theme> listCellTheme = new EditableListCell<Theme>();
//			listCellTheme.getField().textProperty().addListener(new AutoCompleteTextFieldChangeListener<Theme>(listCellTheme.getField(), service, Theme.class));
//			return listCellTheme;
//		});
		editAuteurs.setOnAction(e -> editSelectedLine(listAuteurs));		
		editAuteurs.setOnAction(e -> editSelectedLine(listGenres));
		
		//validation form
		inputTitre.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputTitre.getText().matches("^.++$")) {
					erreurTitre.setText("titre incorrect");
				}
				else erreurTitre.setText("");
			}
		});		
		inputDate.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputDate.getText().matches("^\\d{4}$")) {
					erreurDate.setText("date incorrect");
				}
				else erreurDate.setText("");
			}
		});
		inputEditeur.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputEditeur.getText().matches("^.++$")) {
					erreurEditeur.setText("editeur incorrect");
				}
				else erreurEditeur.setText("");
			}
		});
	}
	
	
	public boolean validate() {	
		if (!inputTitre.getText().matches("^.++$"))
			setValid(false);		
		else if (!inputDate.getText().matches("^\\d{4}$"))
			setValid(false);
		else if (!inputEditeur.getText().matches("^.++$"))
			setValid(false);
		else setValid(true);
		return valid;	
	}
	/*
	 * button actions
	 */
	public void insertAuteur() {
		Auteur auteur = new Auteur();
		document.getAuteurs().add(auteur);
		listAuteurs.layout();
		listAuteurs.edit(listAuteurs.getItems().size()-1);		
	}
	
	public void removeAuteur() {
		if (listAuteurs.getSelectionModel().getSelectedItem() != null) {
			document.getAuteurs().remove(listAuteurs.getSelectionModel().getSelectedItem());
			initialize();
		}		
	}
	
	public <T> void editSelectedLine(ListView<T> list) {
		list.edit(list.getSelectionModel().getSelectedIndex());
	}
	
	public void editCommitAuteur(EditEvent<Auteur> e) throws SQLException {
		e.getSource().getItems().get(e.getIndex()).setLibelle(e.getNewValue().getLibelle());
	}	
		
	public void insertTheme() {
		Tag theme = new Tag();
		document.getTags().add(theme);
		listGenres.layout();
		listGenres.edit(listGenres.getItems().size()-1);
	}
	
	public void removeTheme() {
		if (listGenres.getSelectionModel().getSelectedItem() != null) {
			document.getTags().remove(listGenres.getSelectionModel().getSelectedItem());
		}		
	}
	
	public void editCommitTheme(EditEvent<Tag> e) throws SQLException {
		e.getSource().getItems().get(e.getIndex()).setLibelle(e.getNewValue().getLibelle());
	}

	public void setDocument(Document document) {
		this.document = document;	
	}
	
	public boolean getValid() { return this.valid; }
	public void setValid(boolean valid) { this.valid = valid; }
	
}

