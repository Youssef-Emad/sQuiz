package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

import com.example.Models.Student;
import com.google.gson.JsonObject;

public interface StudentApi {
	
	
	@Headers({"Accept: application/json"})
	@GET("/instructor/groups/{id}")
	public void requestStudents(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token ,@Path("id") int id,Callback<List<Student>> callback);
	
	
	@Headers({"Accept: application/json"})
	@POST("/groups/{id}/student/add")
	public void addStudent(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token,@Path("id") int id,@Body Student student,Callback<Student> callback);
	
	
	@Headers({"Accept: application/json"})
	@POST("/groups/{id}/student/remove")
	public void deleteStudents(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token,@Body List<Student> students,@Path("id") int id,Callback<JsonObject> callback);

}
