package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

public class Library extends GenericEntity {
	
	private ListProperty<Cote> cotes = new SimpleListProperty<Cote>();
	private ObjectProperty<Adress> adress = new SimpleObjectProperty<Adress>();
	
	public Library() {}
	
	public Library(Integer id, String libelle, Adress adress) {
		super(id, libelle);
		this.adress.set(adress);
	}
	
	public ListProperty<Cote> cotesProperty() { return this.cotes; }
	public List<Cote> getCotes() { return this.cotes.get(); }
	public void setCotes(List<Cote> cotes) { this.cotes.set(FXCollections.observableArrayList(cotes)); }
	
	public void addCote(Cote cote) { this.cotes.add(cote); }
	public void removeCote(Cote cote) { this.cotes.remove(cote); }
	
	public ObjectProperty<Adress> adressProperty() { return this.adress; }
	public Adress getAdress() { return this.adress.get(); }
	public void setAdress(Adress adress) { this.adress.set(adress); }
	
	@Override
	public String toString() {
		return this.libelle.get();
	}
		
}
