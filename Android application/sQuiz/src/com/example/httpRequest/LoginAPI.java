
package com.example.httpRequest;


import com.example.Models.LoginForm;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface LoginAPI {
	
	@POST("/login")
		public void login( @Body LoginForm user ,Callback<String> callback);
}