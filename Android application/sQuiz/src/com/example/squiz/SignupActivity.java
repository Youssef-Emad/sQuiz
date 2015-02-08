package com.example.squiz;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.Models.SignupForm;
import com.example.httpRequest.SignUpApi;
import com.example.httpRequest.StudentFormContainer;

public class SignupActivity extends Activity {
	private RadioGroup accType;
	private EditText name;
	private EditText email;
	private EditText password;
	private EditText confirmPassword;
	private Button signUp;
	public static final String ENDPOINT="https://sQuiz.herokuapp.com/api" ; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signup);
		
		 name=(EditText) findViewById(R.id.editTextName);
		 email=(EditText) findViewById(R.id.editTextEmail);
		 password=(EditText) findViewById(R.id.editTextPassword);
		 confirmPassword=(EditText) findViewById(R.id.editTextConfirmPassword);
		 signUp=(Button) findViewById(R.id.submit);
		
		
	  signUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isOnline()){	
					String nameField=name.getText().toString();
					String emailField=email.getText().toString();
					String passField =password.getText().toString();
					String confirmPassField=confirmPassword.getText().toString();
				
					
					try {
						 SignupForm form=new SignupForm();
						 form.populateForm(nameField, emailField, passField,confirmPassField);
						 StudentFormContainer container= new StudentFormContainer();
						 container.setForm(form);
						 submitForm(container);
					} catch (Exception e) {
						Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					
				}
				else
					Toast.makeText(SignupActivity.this, "Error connecting to internet", Toast.LENGTH_LONG).show();
			}

			
		}); 
	}
	private void submitForm(StudentFormContainer form){

		RestAdapter adapter = new RestAdapter.Builder()
							 .setEndpoint(ENDPOINT)
							 .setLogLevel(RestAdapter.LogLevel.FULL)
							 .build();
     		SignUpApi signUpApi = adapter.create(SignUpApi.class);
     		//check if student or instructor
     		 accType=(RadioGroup)findViewById(R.id.accType);
   		 int radioButton = accType.getCheckedRadioButtonId();
   		 
   		 if(radioButton==R.id.student){
   			 
   			 signUpApi.sendStudentForm(form,new Callback<String>() {
			
			@Override
			public void success(String arg0, Response arg1) {
				Toast.makeText(SignupActivity.this, "Signup complete", Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(SignupActivity.this, "failed", Toast.LENGTH_SHORT).show();		
			}
		});
   		 } 		 
   		 else if(radioButton==R.id.instructor){
   			 signUpApi.sendInstructorForm(form, new Callback<String>() {

				@Override
				public void failure(RetrofitError arg0) {
					Toast.makeText(SignupActivity.this, "failed", Toast.LENGTH_SHORT).show();
					
				}

				@Override
				public void success(String arg0, Response arg1) {
					Toast.makeText(SignupActivity.this, "Signup complete", Toast.LENGTH_SHORT).show();
				}
			});
   		 }
   		 else
   			 Toast.makeText(SignupActivity.this, "Please choose account type",Toast.LENGTH_SHORT).show();
	}	
	private boolean isOnline() {
		ConnectivityManager cm= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netActivity=cm.getActiveNetworkInfo();
		if(netActivity !=null && netActivity.isConnectedOrConnecting())
			return true;
			else
				return false;

	
		
	} 

}
