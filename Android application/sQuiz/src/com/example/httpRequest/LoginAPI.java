
package com.example.httpRequest;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

import com.example.Models.LoginForm;
import com.google.gson.JsonObject;

public interface LoginAPI {
	@Headers({"Accept: application/json",})
	@POST("/signin")
		public void login( @Body LoginForm user ,Callback<JsonObject> callback);
}