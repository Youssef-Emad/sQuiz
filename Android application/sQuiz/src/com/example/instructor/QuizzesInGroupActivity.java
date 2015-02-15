package com.example.instructor;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Models.Quiz;
import com.example.adapters.ListAdapter;
import com.example.httpRequest.QuizApi;
import com.example.squiz.R;
import com.example.squiz.Statistics;
import com.example.squiz.ViewQuizQuestions;
import com.example.squiz.WelcomeActivity;
import com.google.gson.JsonObject;

public class QuizzesInGroupActivity extends ListActivity {
	private List<Quiz> quizzes;
	private ActionBar actionBar;
	QuizApi task;
	String email;
	String auth_token_string;
	int groupId;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupdetails);
		
		ListView listView = getListView();
		listView.setSelector(R.drawable.list_selector);
		
		String group = getIntent().getExtras().getString("Group");
		groupId=getIntent().getExtras().getInt("groupID");
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(group);
		quizzes = new ArrayList<>();
		
		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)  //call base url
		.setLogLevel(LogLevel.FULL)
		.build();

		task = restAdapter1.create(QuizApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(QuizzesInGroupActivity.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");
		
		task.requestQuizzes(email, auth_token_string, groupId, new Callback<List<Quiz>>() {
			
			@Override
			public void success(List<Quiz> arg0, Response arg1) {
				quizzes = arg0;
				setListAdapter(new ListAdapter<Quiz>(QuizzesInGroupActivity.this, R.layout.custom_list_item, quizzes));
				
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				JsonObject type=new JsonObject() ;
				JsonObject obj=(JsonObject) arg0.getBodyAs(type.getClass());
				String text=obj.get("error").toString();
				text=text.replace(':', ' ').replaceAll("\"", "");
				Toast.makeText(QuizzesInGroupActivity.this,text, Toast.LENGTH_SHORT).show();
			
			}
		});
		setListAdapter(new ArrayAdapter<>(this, 
				R.layout.custom_list_item, quizzes));		
				
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_bar_quizzes, menu);
	    
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		alert(quizzes.get(position).getId());
	}
	
	private  void alert(final int selectedQuiz) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
	    builder.setTitle(R.string.dialog_title)
	           .setItems(R.array.items_quiz_in_group, new DialogInterface.OnClickListener() {
	             
	        	   public void onClick(DialogInterface dialog, int which) {
	            	   Intent intent = new Intent();
                       intent.putExtra("Quiz", selectedQuiz);
                       intent.putExtra("groupID", groupId);
                   	if (which == 0) {
    					intent.setClass(QuizzesInGroupActivity.this, Statistics.class);
    					startActivity(intent);
    				}
    				else {
    					intent.setClass(QuizzesInGroupActivity.this, ViewQuizQuestions.class);
    					startActivity(intent);
    				}
	               }
	           });
	    AlertDialog alertDialog = builder.create();
	    alertDialog.show();
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

