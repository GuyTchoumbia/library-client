package application.modele;

import java.sql.Date;

import org.springframework.lang.Nullable;

import application.common.GenericEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class UserCote extends GenericEntity {
	
	private ObjectProperty<User> user = new SimpleObjectProperty<User>();
	private ObjectProperty<Cote> cote = new SimpleObjectProperty<Cote>();
	private ObjectProperty<Date> dateEmprunt = new SimpleObjectProperty<Date>();	
	private ObjectProperty<Date> dateReservation = new SimpleObjectProperty<Date>();
	private ObjectProperty<Date> dateRetour = new SimpleObjectProperty<Date>();
	
	public UserCote() {
		
	}
	
	public UserCote(User user, Cote cote, Date dateEmprunt, Date dateReservation, Date dateRetour) {
		this.user.set(user);
		this.cote.set(cote);
		this.dateEmprunt.set(dateEmprunt);
		this.dateReservation.set(dateReservation);
		this.dateRetour.set(dateRetour);
	}	
	
	public ObjectProperty<User> userProperty() { return this.user; }	
	public User getUser() { return this.user.get();	}	
	public void setUser(User user) { this.user.set(user);	}
	
	public ObjectProperty<Cote> coteProperty() { return this.cote; }	
	public Cote getCote() { return this.cote.get();	}	
	public void setCote(Cote cote) { this.cote.set(cote);	}
	
	public ObjectProperty<Date> dateEmpruntProperty() { return this.dateEmprunt; }		
	@Nullable
	public Date getDateEmprunt() { return this.dateEmprunt.get() != null ? this.dateEmprunt.get() : null; }	
	public void setDateEmprunt(@Nullable Date dateEmprunt) { this.dateEmprunt.set(dateEmprunt); }	
	
	public ObjectProperty<Date> dateReservationProperty() { return this.dateReservation; }	
	@Nullable
	public Date getDateReservation() { return this.dateReservation.get() != null ? this.dateReservation.get() : null; }	
	public void setDateReservation(@Nullable Date dateReservation) { this.dateReservation.set(dateReservation); }	
	
	public ObjectProperty<Date> dateRetourProperty() { return this.dateRetour; }	
	@Nullable
	public Date getDateRetour() { return this.dateRetour.get() != null ? this.dateRetour.get() : null; }	
	public void setDateRetour(@Nullable Date dateRetour) { this.dateRetour.set(dateRetour); }	
	
}
