package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class User extends GenericEntity {
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private ObjectProperty<Civil> civil = new SimpleObjectProperty<Civil>();
	private ObjectProperty<Contact> contact = new SimpleObjectProperty<Contact>();
	private ListProperty<UserCote> userCotes = new SimpleListProperty<UserCote>();
	
	public User() {		
	}
	
	public User(Integer id, Civil civil, Contact contact, List<UserCote> userCotes) {
		this.id.set(id);
		this.civil.set(civil);
		this.contact.set(contact);
		this.userCotes.set(FXCollections.observableArrayList(userCotes));
	}
	
	public IntegerProperty idProperty() { return this.id; }
	public ObjectProperty<Civil> civilProperty() { return this.civil; }
	public ObjectProperty<Contact> contactProperty() { return this.contact; }
	public ListProperty<UserCote> userCotesProperty() { return this.userCotes; }
	
	public Integer getId() { return this.id.get(); }
	public Civil getCivil() { return this.civil.get(); }
	public Contact getContact() { return this.contact.get(); }
	public List<UserCote> getUserCotes() { return this.userCotes.get(); }
	
	public void setId(Integer id) { this.id.set(id); }
	public void setCivil(Civil civil) { this.civil.set(civil); }
	public void setContact(Contact contact) { this.contact.set(contact); }
	public void setUserCotes(List<UserCote> userCotes) { this.userCotes.set(FXCollections.observableArrayList(userCotes)); }


}
