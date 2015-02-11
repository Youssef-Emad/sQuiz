package com.example.tabs;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.squiz.QuizzesInGroupActivity;
import com.example.squiz.R;
import com.example.squiz.StudentsInGroupActivity;

public class GroupFragment extends ListFragment {
	private List<String> groups;
	private ArrayAdapter<String> ListAdapter;
	private List<String> itemsToDelete;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		groups = new ArrayList<String>();
		itemsToDelete = new ArrayList<String>();
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
		ListView listView = getListView();
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
		        inflater.inflate(R.menu.context_menu, menu);
				return true;
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
	            case R.id.action_delete:
	                deleteSelectedItems();
	                mode.finish(); // Action picked, so close the CAB
	                return true;
	            default:
	                return false;
				}
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				if (checked) 
					itemsToDelete.add(groups.get(position));
				else
					itemsToDelete.remove(groups.get(position));
			}
			
		});
		super.onActivityCreated(savedInstanceState);
	}
	
	private void deleteSelectedItems() {
		for (String s : itemsToDelete)
			groups.remove(s);
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
                       intent.putExtra("Group", selectedGroup);
	            	   if (which == 1) {
	            		   intent.setClass(getActivity(), StudentsInGroupActivity.class);
	                       startActivity(intent);
	            	   }
	            	   else {
	            		   intent.setClass(getActivity(), QuizzesInGroupActivity.class);
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
		View v = inflater.inflate(R.layout.create_group_alert, null);
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
	
}
