package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Path;

import com.example.Models.Quiz;


public interface QuizApi {
	
	@GET("/{type}/quizes")
	public void sendForm(@Path("type") String type, @Body FormContainer form ,Callback<List<Quiz>> callback);	

}
