package application.common;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GenericEntity implements Entity{
	
	protected IntegerProperty id = new SimpleIntegerProperty();
	protected StringProperty libelle = new SimpleStringProperty();
	
	public GenericEntity() {}
	
	public GenericEntity(Integer id, String libelle) {
		this.id.set(id);
		this.libelle.set(libelle);
	}

	public IntegerProperty idProperty() { return this.id; }
	public StringProperty libelleProperty() { return this.libelle; }

	@Override
	public Integer getId() { return this.id.get(); }
	
	@Override
	public void setId(Integer id) { this.id.set(id); }
	
	@Override
	public String getLibelle() { return this.libelle.get(); }
	
	@Override
	public void setLibelle(String libelle) { this.libelle.set(libelle); }	
	
	@Override
	public String toString() {
		return getLibelle();
	}	
	
}
