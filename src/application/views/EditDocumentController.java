package application.views;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;

import application.common.ControllerImpl;
import application.modele.Auteur;
import application.modele.Cote;
import application.modele.Document;
import application.modele.Support;
import application.modele.Tag;
import application.services.DocumentService;
import application.utils.EditableListCell;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

@Controller
public class EditDocumentController extends ControllerImpl<Document>{
	
	private Document document;		
	private boolean valid;
	private String errorMessage;
	private DocumentService documentService;
	/*
	 * this controller has a constructor with parameter to pass the data of the object from window to window
	 */	
	
	public EditDocumentController(DocumentService documentService) {
		this.documentService = documentService;
		this.valid = false;
		this.errorMessage = "";
	}
	
	@FXML private TextField inputTitre;
	@FXML private TextField inputIsbn;
	@FXML private TextField inputDate; 
	@FXML private ComboBox<Support> selectSupport;
	@FXML private ListView<Auteur> listAuteurs;	
	@FXML private TextField inputEditeur;
	@FXML private ListView<Tag> listTags;
	@FXML private Button addAuteur;
	@FXML private Button removeAuteur;
	@FXML private Button editAuteur;
	@FXML private Button addTag;
	@FXML private Button removeTag;
	@FXML private Button editTag;
	@FXML private CheckBox premier;
	@FXML private CheckBox deuxieme;
	@FXML private CheckBox troisieme;
	@FXML private CheckBox quatrieme;
	@FXML private CheckBox cinquieme;
	@FXML private Label erreurTitre;
	@FXML private Label erreurDate;
	@FXML private Label erreurEditeur;
	
	@FXML 
	protected void initialize() {	
		super.initialize();
		inputTitre.textProperty().bindBidirectional(document.libelleProperty());
		Bindings.bindBidirectional(inputDate.textProperty(), document.dateProperty(), new NumberStringConverter());
		inputIsbn.textProperty().bindBidirectional(document.isbnProperty());
		inputEditeur.textProperty().bindBidirectional(document.getEditeur().libelleProperty());
		listAuteurs.itemsProperty().bindBidirectional(document.auteursProperty());
		listTags.itemsProperty().bindBidirectional(document.tagsProperty());
		
		// TODO link checkbox to librairies
		List<CheckBox> checkboxes = Arrays.asList(premier, deuxieme, troisieme, quatrieme, cinquieme);		
		for (Cote cote : document.getCotes()) {
			for (CheckBox checkbox : checkboxes) {
				if (cote.getLibrary().getLibelle() == checkbox.getText()) {
				checkbox.setSelected(true);
				};
			}
		}		
		selectSupport.setItems(FXCollections.emptyObservableList());
		documentService.findAllSupports().subscribe(
				data -> selectSupport.setItems(FXCollections.observableArrayList(data)),
				error -> popError(error.getMessage()),
				() -> System.out.println("Successfull"));	
		
		// TODO add autocomplete to list cells;
//		inputTitre.textProperty().addListener(
//				new AutoCompleteTextFieldChangeListener<Document>(inputTitre, documentService.autoCompleteLibelle(inputTitre.getText())));
//		inputEditeur.textProperty().addListener(
//				new AutoCompleteTextFieldChangeListener<Editeur>(inputEditeur, documentService.autoCompleteEditeur(inputEditeur.getText())));
		
		listAuteurs.setCellFactory(listView -> {
			EditableListCell<Auteur> listCellAuteur = new EditableListCell<Auteur>();
//			listCellAuteur.getField().textProperty().addListener(
//					new AutoCompleteTextFieldChangeListener<Auteur>(listCellAuteur.getField(), documentService.autoCompleteAuteur(listCellAuteur.getField().getText())));
			return listCellAuteur;
		});
			
		listTags.setCellFactory(listView -> {
			EditableListCell<Tag> listCellTag = new EditableListCell<Tag>();
//			listCellTag.getField().textProperty().addListener(
//					new AutoCompleteTextFieldChangeListener<Tag>(listCellTag.getField(), documentService.autoCompleteTag(listCellTag.getField().getText())));
			return listCellTag;
		});
		editAuteur.setOnAction(e -> editSelectedLine(listAuteurs));		
		editAuteur.setOnAction(e -> editSelectedLine(listTags));
		addAuteur.setOnAction(e -> {
			Auteur auteur = new Auteur();
			document.getAuteurs().add(auteur);
			listAuteurs.layout();
			listAuteurs.edit(listAuteurs.getItems().size()-1);		
		});
		addTag.setOnAction(e -> {
			Tag tag = new Tag();
			document.getTags().add(tag);
			listAuteurs.layout();
			listAuteurs.edit(listTags.getItems().size()-1);		
		});
		removeAuteur.setOnAction(e-> {
			if (listAuteurs.getSelectionModel().getSelectedItem() != null) {		
				document.getAuteurs().remove(listAuteurs.getSelectionModel().getSelectedItem());
				initialize();
			}
		});
		removeTag.setOnAction(e-> {
			if (listTags.getSelectionModel().getSelectedItem() != null) {		
				document.getTags().remove(listTags.getSelectionModel().getSelectedItem());
				initialize();
			}
		});
		
		//validation form
		inputTitre.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputTitre.getText().matches("^.++$")) {
					erreurTitre.setText("Titre incorrect");
				}
				else erreurTitre.setText("");
			}
		});		
		inputDate.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputDate.getText().matches("^\\d{4}$")) {
					erreurDate.setText("Date incorrecte");
				}
				else erreurDate.setText("");
			}
		});
		inputEditeur.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputEditeur.getText().matches("^.++$")) {
					erreurEditeur.setText("Editeur incorrect");
				}
				else erreurEditeur.setText("");
			}
		});
		inputIsbn.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue) {
				if (!inputEditeur.getText().matches("^.++$")) {
					erreurEditeur.setText("Isbn incorrect");					
				}
				else erreurEditeur.setText("");
			}
		});		
		listTags.setOnEditCommit(e-> {
			e.getSource().getItems().get(e.getIndex()).setLibelle(e.getNewValue().getLibelle());
		});
		listAuteurs.setOnEditCommit(e -> {
			e.getSource().getItems().get(e.getIndex()).setLibelle(e.getNewValue().getLibelle());
		});
		
	}
	
	
	public boolean validate() {	
		if (!inputTitre.getText().matches("^.++$")) {
			valid = false;
			errorMessage += "Mauvais format de Titre\n";
		}
		else if (!inputDate.getText().matches("^\\d{4}$")) {
			valid = false;
			errorMessage += "Mauvais format de Date\n";
		}
		else if (!inputEditeur.getText().matches("^.++$")) {
			valid = false;
			errorMessage += "Mauvais format pour l'Editeur\n";
		}
		else if (!inputIsbn.getText().matches("^.++$")) {
			valid = false;
			errorMessage += "Mauvais format de ISBN\n";
		}
		else if (selectSupport.getSelectionModel().getSelectedItem() == null) {
			valid = false;
			errorMessage += "Veuillez choisir un Support\n";
		}		
		return valid;	
	}	
	
	public <T> void editSelectedLine(ListView<T> list) {
		list.edit(list.getSelectionModel().getSelectedIndex());
	}
	
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;	
	}
	
	
	public boolean getValid() { return this.valid; }
	public void setValid(boolean valid) { this.valid = valid; }
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessag(String message) {
		this.errorMessage = message;
	}
	
}

