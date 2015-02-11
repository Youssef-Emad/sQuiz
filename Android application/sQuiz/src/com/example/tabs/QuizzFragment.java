package com.example.tabs;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
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
import android.widget.ListView;
import android.widget.Toast;

import com.example.Models.Quiz;
import com.example.httpRequest.QuizApi;
import com.example.squiz.R;
import com.example.squiz.WelcomeActivity;

public class QuizzFragment extends ListFragment {
	private List<Quiz> quizzes;
	private List<Quiz> itemsToDelete;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		RestAdapter restAdapter1= new RestAdapter.Builder()
	    .setEndpoint(WelcomeActivity.ENDPOINT)  //call base url
	    .setLogLevel(LogLevel.FULL)
	    .build();
		QuizApi task = restAdapter1.create(QuizApi.class);
		String x="b@a.com";
		task.requestForm(x, "VAmfAkkpLPq4PKeGhpaY", "instructor", new Callback<List<Quiz>>(
				) {
			
			@Override
			public void success(List<Quiz> arg0, Response arg1) {
				quizzes=arg0;
				Toast.makeText(getActivity(),quizzes.get(0).getName(), Toast.LENGTH_SHORT).show();
				
				
			}
			
			@Override
			public void failure(RetrofitError arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		quizzes=new ArrayList<Quiz>();
		setListAdapter(new ArrayAdapter<Quiz>(getActivity(), 
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
		for (Quiz s : itemsToDelete)
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
		return super.onOptionsItemSelected(item);
	}

}
