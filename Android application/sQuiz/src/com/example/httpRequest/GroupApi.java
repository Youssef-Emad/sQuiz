package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

import com.example.Models.Group;
import com.google.gson.JsonObject;

public interface GroupApi {
	@Headers({"Accept: application/json",})
	@GET("/{type}/groups")
	public void requestGroups(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token ,@Path("type") String type,Callback<List<Group>> callback);	
	
	@Headers({"Accept: application/json",})
	@POST("/groups/create")
	public void addGroup(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token,@Body  Group group,Callback<Group> callback);
	
	@Headers({"Accept: application/json",})
	@DELETE("/groups/delete")
	public void deleteGroups(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token,@Body  List<Group> group,Callback<JsonObject> callback);
} 
