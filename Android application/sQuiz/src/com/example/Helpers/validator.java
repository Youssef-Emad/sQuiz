package com.example.Helpers;

import java.util.regex.Pattern;

import com.example.squiz.SignupActivity;

public final class validator {
	
private void validator(){}

//checks if a string contains only letters
	public static boolean isAlpha(String string){
		return Pattern.matches("[a-zA-Z]+", string);
	}
	//checks if a string is an email
	public static boolean isValidEmail(String string){
		return Pattern.matches(SignupActivity.EMAIL_PATTERN, string);
	}
}
