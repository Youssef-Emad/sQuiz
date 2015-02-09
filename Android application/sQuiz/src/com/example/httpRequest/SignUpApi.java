package com.example.httpRequest;



import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;


public interface SignUpApi {
	@POST("/students/signup")
	public void sendStudentForm(@Body FormContainer form ,Callback<String> callback);
	
	@POST("/instructors/signup")
	public void sendInstructorForm(@Body FormContainer form,Callback<String> callback);	
}
