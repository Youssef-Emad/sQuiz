package com.example.squiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.httpRequest.QuizApi;

public class Statistics extends Activity {
	
	String email;
	String auth_token_string;
	int quizID,groupId;
	QuizApi task;
	List<Map<String,Integer>> results;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		TableLayout table = new TableLayout(this);

        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

		quizID=getIntent().getExtras().getInt("Quiz");
		groupId=getIntent().getExtras().getInt("groupID");
		results =new ArrayList<>();
		
		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)  //call base url
		.setLogLevel(LogLevel.FULL)
		.build();

		task = restAdapter1.create(QuizApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(Statistics.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");
		
		task.getResults(email, auth_token_string, quizID, groupId, new Callback<List<Map<String,Integer>>>() {
			
			@Override
			public void success(List<Map<String,Integer>> arg0, Response arg1) {
				results =arg0;
				Toast.makeText(Statistics.this, "tmam", Toast.LENGTH_SHORT).show();
	
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(Statistics.this, "failed", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		for (Map<String, Integer> item : results) {	
			
			
		}
	}
}
