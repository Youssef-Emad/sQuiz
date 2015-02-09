package com.example.httpRequest;

import com.example.Models.SignupForm;

public class StudentFormContainer extends FormContainer {
	private SignupForm student;
	@Override 
	public void setForm(SignupForm form){
		student=form;
	}
}
	
