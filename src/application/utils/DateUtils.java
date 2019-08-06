package application.utils;

import java.sql.Date;
import java.time.LocalDate;

public class DateUtils {

	public static Date asDate(LocalDate localDate) {
		if (localDate != null) {
			return Date.valueOf(localDate);
		}
    	else return null;
    }   

    public static LocalDate asLocalDate(Date date) {
    	if (date != null) {
    		return date.toLocalDate();
    	}
    	else return null;
    }    
    
}


