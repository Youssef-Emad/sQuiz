package com.example.httpRequest;



import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.example.Models.SignupForm;


public interface SignUpApi {
	@POST("/students/registrations")
	public void sendStudentForm(@Body SignupForm form ,Callback<Integer> callback);
	
//	@POST("/instructors/registrations")
	//public void sendInstructorForm(@Body SignupForm form);	
}
