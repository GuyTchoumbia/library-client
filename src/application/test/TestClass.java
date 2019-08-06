package application.test;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestClass {
		private StringProperty nom = new SimpleStringProperty();
		private ListProperty<String> bonbons = new SimpleListProperty<String>();
		
		public TestClass() {
			this.nom = new SimpleStringProperty("");
			this.bonbons = new SimpleListProperty<String>(FXCollections.emptyObservableList());
		}
		
		public TestClass(String nom, List<String> bonbons) {
			this.nom.set(nom);
			this.bonbons.set(FXCollections.observableArrayList(bonbons));
		}
		
		public StringProperty nomProperty() { return this.nom; }
		public ObservableList<String> bonbonsProperty() { return this.bonbons; }
		
		public String getNom() { return this.nom.get(); }
		public List<String> getBonbons() { return this.bonbons.get(); }
		
		public void setNom(String nom)  { this.nom.set(nom); }
		public void setBonbons(List<String> bonbons) { this.bonbons.set(FXCollections.observableArrayList(bonbons)); }	
	}