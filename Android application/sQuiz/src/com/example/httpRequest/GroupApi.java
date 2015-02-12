package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;

import com.example.Models.Group;

public interface GroupApi {
	@Headers({"Accept: application/json",})
	@GET("/{type}/groups")
	public void requestGroups(@Header("X-Instructor-Email") String email,@Header("X-Instructor-Token") String token ,@Path("type") String type,Callback<List<Group>> callback);	

} 
