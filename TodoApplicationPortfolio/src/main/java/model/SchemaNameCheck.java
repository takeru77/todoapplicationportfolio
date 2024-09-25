package model;

public class SchemaNameCheck {
	public static boolean startsWithLetter(String str) {
	    return str.matches("^[a-zA-Z].*"); 
	}
}
