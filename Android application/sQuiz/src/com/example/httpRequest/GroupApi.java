package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

import com.example.Models.Group;

public interface GroupApi {
	@GET("/{type}/groups")
	public void requestGroups(@Header("X-Instrutor-Email") String email,@Header("X-Instructor-Token") String x,@Path("type") String type,Callback<List<Group>> callback);	


}
