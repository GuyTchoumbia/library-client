package application.utils;

import application.common.Entity;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EditableListCell<T extends Entity> extends ListCell<T> {
    
	private final AutoCompleteTextField<T> field;	
	
    public EditableListCell() {
    	field = new AutoCompleteTextField<T>();
        field.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
        field.setOnAction(e -> {
            getItem().setLibelle(field.getText());
            setText(field.getText());
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        });
        setGraphic(field);
                
    }
    
    public AutoCompleteTextField<T> getField() {
		return field;
	}



	@Override
    protected void updateItem(T t, boolean empty) {
        super.updateItem(t, empty);
        if (isEditing()) {
            field.setText(t.getLibelle());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else {
            setContentDisplay(ContentDisplay.TEXT_ONLY);
            if (empty) {
                setText(null);
            } else {
                setText(t.getLibelle());
            }
        }
    }

    public void startEdit() {
        super.startEdit();
        field.setText(getItem().getLibelle());
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        field.requestFocus();
        field.selectAll();
    }

    
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem().getLibelle());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
}