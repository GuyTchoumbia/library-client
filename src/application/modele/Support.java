package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Support extends GenericEntity {	
	
	private ListProperty<Document> documents = new SimpleListProperty<Document>();
	
	public Support() { }
	
	public Support(Integer id, String libelle) {
		super(id, libelle);
	}

	public ListProperty<Document> documentsProperty() { return this.documents; }	
	public List<Document> getDocuments() { return this.documents.get(); }	
	public void setDocuments(List<Document> documents) { this.documents.set(FXCollections.observableArrayList(documents)); }	
	
	public void addDocument(Document document) { this.documents.add(document); }
	public void removeDocument(Document document) { this.documents.remove(document); }
		
}
