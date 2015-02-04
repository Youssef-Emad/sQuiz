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

public class SignupActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signup);
		
	
		
		
		
		
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
