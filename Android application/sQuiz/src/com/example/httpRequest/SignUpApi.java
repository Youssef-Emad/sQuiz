package com.example.httpRequest;



import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

import com.google.gson.JsonObject;


public interface SignUpApi {
	@Headers({"Accept: application/json",})
	@POST("/{type}/signup")
	public void sendForm(@Path("type") String type, @Body FormContainer form ,Callback<JsonObject> callback);	
}
