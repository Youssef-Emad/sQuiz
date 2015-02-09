package com.example.httpRequest;

import com.example.Models.SignupForm;

public class InstructorFormContainer extends FormContainer {
	private SignupForm instructor;
	@Override 
	public void setForm(SignupForm form){
		instructor=form;
	}
}
	
