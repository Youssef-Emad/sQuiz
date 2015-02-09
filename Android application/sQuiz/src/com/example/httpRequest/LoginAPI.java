
package com.example.httpRequest;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.example.Models.LoginForm;
import com.google.gson.JsonObject;

public interface LoginAPI {
	
	@POST("/signin")
		public void login( @Body LoginForm user ,Callback<JsonObject> callback);
}