package com.example.squiz;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

public class QuizzesInGroupActivity extends ListActivity {
	private List<String> quizzes;
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupdetails);
		
		String group = getIntent().getExtras().getString("Group");
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(group);
		
		quizzes = new ArrayList<>();
		for (int i = 0; i < 5; i++) 
			quizzes.add("Quiz " + i);
		
		setListAdapter(new ArrayAdapter<>(this, 
				R.layout.custom_list_item, quizzes));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_bar_quizzes, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
