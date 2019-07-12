package application.common;

import java.sql.Connection;
import java.util.Optional;

import application.modele.Document;
import application.modele.User;
import application.utils.EditDocumentDialog;
import application.utils.EditUserDialog;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class ControllerImpl<T extends Entity> implements Controller<T>{	
	
	
	protected Connection cnx;
	
	protected ObservableList<T> list;
	
	protected Alert confirm;
	protected Alert empty;
	
	public ControllerImpl() {		
		list = FXCollections.observableArrayList();
		confirm = new Alert(AlertType.CONFIRMATION);
	}		
	
	/*
	 * converts lists into a string(property) for display
	 */
	@Override
	public <U> SimpleStringProperty explode(ObservableList<U> list) {
		String s = "";
		try {
			for (U element : list) {
				if (element!=null) {
					s += element.toString();
					if (list.indexOf(element)!= list.size()-1) {
						s += ", ";
					}
				}
			}
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		return new SimpleStringProperty(s);
	}
	
	/*
	 * new window for editing 
	 */
		
	public void editDocumentMenu(Document document) {
		EditDocumentDialog dialog = new EditDocumentDialog(document);
		Optional<Document> result = dialog.showAndWait();
		result.orElse(null);		
	}
	
	public User editUserMenu(User user) {
		EditUserDialog dialog =  new EditUserDialog(user);
		return dialog.getResult();
	}

	@Override
	public void add(TextField input) {
		confirm.showAndWait();
	}
	
	@Override
	public void remove(TableView<T> table) {
		confirm.showAndWait();
	}

	@Override
	public void edit(CellEditEvent<T, String> e) {
		confirm.showAndWait();		
	}	
	
}
