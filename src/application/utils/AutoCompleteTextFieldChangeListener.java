package application.utils;

import application.common.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class AutoCompleteTextFieldChangeListener<T extends Entity> implements ChangeListener<String> {

	private AutoCompleteTextField<T> field;
	private ObservableList<T> source;
	
	public AutoCompleteTextFieldChangeListener(AutoCompleteTextField<T> field, ObservableList<T> source) {
		super();
		this.field = field;
		this.source = source;
	}
		
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (!newValue.equals("") && newValue.length() > 3) {
			try {
				field.setSource(source);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	

}
