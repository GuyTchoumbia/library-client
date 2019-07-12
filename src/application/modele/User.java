package application.modele;

import application.common.GenericEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User extends GenericEntity {
	
	private StringProperty nom = new SimpleStringProperty();
	private StringProperty  prenom = new SimpleStringProperty();
	private ObjectProperty<Adresse> adresse = new SimpleObjectProperty<Adresse>();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty telephone = new SimpleStringProperty();
	
	public User() {
		this.nom.set("");
		this.prenom.set("");
		this.adresse.set(new Adresse());
		this.telephone.set("");
		this.email.set("");
	}
	
	public User(String nom, String prenom, Adresse adresse, String email, String telephone) {
		this.nom.set(nom);
		this.prenom.set(prenom);
		this.adresse.set(adresse);
		this.email.set(email);
		this.telephone.set(telephone);
	}
	
	public StringProperty nomProperty() { return this.nom; }
	public StringProperty prenomProperty() { return this.prenom; }
	public ObjectProperty<Adresse> adresseProperty() { return this.adresse; }
	public StringProperty emailProperty() { return this.email; }
	public StringProperty telephoneProperty() { return this.telephone; }
	
	public String getNom() { return this.nom.get(); }
	public String getPrenom() { return this.prenom.get(); }
	public Adresse getAdresse() { return this.adresse.get(); }
	public String getEmail() { return this.email.get(); }
	public String getTelephone() { return this.telephone.get(); }
	
	public void setNom(String nom) { this.nom.set(nom); }
	public void setPrenom(String prenom) { this.prenom.set(prenom); }
	public void setAdresse(Adresse adresse) { this.adresse.set(adresse); }
	public void setEmail(String email) { this.email.set(email); }
	public void setTelephone(String telephone) { this.telephone.set(telephone); }


}
