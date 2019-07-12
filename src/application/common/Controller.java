package application.common;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface Controller<T> {
	
	void add(TextField innput);
	void remove(TableView<T> table);
	void edit(CellEditEvent<T, String> e);
	
	<U> StringProperty explode(ObservableList<U> list);	
}
