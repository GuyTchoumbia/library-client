package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class User extends GenericEntity {
	
	private StringProperty username = new SimpleStringProperty();
	private ObjectProperty<Civil> civil = new SimpleObjectProperty<Civil>();
	private ObjectProperty<Contact> contact = new SimpleObjectProperty<Contact>();
	private ListProperty<UserCote> userCotes = new SimpleListProperty<UserCote>();
	
	public User() {		
	}
	
	public User(String username, Civil civil, Contact contact, List<UserCote> userCotes) {
		this.username.set(username);
		this.civil.set(civil);
		this.contact.set(contact);
		this.userCotes.set(FXCollections.observableArrayList(userCotes));
	}
	
	public StringProperty usernameProperty() { return this.username; }
	public ObjectProperty<Civil> civilProperty() { return this.civil; }
	public ObjectProperty<Contact> contactProperty() { return this.contact; }
	public ListProperty<UserCote> userCotesProperty() { return this.userCotes; }
	
	public String getUsername() { return this.username.get(); }
	public Civil getCivil() { return this.civil.get(); }
	public Contact getContact() { return this.contact.get(); }
	public List<UserCote> getUserCotes() { return this.userCotes.get(); }
	
	public void setUsername(String username) { this.username.set(username); }
	public void setCivil(Civil civil) { this.civil.set(civil); }
	public void setContact(Contact contact) { this.contact.set(contact); }
	public void setUserCotes(List<UserCote> userCotes) { this.userCotes.set(FXCollections.observableArrayList(userCotes)); }

}
