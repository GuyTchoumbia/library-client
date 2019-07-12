package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Tag extends GenericEntity {
	
	private ListProperty<Document> documents = new SimpleListProperty<Document>();

	public Tag() {}
	
	public Tag(int id, String libelle) {
		super(id, libelle);		
	}
	
	public final ListProperty<Document> documentsProperty() { return this.documents; }	
	public List<Document> getDocuments() { return this.documents.get(); }	
	public void setDocuments(List<Document> documents) { this.documents.set(FXCollections.observableArrayList(documents)); }
	
	public void addDocument(Document document) { this.documents.add(document); }
	public void removeDocument(Document document) { this.documents.remove(document); }	
	
}
