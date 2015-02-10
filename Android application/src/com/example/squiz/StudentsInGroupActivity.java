package com.example.squiz;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class StudentsInGroupActivity extends ListActivity {
	private List<String> students;
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupdetails);
		
		String group = getIntent().getExtras().getString("Group");
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(group);
		
		students = new ArrayList<>();
		for (int i = 0; i < 5; i++) 
			students.add("Student " + i);
		
		setListAdapter(new ArrayAdapter<>(this, 
				R.layout.custom_list_item, students));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_bar_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_add) {
			alertCustom();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void alertCustom() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Enter student E-mail: ");
		LayoutInflater inflater = LayoutInflater.from(this);
		View v = inflater.inflate(R.layout.add_student_alert, null);
		final EditText et = (EditText) v.findViewById(R.id.student_email);
		builder.setView(v);
		builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				students.add(et.getText().toString());
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
}
