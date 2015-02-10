package com.example.squiz;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.Models.SignupForm;
import com.example.httpRequest.FormContainer;
import com.example.httpRequest.InstructorFormContainer;
import com.example.httpRequest.SignUpApi;
import com.example.httpRequest.StudentFormContainer;
import com.google.gson.JsonObject;

public class SignupActivity extends Activity {
	private RadioGroup accType;
	private EditText name;
	private EditText email;
	private EditText password;
	private EditText confirmPassword;
	private Button signUp;
	public static final String ENDPOINT="https://sQuiz.herokuapp.com/api" ; 
	private String authToken=new String();
	private ProgressBar pb;
	private SharedPreferences settings;
	
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
		 accType=(RadioGroup)findViewById(R.id.accType);
		 pb=(ProgressBar) findViewById(R.id.progressBar1);
		 pb.setVisibility(View.INVISIBLE);
		 settings= PreferenceManager.getDefaultSharedPreferences(SignupActivity.this);
		 	
	   		
		
	  signUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isOnline()){	
					String nameField=name.getText().toString();
					String emailField=email.getText().toString();
					String passField =password.getText().toString();
					String confirmPassField=confirmPassword.getText().toString();
					int radioButton = accType.getCheckedRadioButtonId();
			   		 
					
					try {
						 SignupForm form=new SignupForm();
						 form.populateForm(nameField, emailField, passField,confirmPassField,radioButton);
						if(form.getAccType()==R.id.student){
						 StudentFormContainer container= new StudentFormContainer();
						 container.setForm(form);
						 pb.setVisibility(View.VISIBLE);
						 submitForm(container);
						 }
						 else if (form.getAccType()==R.id.instructor){
							 InstructorFormContainer  container = new InstructorFormContainer();
							 container.setForm(form);
							 pb.setVisibility(View.VISIBLE);
						    submitForm(container);
						 }   
						 
					} catch (Exception e) {
						Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					
				}
				else
					Toast.makeText(SignupActivity.this, "Error connecting to internet", Toast.LENGTH_LONG).show();
			}

			
		}); 
	}
	private void submitForm(FormContainer form){

		RestAdapter adapter = new RestAdapter.Builder()
							 .setEndpoint(ENDPOINT)
							 .setLogLevel(RestAdapter.LogLevel.FULL)
							 .build();
     		SignUpApi signUpApi = adapter.create(SignUpApi.class);
     		//check if student or instructor
     		 
   		 if(form instanceof StudentFormContainer){
   			 signUpApi.sendForm("students",form,new Callback<JsonObject>() {
			
			@Override
			public void success(JsonObject arg0, Response arg1) {
				 pb.setVisibility(View.INVISIBLE);
				if(arg0.get("success").toString().equals("true")){
				 authToken=arg0.get("auth_token").toString();
				 SharedPreferences.Editor editor = settings.edit();		
				 editor.putString("authToken", authToken);
				 editor.commit();
				}
				
				Toast.makeText(SignupActivity.this, "signup Complete", Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				try {
					 pb.setVisibility(View.INVISIBLE);
					JsonObject obj=(JsonObject) arg0.getBody();
					String text=obj.get("info").toString() + "-";
							text=text.replace(':', ' ').replaceAll("[^a-zA-Z0-9_ ,]", "").replace(',', '\n');
					Toast.makeText(SignupActivity.this,text, Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					
					Toast.makeText(SignupActivity.this,"Internal Server Error", Toast.LENGTH_LONG).show();
				}	
			}
		});
   		 } 		 
   		 else if(form instanceof InstructorFormContainer){
   			 signUpApi.sendForm("instructors",form, new Callback<JsonObject>() {

				@Override
				public void failure(RetrofitError arg0) {
					try {
						pb.setVisibility(View.INVISIBLE);
						JsonObject obj=(JsonObject) arg0.getBody();
						String text=obj.get("info").toString() + "-";
								text=text.replace(':', ' ').replaceAll("[^a-zA-Z0-9_ ,]", "").replace(',', '\n');
						Toast.makeText(SignupActivity.this,text, Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						
						Toast.makeText(SignupActivity.this,"Internal Server Error", Toast.LENGTH_LONG).show();
					}	
					
				}

				@Override
				public void success(JsonObject arg0, Response arg1) {
					 pb.setVisibility(View.INVISIBLE);
					Toast.makeText(SignupActivity.this, "Signup complete", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(SignupActivity.this, AfterLoginInstructorActivity.class));
		        	
				}
			});
   		 }
   		 
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
