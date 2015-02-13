package com.example.instructor;

import com.example.Models.Quiz;
import com.example.squiz.R;
import com.example.squiz.R.id;
import com.example.squiz.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuizFormActivity extends Activity {
	private Button next;
	private EditText numberOfMCQ, numberOfRearrangement, quizName,quizsubj,Duration;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_form);
		
		numberOfMCQ = (EditText) findViewById(R.id.editTextNumberOfMcq);
		numberOfRearrangement = (EditText) findViewById(R.id.editTextNumberOfRearrangement);
		quizName = (EditText) findViewById(R.id.editTextQuizName);
		quizsubj=(EditText) findViewById(R.id.editTextSubject);
		Duration=(EditText) findViewById(R.id.editTextDuration);
		
		next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String nMCQ =numberOfMCQ.getText().toString();
				String nRe = numberOfRearrangement.getText().toString();
				String QName = quizName.getText().toString();
				String Qsubject=quizsubj.getText().toString();
				String duration = Duration.getText().toString();
				Quiz quiz=new Quiz();
				try {
					quiz.setQuizInfo(nMCQ, nRe, QName, Qsubject,duration);
					Intent intent = new Intent(QuizFormActivity.this, QuizDetailsActivity.class);
					intent.putExtra("nQuestions", nMCQ + nRe);
					intent.putExtra("quizName", QName);
					startActivity(intent);
				} catch (Exception e) {
					
					Toast.makeText(QuizFormActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

}
