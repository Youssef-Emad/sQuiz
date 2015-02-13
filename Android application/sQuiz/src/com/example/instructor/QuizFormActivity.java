package com.example.instructor;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.Quiz;
import com.example.httpRequest.QuizApi;
import com.example.squiz.R;
import com.example.squiz.SignupActivity;
import com.google.gson.JsonObject;

public class QuizFormActivity extends Activity {
	private Button next;
	private EditText numberOfMCQ, numberOfRearrangement, quizName,quizsubj,Duration;
	QuizApi task;
	String email;
	String auth_token_string;
	Intent intent;
	Quiz quiz;
	String nMCQ,nRe,QName,Qsubject,duration;
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
				if(isOnline())
				{	
					nMCQ =numberOfMCQ.getText().toString();
					nRe = numberOfRearrangement.getText().toString();
					QName = quizName.getText().toString();
					Qsubject=quizsubj.getText().toString();
					duration = Duration.getText().toString();
					quiz=new Quiz();
					try {
						quiz.setQuizInfo(nMCQ, nRe, QName, Qsubject,duration);
						RestAdapter restAdapter = new RestAdapter.Builder()
						.setEndpoint(SignupActivity.ENDPOINT)  //call base url
						.setLogLevel(LogLevel.FULL)
						.build();
						SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(QuizFormActivity.this);
						auth_token_string = settings.getString("authToken", "");
						email=settings.getString("email", "");
						task = restAdapter.create(QuizApi.class);
						task.createQuiz(email, auth_token_string, quiz, new Callback<JsonObject>() {

							@Override
							public void success(JsonObject arg0, Response arg1) {
								String quizID=arg0.get("id").toString().replaceAll("\"", "");
								Intent intent = new Intent(QuizFormActivity.this, QuizDetailsActivity.class);
								intent.putExtra("quizID", Integer.parseInt(quizID));
								intent.putExtra("nMCQ", Integer.parseInt(nMCQ));
								intent.putExtra("nRe", Integer.parseInt(nRe));
								intent.putExtra("quizName", QName);
								startActivity(intent);
							}

							@Override
							public void failure(RetrofitError arg0) {
								JsonObject obj =(JsonObject)arg0.getBody();
								String error=obj.get("error").toString().replaceAll("\"", "");
								Toast.makeText(QuizFormActivity.this, error, Toast.LENGTH_SHORT).show();
							}
						});

					} catch (Exception e) {

						Toast.makeText(QuizFormActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
					}
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
