package com.example.tabs;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.squiz.GroupDetailsActivity;
import com.example.squiz.R;

public class GroupFragment extends ListFragment {
	private List<String> groups;
	private ArrayAdapter<String> ListAdapter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		groups = new ArrayList<String>();
		ListAdapter = new ArrayAdapter<String>(getActivity(), 
				R.layout.custom_list_item, groups);
		
		groups.add("Group 1");
		groups.add("Group 2");
		groups.add("Group 3");
		groups.add("Group 4");
		setListAdapter(ListAdapter);
		return inflater.inflate(R.layout.fragment_groups, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		registerForContextMenu(getListView());
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		alert(groups.get(position));
	}
	
	private void alert(final String selectedGroup) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
	    builder.setTitle(R.string.dialog_title)
	           .setItems(R.array.items, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	            	   Intent intent = new Intent();
                       intent.setClass(getActivity(), GroupDetailsActivity.class);
                       intent.putExtra("Group", selectedGroup);
	            	   if (which == 0) {
	                       intent.putExtra("Choice", "Quizzes");
	                       startActivity(intent);
	            	   }
	            	   else {
	            		   intent.putExtra("Choice", "Students");
	            		   startActivity(intent);
	            	   }
	               }
	           });
	    AlertDialog alertDialog = builder.create();
	    alertDialog.show();
	}
	
	private void alertCustom() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Enter group name: ");
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View v = inflater.inflate(R.layout.activity_oncreategroup, null);
		final EditText et = (EditText) v.findViewById(R.id.group_name);
		builder.setView(v);
		builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				groups.add(et.getText().toString());
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
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_bar_menu, menu);
		getActivity().getActionBar().setTitle("Groups");
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_add) {
			alertCustom();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getActivity().getMenuInflater();
	    inflater.inflate(R.menu.context_menu, menu);
	}
}
