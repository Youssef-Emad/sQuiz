package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

import com.example.Models.Quiz;
import com.google.gson.JsonObject;


public interface QuizApi {
	@Headers({"Accept: application/json",})
	@GET("/{type}/quizzes")
	public void requestForm(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token
			,@Path("type") String typ,Callback<List<Quiz>> callback);	

	@Headers({"Accept: application/json",})
	@POST("/groups/delete")
	public void deleteQuizzes(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token
			,@Body  List<Quiz> quiz,Callback<JsonObject> callback);

	@Headers({"Accept: application/json",})
	@GET("/groups/{id}/quizzes")
	public void requestQuizzes(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token
			,@Path("id") int groupID,Callback<List<Quiz>> callback);
	
}
