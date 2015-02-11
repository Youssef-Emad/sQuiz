package com.example.squiz;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		alert(quizzes.get(position));
	}
	
	private  void alert(final String selectedQuiz) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
	    builder.setTitle(R.string.dialog_title)
	           .setItems(R.array.items_quiz_in_group, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	            	   Intent intent = new Intent();
                       intent.putExtra("Quiz", selectedQuiz);
	               }
	           });
	    AlertDialog alertDialog = builder.create();
	    alertDialog.show();
	}
}

