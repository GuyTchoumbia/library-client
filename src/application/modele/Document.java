package application.modele;

import java.util.List;

import application.common.GenericEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Document extends GenericEntity {
	
	private IntegerProperty date = new SimpleIntegerProperty();
	private StringProperty isbn = new SimpleStringProperty();
	
	private ObjectProperty<Support> support = new SimpleObjectProperty<Support>();
	private ObjectProperty<Editeur> editeur = new SimpleObjectProperty<Editeur>();
	private ListProperty<Auteur> auteurs = new SimpleListProperty<Auteur>();
	private ListProperty<Tag> tags = new SimpleListProperty<Tag>();
	private ListProperty<Cote> cotes = new SimpleListProperty<Cote>();
	
	public Document() {
	}
	
	public Document(Integer id, String libelle, String isbn, Integer date, Editeur editeur, Support support, List<Auteur> auteurs, List<Tag> tags, List<Cote> cotes) {
		super(id, libelle); 
		this.date.set(date);
		this.isbn.set(isbn);
		this.editeur.set(editeur);
		this.support.set(support);
		this.auteurs.set(FXCollections.observableArrayList(auteurs));
		this.tags.set(FXCollections.observableArrayList(tags));
		this.cotes.set(FXCollections.observableArrayList(cotes));
	}	
	/*
	 * bean accessors
	 */
	public IntegerProperty dateProperty() { return this.date; }
	public Integer getDate() { return this.date.get(); }
	public void setDate(Integer date) { this.date.set(date); }
	
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

	public ListProperty<Cote> cotesProperty() { return this.cotes; } 	
	public List<Cote> getCotes() { return this.cotes.get(); }
	public void setCotes(List<Cote> cotes) { this.cotes.set(FXCollections.observableArrayList(cotes)); }	
	public void addCote(Cote cote) { this.cotes.add(cote); }	
	public void removeCote(Cote cote) { this.cotes.remove(cote); }
	
}
