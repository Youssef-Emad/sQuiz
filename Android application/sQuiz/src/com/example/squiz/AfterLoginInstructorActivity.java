package com.example.squiz;

import com.example.tabs.GroupFragment;
import com.example.tabs.QuizzFragment;
import com.example.tabs.TabListener;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;


@SuppressWarnings("deprecation")
public class AfterLoginInstructorActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		android.app.ActionBar actionBar = getActionBar();
		
		actionBar.setTitle("sQuiz me!");
		
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    Tab tab = actionBar.newTab()
	                       .setText("Groups")
	                       .setTabListener(new TabListener<GroupFragment>(
	                               this, "groups", GroupFragment.class));
	    actionBar.addTab(tab);

	    tab = actionBar.newTab()
	                   .setText("Quizzes")
	                   .setTabListener(new TabListener<QuizzFragment>(
	                           this, "quizzes", QuizzFragment.class));
	    actionBar.addTab(tab);

	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_bar_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
}
