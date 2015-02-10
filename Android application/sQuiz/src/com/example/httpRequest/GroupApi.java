package com.example.httpRequest;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

import com.example.Models.Group;

public interface GroupApi {
	@GET("/{type}/groups")
	public void sendForm(@Path("type") String type,Callback<List<Group>> callback);	


}
