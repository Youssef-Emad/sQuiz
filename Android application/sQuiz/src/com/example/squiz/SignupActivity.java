package com.example.squiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

public class SignupActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_signup);
	}

}
