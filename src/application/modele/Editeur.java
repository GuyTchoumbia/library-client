package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Editeur extends GenericEntity {
	
	private ListProperty<Document> documents = new SimpleListProperty<Document>();
	
	public Editeur() {}
	
	public Editeur(Integer id, String libelle) {
		super(id, libelle);
	}	
	
	
	public final ListProperty<Document> documentsProperty() { return this.documents; }	
	public List<Document> getDocument() { return this.documents.get(); }	
	public final void setDocuments(List<Document> documents) { this.documents.set(FXCollections.observableArrayList(documents)); }
	
	public void addDocument(Document documents) { this.documents.add(documents); }
	public void removeDocument(Document documents) { this.documents.remove(documents); }

	@Override
	public String toString() {
		return this.getLibelle();
	}

}
