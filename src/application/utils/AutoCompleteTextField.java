package application.utils;

import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;	
	

/*
 * champ de texte avec auto completion fait-main: 
 * 	un champ classique auquel on ajoute un menu contextuel qui apparait/disparait selon la pr�sence ou non d'objet dans la liste "source"
 * on ajoute un ClickEvent a chaque objet de la liste pour remplacer le texte du champ par le texte de l'objet.
 */
public class AutoCompleteTextField<T> extends TextField {

	ContextMenu contextMenu;
			
	public AutoCompleteTextField() {
		this.contextMenu = new ContextMenu();
		this.setContextMenu(contextMenu);
	}
	
	public AutoCompleteTextField(ObservableList<T> source) {
		this();
		setSource(source);
	}	
	
	public void setSource(ObservableList<T> source) {		
		if (!source.isEmpty()) {			
			for (T entity : source) {
				MenuItem item = new MenuItem(entity.toString());
				item.setOnAction(e -> {
					this.setText(item.getText());
				});
				this.getContextMenu().getItems().add(item);
			}			
			this.getContextMenu().show(this, Side.BOTTOM, 0, 0);
		}
		else {			
			this.getContextMenu().hide();
		}		
	}
	
}
