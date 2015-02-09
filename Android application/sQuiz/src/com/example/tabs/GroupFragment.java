package com.example.tabs;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.squiz.GroupDetailsActivity;
import com.example.squiz.R;

public class GroupFragment extends ListFragment {
	public List<String> groups;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		groups = new ArrayList<String>();
		groups.add("Group 1");
		groups.add("Group 2");
		groups.add("Group 3");
		groups.add("Group 4");
		setListAdapter(new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, groups));
		return inflater.inflate(R.layout.fragment_groups, container, false);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		alert(groups.get(position));
	}
	
	public void alert(final String selectedGroup) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = (LayoutInflater) getActivity().getLayoutInflater();
		View customTitleView = inflater.inflate(R.layout.dialog_title_view, null);
		
	    builder.setTitle(R.string.dialog_title).setCustomTitle(customTitleView)
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
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_bar_menu, menu);
		getActivity().getActionBar().setTitle("Groups");
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_add) {
			alert("7oms");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
