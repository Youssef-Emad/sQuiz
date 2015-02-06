package com.example.squiz;

import com.example.Models.LoginForm;
import com.example.httpRequest.LoginAPI;

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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	
	private Button logIn;
	private EditText Email;
	private EditText Password;
	private LoginForm user;
	
	public static final String ENDPOINT = 
			"http://services.hanselandpetal.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_welcome);
		
		

		logIn.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {

				if(isOnline()){

					logIn = (Button) findViewById(R.id.login);
					Email = (EditText) getText(R.id.email);
					Password = (EditText) getText(R.id.password);
					user.setEmail(Email.toString());
					user.setPassword(Password.toString());
					sendData(user);
				}
				else{
					Toast.makeText(WelcomeActivity.this, "the network is not available", Toast.LENGTH_SHORT).show();
				}
			}		

		});


	}
	protected boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}

	
	private void sendData(LoginForm user){

		RestAdapter restAdapter = new RestAdapter.Builder()
	    .setEndpoint(ENDPOINT)  //call base url
	    .build();


	LoginAPI task = restAdapter.create(LoginAPI.class); //retrofit createapi
	task.login(user ,new Callback<Integer>() {
	        @Override
	        public void success(Integer arg0, Response arg1) {
	        	
	        	Toast.makeText(WelcomeActivity.this, "welcome", Toast.LENGTH_SHORT).show();

	        }

	        @Override
	        public void failure(RetrofitError retrofitError) {
	            retrofitError.printStackTrace();
	            Toast.makeText(WelcomeActivity.this, "failed", Toast.LENGTH_SHORT).show();
	        }
	    });
	}
		
		
}