package com.example.tabs;

import java.util.ArrayList;
import java.util.List;

import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AbsListView.MultiChoiceModeListener;

import com.example.squiz.QuizFormActivity;
import com.example.squiz.R;

public class QuizzFragment extends ListFragment {
	private List<String> quizzes;
	private List<String> itemsToDelete;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		quizzes = new ArrayList<>();
		itemsToDelete = new ArrayList<>();
		quizzes.add("Quiz 1");
		quizzes.add("Quiz 2");
		quizzes.add("Quiz 3");
		quizzes.add("Quiz 4");
		setListAdapter(new ArrayAdapter<String>(getActivity(), 
				R.layout.custom_list_item, quizzes));
		return inflater.inflate(R.layout.fragment_quizzes, container, false);
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
					itemsToDelete.add(quizzes.get(position));
				else
					itemsToDelete.remove(quizzes.get(position));
			}
			
		});
		super.onActivityCreated(savedInstanceState);
	}
	
	private void deleteSelectedItems() {
		for (String s : itemsToDelete)
			quizzes.remove(s);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_bar_menu, menu);
		getActivity().getActionBar().setTitle("Quizzes");
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_add) {
			startActivity(new Intent(getActivity(), QuizFormActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
