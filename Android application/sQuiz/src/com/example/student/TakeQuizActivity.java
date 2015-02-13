package com.example.student;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Models.Question;
import com.example.httpRequest.QuestionsApi;
import com.example.squiz.R;
import com.example.squiz.WelcomeActivity;
import com.example.student.tabs.TakeQuizPagerAdapter;
import com.google.gson.JsonObject;

public class TakeQuizActivity extends FragmentActivity {

	private TakeQuizPagerAdapter questionPagerAdapter;
	private ViewPager mViewPager;
	private Button submit;
	private int nQuestion, nMCQ, prevPosition;
	private int quizID;
	QuestionsApi task;
	String auth_token_string, email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_quiz);

		quizID    = getIntent().getExtras().getInt("quizID");
		nQuestion = getIntent().getExtras().getInt("nQuestion");
		nMCQ      = getIntent().getExtras().getInt("nMCQ");

		mViewPager = (ViewPager) findViewById(R.id.questions_pager);

		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)
		.setLogLevel(LogLevel.FULL)
		.build();
		task = restAdapter1.create(QuestionsApi.class);
		SharedPreferences settings = 
				PreferenceManager.getDefaultSharedPreferences(TakeQuizActivity.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");

		task.studentGetQuestions(email, auth_token_string, quizID,
				"student", new Callback<Question[]>() {

			@Override
			public void success(Question[] arg0, Response arg1) {
				questionPagerAdapter = new TakeQuizPagerAdapter
						(getSupportFragmentManager(), nQuestion, nMCQ, arg0);
				mViewPager.setAdapter(questionPagerAdapter);
			}
			@Override
			public void failure(RetrofitError arg0) {
				JsonObject type=new JsonObject() ;
				JsonObject obj=(JsonObject) arg0.getBodyAs(type.getClass());
				String text=obj.get("error").toString();
				text=text.replace(':', ' ').replaceAll("\"", "");
				Toast.makeText(TakeQuizActivity.this,text, Toast.LENGTH_SHORT).show();
			}
		});
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position > 0 && position > prevPosition) {
					if (position - 1 < nMCQ)
						collectMCQData(position - 1);
					else
						collectReData(position - 1);
				}
				prevPosition = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}
	
	private void collectMCQData(int pos) {
		
	}

	public void setSubmitButton(Button submit) {
		this.submit = submit;
		this.submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

}
