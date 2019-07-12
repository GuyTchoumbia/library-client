package application.views;

import java.util.Optional;

import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;

import application.common.ControllerImpl;
import application.modele.Document;
import application.modele.Support;
import application.utils.EditDocumentDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DocumentController extends ControllerImpl<Document> {
	
	@FXML private TableView<Document> documentTable;
	@FXML private TableColumn<Document, String> titreColumn;
	@FXML private TableColumn<Document, Number> idColumn;
	@FXML private TableColumn<Document, String> dateColumn;
	@FXML private ComboBox<Support> inputSupport;
	@FXML private TableColumn<Document, String> auteurColumn;
	@FXML private TableColumn<Document, String> editeurColumn;
	@FXML private TableColumn<Document, String> themeColumn;
	@FXML private TableColumn<Document, String> bibliothequeColumn;
	@FXML private ContextMenu context;
	@FXML private MenuItem modifMenu;
	@FXML private MenuItem ajouterMenu;
	@FXML private MenuItem deleteMenu;
	@FXML private TextField inputId;
	@FXML private TextField inputDate;
	@FXML private TextField inputTitre;
	@FXML private TextField inputAuteur;
	@FXML private TextField inputEditeur;
	@FXML private TextField inputTheme;
	@FXML private TextField inputBibliotheque;	
	@FXML private Button searchButton;
		
	@FXML
	private void initialize() {
		titreColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());	
		auteurColumn.setCellValueFactory(cellData -> explode(cellData.getValue().auteursProperty().getValue()));	
		editeurColumn.setCellValueFactory(cellData -> cellData.getValue().getEditeur().libelleProperty());	
		themeColumn.setCellValueFactory(cellData -> explode(cellData.getValue().tagsProperty().getValue()));	
		bibliothequeColumn.setCellValueFactory(cellData -> explode(cellData.getValue().librariesProperty().getValue()));	
		documentTable.setEditable(true);
		
		searchButton.setOnAction(e -> {
			RestClient restClient = RestClient.create()
			        .method("GET")
			        .host("http://localhost:8080/library-api/document/all");
			GluonObservableList<Document> documents = DataProvider.retrieveList(restClient.createListDataReader(Document.class));
			documentTable.setItems(documents);
		});
		
		
		context.setOnShowing(e -> {
			if (documentTable.getSelectionModel().getSelectedItem() == null) {
				modifMenu.setDisable(true);
				deleteMenu.setDisable(true);
			}
			else {
				modifMenu.setDisable(false);
				deleteMenu.setDisable(false);
			}
		});
		
		modifMenu.setOnAction(e -> {
			EditDocumentDialog dialog = new EditDocumentDialog(documentTable.getSelectionModel().getSelectedItem());
			Optional<Document> result = dialog.showAndWait();
			result.ifPresent(response -> {
				//here, insert into database
				list.set(documentTable.getSelectionModel().getSelectedIndex(), result.get());	
			});
		});
		ajouterMenu.setOnAction(e -> {
			EditDocumentDialog dialog = new EditDocumentDialog(new Document());
			Optional<Document> result = dialog.showAndWait();			
			result.ifPresent(response -> {
				//here, insert into database by service
				list.add(result.get());
			});
		});
		deleteMenu.setOnAction(e -> {
			confirm.showAndWait()
				.filter(response -> response == ButtonType.OK)
		      	.ifPresent(response -> {
		      		//remove from database
		      		list.remove(documentTable.getSelectionModel().getSelectedItem());
		      	});			
		});						
//		inputTitre.textProperty().addListener(new AutoCompleteTextFieldChangeListener<Document>(inputTitre, service, Document.class));
//		inputAuteur.textProperty().addListener(new AutoCompleteTextFieldChangeListener<Auteur>(inputAuteur, service, Auteur.class));
//		inputEditeur.textProperty().addListener(new AutoCompleteTextFieldChangeListener<Editeur>(inputEditeur, service, Editeur.class));
//		inputTheme.textProperty().addListener(new AutoCompleteTextFieldChangeListener<Theme>(inputTheme, service, Theme.class));
//		inputBibliotheque.textProperty().addListener(new AutoCompleteTextFieldChangeListener<Library>(inputBibliotheque, service, Library.class));
	}


}
