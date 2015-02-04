package com.example.squiz;

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

import com.example.httpRequest.SignupForm;

public class SignupActivity extends Activity {
	
	EditText name=(EditText) findViewById(R.id.editTextName);
	EditText email=(EditText) findViewById(R.id.editTextEmail);
	EditText password=(EditText) findViewById(R.id.editTextPassword);
	EditText confirmPassword=(EditText) findViewById(R.id.editTextConfirmPassword);
	Button signUp=(Button)findViewById(R.id.submit);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signup);
		
		signUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isOnline()){
					SignupForm form=new SignupForm();
					//fill form
					form.setName(name.getText().toString());
					form.setEmail(email.getText().toString());
					form.setPassword(password.getText().toString());
					form.setPassword_confirmation(confirmPassword.getText().toString());
					
					
					
				}
					
				
			}
		});
		
		
		
		
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
