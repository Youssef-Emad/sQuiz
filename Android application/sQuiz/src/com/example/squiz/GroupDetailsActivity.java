package com.example.squiz;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

public class GroupDetailsActivity extends ListActivity {
	private List<String> list;
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupdetails);
		
		String group = getIntent().getExtras().getString("Group");
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(group);
		
		list = new ArrayList<>();
		String choice = getIntent().getExtras().getString("Choice");
		if (choice.equals("Quizzes")) {
			list.clear();
			for (int i = 0; i < 100; i++) {
				list.add("Quizz " + i);
			}
		}
		else if (choice.equals("Students")){
			list.clear();
			for (int i = 0; i < 100; i++) {
				list.add("Student " + i);
			}
		}
		
		setListAdapter(new ArrayAdapter<>(this, 
				R.layout.custom_list_item, list));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_bar_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
}
