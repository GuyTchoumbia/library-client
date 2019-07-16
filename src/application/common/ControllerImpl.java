package application.common;

import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@Component
public class ControllerImpl<T extends Entity> implements Controller<T> {	
	
	protected ObservableList<T> list;
	protected Alert confirmAlert;
	protected Alert emptyAlert;
	protected Alert errorAlert;
	
	public ControllerImpl() {			
		confirmAlert = new Alert(AlertType.CONFIRMATION);
		confirmAlert.setContentText("Etes-vous sur?");
		emptyAlert = new Alert(AlertType.WARNING);
		errorAlert = new Alert(AlertType.ERROR);
		list = FXCollections.observableArrayList();
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
	
	@Override
	public void add(TextField input) {
		confirmAlert.showAndWait();
	}
	
	@Override
	public void remove(TableView<T> table) {
		confirmAlert.showAndWait();
	}

	@Override
	public void edit(CellEditEvent<T, String> e) {
		confirmAlert.showAndWait();		
	}	
	
	public void popError(String message) {
		errorAlert.setContentText(message);
		errorAlert.showAndWait();
	}
	
}
