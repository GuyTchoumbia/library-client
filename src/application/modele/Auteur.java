package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Auteur extends GenericEntity {
	
	private ListProperty<Document> documents = new SimpleListProperty<Document>();
	
	public Auteur() {}
	
	public Auteur(Integer id, String libelle) {
		super(id, libelle);		
	}
	
	
	public final ListProperty<Document> documentsProperty() { return this.documents; }	
	public List<Document> getDocuments() { return this.documents.get(); }		
	public void setDocuments(List<Document> documents) { this.documents.set(FXCollections.observableList(documents)); }
	
	public void addDocuments(Document document) { this.documents.add(document); }
	public void removeDocuments(Document document) { this.documents.remove(document); }
	
	@Override
	public String toString() {
		return this.getLibelle();
	}

}
