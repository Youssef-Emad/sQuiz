package com.example.httpRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

import com.example.Models.Question;
import com.google.gson.JsonObject;


public interface QuestionsApi {
	@Headers({"Accept: application/json",})
	@POST("/api/quizzes/addquestion/{id}")
	public void addQuestions(@Header("X-Instructor-Email") String email,
			@Header("X-Instructor-Token") String token
			,@Body Question[] questions,Callback<JsonObject> callback);
}
