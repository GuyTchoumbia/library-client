package application.modele;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Civil {
	
	private StringProperty nom = new SimpleStringProperty();
	private StringProperty prenom = new SimpleStringProperty();
	private ObjectProperty<Date> dateNaissance = new SimpleObjectProperty<>();
	
	public Civil() {
		
	}
	
	public Civil(String nom, String prenom, Date dateNaissance) {
		this.nom.set(nom);
		this.prenom.set(prenom);
		this.dateNaissance.set(dateNaissance);
	}
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	
	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	
	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	
	public final ObjectProperty<Date> dateNaissanceProperty() {
		return this.dateNaissance;
	}
	
	public final Date getDateNaissance() {
		return this.dateNaissanceProperty().get();
	}
	
	public final void setDateNaissance(final Date date) {
		this.dateNaissanceProperty().set(date);
	}
	
	
	

}
