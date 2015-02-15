package com.example.squiz;

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
import android.view.MenuItem;

import com.example.Models.Question;
import com.example.httpRequest.QuestionsApi;
import com.example.instructor.tabs.ViewQuestionsPagerAdapter;

public class ViewQuizQuestions extends FragmentActivity {
	QuestionsApi task;
	String auth_token_string, email;
	private ViewQuestionsPagerAdapter questionPagerAdapter;
	private ViewPager mViewPager;
	private int quizID,nQuestion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_details);

		quizID = getIntent().getExtras().getInt("Quiz");
		mViewPager = (ViewPager) findViewById(R.id.questions_pager);
		nQuestion = getIntent().getExtras().getInt("nQuestion");
		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)
		.setLogLevel(LogLevel.FULL)
		.build();
		task = restAdapter1.create(QuestionsApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ViewQuizQuestions.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");

		task.instructorGetQuestions(email, auth_token_string, quizID,
				"instructor", new Callback<Question[]>() {

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


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
