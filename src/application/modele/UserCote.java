package application.modele;

import java.util.Date;

import application.common.GenericEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class UserCote extends GenericEntity {
	
	private ObjectProperty<User> user = new SimpleObjectProperty<User>();
	private ObjectProperty<Cote> cote = new SimpleObjectProperty<Cote>();
	private ObjectProperty<Date> dateEmprunt = new SimpleObjectProperty<Date>();
	private ObjectProperty<Date> dateRetour = new SimpleObjectProperty<Date>();
	
	
	public UserCote(User user, Cote cote, Date dateEmprunt, Date dateRetour) {
		this.user.set(user);
		this.cote.set(cote);
		this.dateEmprunt.set(dateEmprunt);
		this.dateRetour.set(dateRetour);		
	}
	
	
	public ObjectProperty<User> userProperty() { return this.user; }	
	public User getUser() { return this.user.get();	}	
	public void setUser(final User user) { this.user.set(user);	}
	
	public ObjectProperty<Cote> cote() { return this.cote; }	
	public Cote getCote() { return this.cote().get();	}	
	public void setCote(Cote cote) { this.cote.set(cote);	}
	
	public ObjectProperty<Date> dateEmprunt() { return this.dateEmprunt; }	
	public Date getDateEmprunt() { return this.dateEmprunt.get(); }	
	public void setDateEmprunt(Date dateEmprunt) { this.dateEmprunt.set(dateEmprunt);	}	
	
	public ObjectProperty<Date> dateRetour() { return this.dateRetour; }	
	public Date getDateRetour() { return this.dateRetour.get();	}	
	public void setDateRetour(Date dateRetour) { this.dateRetour.set(dateRetour); }	
	
}
