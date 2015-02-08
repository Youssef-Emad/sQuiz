package com.example.tabs;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.squiz.R;

public class GroupFragment extends ListFragment {
	private List<String> groups;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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
	
	private void alert(String selectedGroup) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle(R.string.dialog_title)
	           .setItems(R.array.items, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	            	   if (which == 0) {
	            		   
	            	   }
	            	   else {
	            		   
	            	   }
	               }
	           });
	    AlertDialog alertDialog = builder.create();
	    alertDialog.show();
	}
}
