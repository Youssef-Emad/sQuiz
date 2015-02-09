package com.example.tabs;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.squiz.R;

public class QuizzFragment extends ListFragment {
	List<String> quizzes;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		quizzes = new ArrayList<>();
		quizzes.add("Quiz 1");
		quizzes.add("Quiz 2");
		quizzes.add("Quiz 3");
		quizzes.add("Quiz 4");
		setListAdapter(new ArrayAdapter<String>(getActivity(), 
				R.layout.custom_list_item, quizzes));
		return inflater.inflate(R.layout.fragment_quizzes, container, false);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_bar_menu, menu);
		getActivity().getActionBar().setTitle("Quizzes");
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}
