package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

import com.example.Models.Group;
import com.example.Models.Quiz;
import com.example.Models.Student;
import com.google.gson.JsonObject;

public interface StudentApi {
	
	@Headers({"Accept: application/json"})
	@GET("/instructor/groups/{id}")
	public void requestStudents(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token ,@Path("id") int id,Callback<List<Student>> callback);


	@POST("/groups/delete")
	public void deleteGroups(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token,@Body List<Group> groups,Callback<JsonObject> callback);

}
