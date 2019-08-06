package application.modele;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
	
	private ObjectProperty<Adress> adress = new SimpleObjectProperty<Adress>();
	private StringProperty phone = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	
	public Contact() {
		
	}
	
	public Contact(Adress adress, String phone, String email) {
		this.adress.set(adress);
		this.phone.set(phone);
		this.email.set(email);
	}
	
	public ObjectProperty<Adress> adressProperty() { return this.adress; }
	public StringProperty phoneProperty() { return this.phone; }
	public StringProperty emailProperty() { return this.email; }
	
	public Adress getAdress() { return this.adress.get(); }
	public String getPhone() { return this.phone.get(); }
	public String getEmail() { return this.email.get(); }
	
	public void setAdress(Adress adress) { this.adress.set(adress); }
	public void setPhone(String phone) { this.phone.set(phone); }
	public void setEmail(String email) { this.email.set(email); }	

}
