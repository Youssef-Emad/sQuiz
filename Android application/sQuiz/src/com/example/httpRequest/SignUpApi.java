package com.example.httpRequest;



import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import com.example.Models.SignupForm;


public interface SignUpApi {
	@POST("/students/signup")
	public void sendStudentForm(@Body StudentFormContainer form ,Callback<String> callback);
	
	@POST("/instructors/signup")
	public void sendInstructorForm(@Body StudentFormContainer form,Callback<String> callback);	
}
