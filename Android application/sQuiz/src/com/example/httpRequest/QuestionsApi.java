package com.example.httpRequest;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

import com.example.Models.PublishInfo;
import com.example.Models.Question;
import com.google.gson.JsonObject;


public interface QuestionsApi {
	@Headers({"Accept: application/json",})
	@GET("/{type}/quizzes/{id}")
	public void instructorGetQuestions(@Header("X-Instructor-Email") String email,
			@Header("X-Instructor-Token") String token, @Path("id") int id, 
			@Path("type") String type
			,Callback<Question[]> callback);
	
	@Headers({"Accept: application/json",})
	@GET("/{type}/quizzes/{id}")
	public void studentGetQuestions(@Header("X-Student-Email") String email,
			@Header("X-Student-Token") String token, @Path("id") int id, 
			@Path("type") String type
			,Callback<Question[]> callback);
	
	@Headers({"Accept: application/json",})
	@POST("/quizzes/addquestion/{id}")
	public void addQuestions(@Header("X-Instructor-Email") String email,
			@Header("X-Instructor-Token") String token, @Path("id") int id
			,@Body Question[] questions,Callback<JsonObject> callback);
	
	@Headers({"Accept: application/json"})
	@POST("/quizzes/publish/{id}")
	public void publishQuiz(@Header("X-Instructor-Email") String email,
			@Header("X-Instructor-Token") String token, @Body PublishInfo pi,
			@Path("id") int id, Callback<JsonObject> callback);
	
	@Headers({"Accept: application/json",})
	@POST("/student/markquiz/{id}")
	public void mark(@Header("X-Student-Email") String email,
			@Header("X-Student-Token") String token, @Body JsonObject answers,
			@Path("id") int id, Callback<JsonObject> callback);
}
