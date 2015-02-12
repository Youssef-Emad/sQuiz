package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;

import com.example.Models.Quiz;


public interface QuizApi {
	@Headers({"Accept: application/json",})
	@GET("/{type}/quizzes")
	public void requestForm(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token,@Path("type") String typ,Callback<List<Quiz>> callback);	

}
