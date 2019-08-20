package application;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import application.modele.Adress;
import application.modele.Auteur;
import application.modele.Civil;
import application.modele.Contact;
import application.modele.Cote;
import application.modele.Document;
import application.modele.Editeur;
import application.modele.Support;
import application.modele.Tag;
import application.modele.User;
import application.modele.UserCote;

@Configuration
@ComponentScan("application")
public class AppConfig {
		
	@Bean
	public User defaultUser() {
		User defaultUser = new User();
		defaultUser.setCivil(new Civil("", "", null));		
		defaultUser.setContact(new Contact());
		defaultUser.getContact().setAdress(new Adress());
		defaultUser.setUserCotes(new ArrayList<UserCote>());	
		return defaultUser;
	}
	
	@Bean
	public Document defaultDocument() {
		Document defaultDocument = new Document();
		defaultDocument.setLibelle("");
		defaultDocument.setIsbn("");
		defaultDocument.setSupport(new Support());
		defaultDocument.setEditeur(new Editeur());
		defaultDocument.setAuteurs(new ArrayList<Auteur>());
		defaultDocument.setTags(new ArrayList<Tag>());
		defaultDocument.setCotes(new ArrayList<Cote>());
		return defaultDocument;
	}

}
