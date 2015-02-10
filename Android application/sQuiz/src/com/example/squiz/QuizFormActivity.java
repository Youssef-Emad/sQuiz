package com.example.squiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizFormActivity extends Activity {
	private Button next;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_form);
		
		next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(QuizFormActivity.this, QuizDetailsActivity.class));
			}
		});
	}

}
