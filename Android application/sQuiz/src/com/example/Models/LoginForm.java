package com.example.Models;

import com.example.Helpers.validator;


public class LoginForm {
		
		private String email;
		private String password;
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		public void populateForm(String email, String password) throws Exception{

			if(!email.matches("")){
				if(validator.isValidEmail(email))  
					this.email = email;
				else
					throw new Exception("invalid Email format") ;
			}
			else{
				throw new Exception("Please fill in missing Data");
			}

		if(!password.matches(""))	
			this.password = password;
		else
			throw new Exception("Please enter password");

		}
}