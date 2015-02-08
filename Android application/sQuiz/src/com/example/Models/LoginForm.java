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
					this.email = email; // VigenereCipher(email, "squiz");
				else
					throw new Exception("invalid Email format") ;
			}
			else{
				throw new Exception("Please fill in missing Data");
			}

		if(!password.matches(""))	
			this.password = password; // VigenereCipher(password, "squiz");
		else
			throw new Exception("Please enter password");

		}
		
		/*private String VigenereCipher(String text, final String key) {
		        String res = "";
		        text = text.toUpperCase();
		        for (int i = 0, j = 0; i < text.length(); i++) {
		            char c = text.charAt(i);
		            if (c < 'A' || c > 'Z') continue;
		            res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
		            j = ++j % key.length();
		        }
		        return res.toLowerCase();
		    }*/
}