package com.example.squiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Models.Result;
import com.example.adapters.ResultAdapter;
import com.example.httpRequest.QuizApi;

public class Statistics extends ListActivity {
	
	String email;
	String auth_token_string;
	int quizID,groupId;
	QuizApi task;
	List<Result> results;
	Map<String, Integer> m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
      
		quizID=getIntent().getExtras().getInt("quizID");
		groupId=getIntent().getExtras().getInt("groupID");
		results = new ArrayList<>();

		final ListView l = getListView();
		
		
		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)  //call base url
		.setLogLevel(LogLevel.FULL)
		.build();

		task = restAdapter1.create(QuizApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(Statistics.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");
		
		task.getResults(email, auth_token_string, quizID, groupId, new Callback<Map<String, Integer>>() {
			
			@Override
			public void success(Map<String, Integer> arg0, Response arg1) {
				m =arg0;
				Toast.makeText(Statistics.this, "Quiz results", Toast.LENGTH_SHORT).show();
				for (Map.Entry<String, Integer> result : 
					m.entrySet()) {
					results.add(new Result(result.getKey(), result.getValue()));
				}
				l.setAdapter(new ResultAdapter(Statistics.this, R.layout.table, results));
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(Statistics.this, "failed", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }
