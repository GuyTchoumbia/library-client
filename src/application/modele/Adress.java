package application.modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adress {
	
	private StringProperty numero = new SimpleStringProperty();
	private StringProperty rue = new SimpleStringProperty();
	private StringProperty codePostal = new SimpleStringProperty();
	private StringProperty ville = new SimpleStringProperty();
	
	public Adress(String numero, String rue, String codePostal, String ville) {
		this.numero.set(numero);
		this.rue.set(rue);
		this.codePostal.set(codePostal);
		this.ville.set(ville);		
	}
	
	public Adress() {		
	}

	public final StringProperty numeroProperty() { return this.numero; }
	public final StringProperty rueProperty() { return this.rue; }
	public final StringProperty codePostalProperty() { return this.codePostal; }
	public final StringProperty villeProperty() { return this.ville; }
	
	public String getNumero() { return this.numero.get(); }
	public String getRue() { return this.rue.get(); }
	public String getCodePostal() { return this.codePostal.get(); }
	public String getVille() { return this.ville.get(); }
	
	public void setNumero(String numero) { this.numero.set(numero); }
	public void setRue(String rue) { this.rue.set(rue); }
	public void setCodePostal(String codePostal) { this.codePostal.set(codePostal); }
	public void setVille(String ville) { this.ville.set(ville); }
	
	public String toString() {
		return getNumero()+" "+getRue()+", "+getCodePostal()+" "+getVille();
	}
	
	public StringProperty toStringProperty() {
		return new SimpleStringProperty(getNumero()+" "+getRue()+", "+getCodePostal()+" "+getVille());
	}
 
}
