package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Document extends GenericEntity {
	
	private StringProperty date = new SimpleStringProperty();
	private StringProperty isbn = new SimpleStringProperty();
	
	private ObjectProperty<Support> support = new SimpleObjectProperty<Support>();
	private ObjectProperty<Editeur> editeur = new SimpleObjectProperty<Editeur>();
	private ListProperty<Auteur> auteurs = new SimpleListProperty<Auteur>();
	private ListProperty<Tag> tags = new SimpleListProperty<Tag>();
	private ListProperty<Library> libraries = new SimpleListProperty<Library>();
	private ListProperty<Cote> cotes = new SimpleListProperty<Cote>();
	
	public Document() {
//		this.libelle.set("");
//		this.date.set("");
//		this.support.set(new Support(0, ""));
//		this.auteurs.set(FXCollections.observableArrayList());
//		this.editeur.set(new Editeur(0, ""));
//		this.tags.set(FXCollections.observableArrayList());
//		this.libraries.set(FXCollections.observableArrayList());
//		this.cotes.set(FXCollections.observableArrayList());
	}
	
	public Document(Integer id, String libelle, String isbn, String date, Editeur editeur, Support support) {
		super(id, libelle);
		this.date.set(date);
		this.isbn.set(isbn);
		this.editeur.set(editeur);
		this.support.set(support);
	}	
	/*
	 * bean accessors
	 */
	public StringProperty dateProperty() { return this.date; }
	public String getDate() { return this.date.get(); }
	public void setDate(String i) { this.date.set(i); }
	
	public StringProperty isbnProperty() { return this.isbn; }
	public String getIsbn() { return this.isbn.get(); }
	public void setIsbn(String isbn) { this.isbn.set(isbn); }
	
	public final ObjectProperty<Support> supportProperty() { return this.support; }
	public Support getSupport() { return this.support.get(); }
	public void setSupport(Support support) { this.support.set(support); }	
	
	public ListProperty<Auteur> auteursProperty() { return this.auteurs; }
	public List<Auteur> getAuteurs() { return this.auteurs.get(); }
	public void setAuteurs(List<Auteur> auteurs) { this.auteurs.set(FXCollections.observableArrayList(auteurs)); }
	public void addAuteur(Auteur auteur) { this.auteurs.add(auteur); }
	public void removeAuteur(Auteur auteur) { this.auteurs.remove(auteur); }	
	
	public ObjectProperty<Editeur> editeurProperty() { return this.editeur; }
	public Editeur getEditeur() { return this.editeur.get(); }
	public void setEditeur(Editeur editeur) { this.editeur.set(editeur); }

	public ListProperty<Tag> tagsProperty() { return this.tags; }
	public List<Tag> getTags() { return this.tags.get(); }
	public void setTags(List<Tag> tags) { this.tags.set(FXCollections.observableArrayList(tags)); }
	public void addTag(Tag tag) { this.tags.add(tag); }
	public void removeTag(Tag tag) { this.tags.remove(tag); }

	public ListProperty<Library> librariesProperty() { return this.libraries; }
	public List<Library> getLibraries() { return this.libraries.get(); }
	public void setLibraries(List<Library> libraries) { this.libraries.set(FXCollections.observableArrayList(libraries)); }
	public void addLibrary(Library library) { this.libraries.add(library); }
	public void removeLibrary(Library library) { this.libraries.remove(library); }

	public ListProperty<Cote> cotesProperty() { return this.cotes; } 	
	public List<Cote> getCotes() { return this.cotes.get(); }
	public void setCotes(List<Cote> cotes) { this.cotes.set(FXCollections.observableArrayList(cotes)); }	
	public void addCote(Cote cote) { this.cotes.add(cote); }	
	public void removeCote(Cote cote) { this.cotes.remove(cote); }
	
}
