package com.example.squiz;

import java.util.regex.Pattern;

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
import android.widget.Toast;

import com.example.Models.SignupForm;
import com.example.httpRequest.SignUpApi;

public class SignupActivity extends Activity {
	 
	private EditText name;
	private EditText email;
	private EditText password;
	private EditText confirmPassword;
	private Button signUp;
	public static final String ENDPOINT="https://sQuiz.herokuapp.com/api" ; 
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
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
						 SignupForm form=populateForm(nameField, emailField, passField,confirmPassField);
						 submitForm(form);
					} catch (Exception e) {
						Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					
				}
				else
					Toast.makeText(SignupActivity.this, "Error connecting to internet", Toast.LENGTH_LONG).show();
			}

			private SignupForm populateForm(String nameField,
					String emailField, String passField, String confirmPassField) throws Exception {
				SignupForm form =new SignupForm();
				if(!nameField.matches("")){
					if(isAlpha(nameField))       //check if the Name is only letters 
					form.setName(nameField); //populate form
					else 
					throw new Exception("Your name should contain only letters");
				}
				else 
					throw new Exception("Please fill in missing Data");
				if(!emailField.matches("")){
					if(isValidEmail(emailField))  
					form.setEmail(emailField);
					else
					throw new Exception("invalid Email format");
				}
				else
					throw new Exception("Please fill in missing Data");
				if(!passField.matches("")){
					if(passField.equals(confirmPassField)){
						form.setPassword(passField);
						form.setPassword_confirmation(confirmPassField);
					}
					else
					throw new Exception("Please re-enter your password");
				}
				else 
					throw new Exception("Please fill in missing Data");
				
				return form;
			}
		}); 
	}
	private void submitForm(SignupForm form){
		 
		RestAdapter adapter = new RestAdapter.Builder()
							 .setEndpoint(ENDPOINT)
							 .setLogLevel(RestAdapter.LogLevel.FULL)
							 .build();
     		SignUpApi signUpApi = adapter.create(SignUpApi.class);
		signUpApi.sendStudentForm(form,new Callback<Integer>() {
			
			@Override
			public void success(Integer arg0, Response arg1) {

				Toast.makeText(SignupActivity.this, "Signup complete", Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(SignupActivity.this, "failed", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	//checks if a string contains only letters
	private boolean isAlpha(String string){
		return Pattern.matches("[a-zA-Z]+", string);
	}
	//checks if a string is an email
	private boolean isValidEmail(String string){
		return Pattern.matches(EMAIL_PATTERN, string);
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
