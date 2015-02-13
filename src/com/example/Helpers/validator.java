package com.example.Helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class validator {
	
private void validator(){}

public static final String EMAIL_PATTERN = 
"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

//checks if a string contains only letters
	public static boolean isAlpha(String string){
		String ignoreSpace=string.replaceAll(" ","");
		Pattern p = Pattern.compile("[a-zA-Z]+");
		 Matcher m = p.matcher(ignoreSpace);
		 return m.matches();
	}
	//checks if a string is an email
	public static boolean isValidEmail(String string){
		return Pattern.matches(EMAIL_PATTERN, string);
	}
}
