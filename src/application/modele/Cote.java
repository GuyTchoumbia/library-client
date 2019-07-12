package application.modele;

import application.common.GenericEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Cote extends GenericEntity {
	
	private final ObjectProperty<Document> document = new SimpleObjectProperty<Document>();
	private final ObjectProperty<Library> library = new SimpleObjectProperty<Library>();
	
	public Cote() {		
	}
	
	public Cote(Integer id, String libelle, Document document, Library library) {
		super(id, libelle);
		this.document.set(document);
		this.library.set(library);
	}
	
	public ObjectProperty<Document> documentProperty() { return this.document; }
	public ObjectProperty<Library> libraryProperty() { return this.library; }
	
	public Document getDocument() { return this.document.get(); }
	public Library getLibrary() { return this.library.get(); }
	
	public void setDocument(Document document) { this.document.set(document); }
	public void setLibrary(Library library) { this.library.set(library); }
}
