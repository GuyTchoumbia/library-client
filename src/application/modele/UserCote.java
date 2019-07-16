package application.modele;

import java.time.LocalDate;
import java.util.Date;

import application.common.GenericEntity;
import application.utils.DateUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class UserCote extends GenericEntity {
	
	private ObjectProperty<User> user = new SimpleObjectProperty<User>();
	private ObjectProperty<Cote> cote = new SimpleObjectProperty<Cote>();
	private ObjectProperty<LocalDate> dateEmprunt = new SimpleObjectProperty<LocalDate>();	
	
	public UserCote() {
		
	}
	
	public UserCote(User user, Cote cote, LocalDate dateEmprunt) {
		this.user.set(user);
		this.cote.set(cote);
		this.dateEmprunt.set(dateEmprunt);		
	}	
	
	public ObjectProperty<User> userProperty() { return this.user; }	
	public User getUser() { return this.user.get();	}	
	public void setUser(User user) { this.user.set(user);	}
	
	public ObjectProperty<Cote> coteProperty() { return this.cote; }	
	public Cote getCote() { return this.cote.get();	}	
	public void setCote(Cote cote) { this.cote.set(cote);	}
	
	public ObjectProperty<LocalDate> dateEmpruntProperty() { return this.dateEmprunt; }	
	public LocalDate getDateEmprunt() { return this.dateEmprunt.get(); }	
	public void setDateEmprunt(Date dateEmprunt) { this.dateEmprunt.set(DateUtils.asLocalDate(dateEmprunt)); }	
	
	
}
