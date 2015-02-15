package com.example.squiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.httpRequest.QuizApi;

public class Statistics extends Activity {
	
	String email;
	String auth_token_string;
	int quizID,groupId;
	QuizApi task;
	List<Map<String,Integer>> results;
	String[] students ;
	int[] marks;
	TableLayout tl;
    TableRow tr;
    TextView studentTv,valueTV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		tl = (TableLayout) findViewById(R.id.maintable);
      

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
				addHeaders();
		        addData();
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(Statistics.this, "failed", Toast.LENGTH_SHORT).show();
				
			}
		});
		for (int i=0;i<results.size();i++) {	
			students[i]=results.get(i).keySet().toString();  
			
		}
		for (int i=0;i<results.size();i++) {	
			marks[i]=Integer.parseInt(results.get(i).values().toString());  
			
		}
		  
		
	}
	
	 public void addHeaders(){
		 
         /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
 
        /** Creating a TextView to add to the row **/
        TextView studentTv = new TextView(this);
        studentTv.setText("Students");
        studentTv.setTextColor(Color.GRAY);
        studentTv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        studentTv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        studentTv.setPadding(5, 5, 5, 0);
        tr.addView(studentTv);  // Adding textView to tablerow.
 
        /** Creating another textview **/
        TextView valueTV = new TextView(this);
        valueTV.setText("Operating Systems");
        valueTV.setTextColor(Color.GRAY);
        valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        valueTV.setPadding(5, 5, 5, 0);
        valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(valueTV); // Adding textView to tablerow.
 
        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
 
        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
 
        /** Creating another textview **/
        TextView divider = new TextView(this);
        divider.setText("-----------------");
        divider.setTextColor(Color.GREEN);
        divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.
 
        TextView divider2 = new TextView(this);
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.GREEN);
        divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.
 
        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
    }
 
    /** This function add the data to the table **/
    public void addData(){
 
        for (int i = 0; i < students.length; i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
 
            /** Creating a TextView to add to the row **/
            studentTv = new TextView(this);
            studentTv.setText(students[i]);
            studentTv.setTextColor(Color.RED);
            studentTv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            studentTv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            studentTv.setPadding(5, 5, 5, 5);
            tr.addView(studentTv);  // Adding textView to tablerow.
 
            /** Creating another textview **/
            valueTV = new TextView(this);
            valueTV.setText(marks[i]);
            valueTV.setTextColor(Color.GREEN);
            valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            valueTV.setPadding(5, 5, 5, 5);
            valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV); // Adding textView to tablerow.
 
            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        
    }
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
