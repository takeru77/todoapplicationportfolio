package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public boolean isValidDate(String str) {
		try {
			DATE_FORMAT.setLenient(false);
			DATE_FORMAT.parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
