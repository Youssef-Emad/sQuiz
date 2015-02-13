package com.example.httpRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

import com.example.Models.Question;
import com.google.gson.JsonObject;


public interface QuestionsApi {
	@Headers({"Accept: application/json",})
	@POST("/quizzes/addquestion/{id}")
	public void addQuestions(@Header("X-Instructor-Email") String email,
			@Header("X-Instructor-Token") String token, @Path("id") int id
			,@Body Question[] questions,Callback<JsonObject> callback);
}
