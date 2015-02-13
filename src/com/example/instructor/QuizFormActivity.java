package com.example.instructor;

import com.example.squiz.R;
import com.example.squiz.R.id;
import com.example.squiz.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuizFormActivity extends Activity {
	private Button next;
	private EditText numberOfMCQ, numberOfRearrangement, quizName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_form);
		
		numberOfMCQ = (EditText) findViewById(R.id.editTextNumberOfMcq);
		numberOfRearrangement = (EditText) findViewById(R.id.editTextNumberOfRearrangement);
		quizName = (EditText) findViewById(R.id.editTextQuizName);
		
		next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int nMCQ = Integer.parseInt(numberOfMCQ.getText().toString());
				int nRe = Integer.parseInt(numberOfRearrangement.getText().toString());
				String QName = quizName.getText().toString();
				Intent intent = new Intent(QuizFormActivity.this, QuizDetailsActivity.class);
				intent.putExtra("nQuestions", nMCQ + nRe);
				intent.putExtra("quizName", QName);
				startActivity(intent);
				
			}
		});
	}

}
