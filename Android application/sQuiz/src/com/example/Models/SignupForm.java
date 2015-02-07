package com.example.Models;

import com.example.Helpers.validator;

public class SignupForm {
	
	
	private String email;
	private String name;
	private String password;
	private String password_confirmation;
	
	public SignupForm(){}
	
	public SignupForm populateForm(String nameField,
			String emailField, String passField, String confirmPassField)throws Exception {
		
		this.setName(nameField);
		this.setEmail(emailField);
		this.setPassword(passField);
		this.setPassword_confirmation(confirmPassField);
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	private void setEmail(String email) throws Exception {
		
		if(!email.matches("")){
			if(validator.isValidEmail(email))  
				this.email = email;
			else
			throw new Exception("invalid Email format");
		}
		else
			throw new Exception("Please fill in missing Data");
		
	}
	public String getName() {
		return name;
	}
	private void setName(String name)throws Exception {
		
		if(!name.matches("")){
			if(validator.isAlpha(name))       //check if the Name is only letters 
			this.name=name; //populate form
			else 
			throw new Exception("Your name should contain only letters");
		}
		else 
			throw new Exception("Please fill in missing Data");
		
	}
	public String getPassword() {
		return password;
	}
	private void setPassword(String password)throws Exception {
		
		if(!password.matches("")){		
			this.password = password;
		}
		else
			throw new Exception("Please enter password");
	}
	public String getPassword_confirmation() {
		return password_confirmation;
	}
	
	private void setPassword_confirmation(String password_confirmation)throws Exception {
		if(!password_confirmation.matches("")){
			if(password_confirmation.equals(this.password)){
				this.password = password_confirmation;
			}
			else
				throw new Exception("password mismatch");
		}
		else 
			throw new Exception("Please confirm your password");
		
	}
}
