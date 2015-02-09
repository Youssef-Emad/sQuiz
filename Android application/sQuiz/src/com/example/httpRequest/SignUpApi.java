package com.example.httpRequest;



import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;


public interface SignUpApi {
	@POST("/{type}/signup")
	public void sendForm(@Path("type") String type, @Body FormContainer form ,Callback<String> callback);
	
}
