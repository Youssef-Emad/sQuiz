package com.example.instructor;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Response;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.Models.Question;
import com.example.httpRequest.QuestionsApi;
import com.example.instructor.tabs.ViewQuestionsPagerAdapter;
import com.example.squiz.R;
import com.example.squiz.WelcomeActivity;

public class ViewQuizDetailsActivity extends FragmentActivity {
	private ViewQuestionsPagerAdapter questionPagerAdapter;
	private ViewPager mViewPager;
	private Button publish;
	private int nQuestion;
	private int quizID;
	private Question[] questions;
	QuestionsApi task;
	String auth_token_string, email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_details);
		
		quizID = getIntent().getExtras().getInt("quizID");
		nQuestion = getIntent().getExtras().getInt("nQuestion");
		
		mViewPager = (ViewPager) findViewById(R.id.questions_pager);
		
		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)
		.setLogLevel(LogLevel.FULL)
		.build();
		task = restAdapter1.create(QuestionsApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ViewQuizDetailsActivity.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");
		
		task.getQuestions(email, auth_token_string, quizID, "instructor", new Callback<Question[]>() {
			
			@Override
			public void success(Question[] arg0, Response arg1) {
				questionPagerAdapter = new 
						ViewQuestionsPagerAdapter(getSupportFragmentManager(), nQuestion, arg0);
				mViewPager.setAdapter(questionPagerAdapter);
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				
			}
		});
	}
	
	public void setPublishButton(Button publish) {
		this.publish = publish;
		this.publish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
