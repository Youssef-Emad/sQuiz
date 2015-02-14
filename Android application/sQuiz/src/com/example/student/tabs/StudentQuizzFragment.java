package com.example.student.tabs;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Models.Quiz;
import com.example.adapters.ListAdapter;
import com.example.httpRequest.QuizApi;
import com.example.squiz.R;
import com.example.squiz.WelcomeActivity;
import com.example.student.TakeQuizActivity;

public class StudentQuizzFragment extends ListFragment {
	private List<Quiz> quizzes;
	private ListAdapter<Quiz> QuizAdapter;
	//	private List<Quiz> itemsToDelete;
	QuizApi task;
	String auth_token_string, email;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);

		quizzes=new ArrayList<Quiz>();
		//itemsToDelete = new ArrayList<Quiz>();
		
		ListView listView = getListView();
		listView.setSelector(R.drawable.list_selector);

		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)  //call base url
		.setLogLevel(LogLevel.FULL)
		.build();
		task = restAdapter1.create(QuizApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		auth_token_string = settings.getString("authToken", ""/*default value*/);
		email=settings.getString("email", "");
		task.studentRequestQuiz(email, auth_token_string, "student", new Callback<List<Quiz>>(
				) {

			@Override
			public void success(List<Quiz> arg0, Response arg1) {
				quizzes=arg0;
				QuizAdapter = new ListAdapter<Quiz>(getActivity(), 
						R.layout.custom_list_item, quizzes);
				setListAdapter(QuizAdapter);
			}

			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(getActivity(), "Failed for a reason", Toast.LENGTH_SHORT).show();

			}
		});
		return inflater.inflate(R.layout.fragment_quizzes, container, false);
	}

	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		final Intent intent = new Intent(getActivity(), TakeQuizActivity.class);

		Quiz q = quizzes.get(pos);

		intent.putExtra("quizID", q.getId());
		intent.putExtra("nQuestion", q.getNQuestion());
		intent.putExtra("nMCQ", q.getnMCQ());
		intent.putExtra("duration", q.getDuration());
		intent.putExtra("quizName", q.toString());
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("You have " + q.getDuration() + 
				" minutes to solve this quiz.\n\n" + "Do you want to solve it now?")
		.setTitle("Alert Message")
		.setPositiveButton("Solve", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(intent);
			}
		});
		
		builder.setNegativeButton("Cancel", null);

		AlertDialog alertDialog = builder.create();
		alertDialog.show();

		super.onListItemClick(l, v, pos, id);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		//ListView listView = getListView();
		//	listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		/*	listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

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

			/*		@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
	            case R.id.action_delete:
	            	    task.deleteQuizzes(email, auth_token_string, itemsToDelete, new Callback<JsonObject>() {
						@Override
						public void failure(RetrofitError arg0) {
							JsonObject obj=(JsonObject) arg0.getBody();
							String text=obj.get("info").toString() + "-";
									text=text.replace(':', ' ').replaceAll("\"", "");
							Toast.makeText(getActivity(),text, Toast.LENGTH_SHORT).show();
						}

						@Override
						public void success(JsonObject arg0, Response arg1) {
							deleteSelectedItems();							
						}
					});
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

		});  */
		super.onActivityCreated(savedInstanceState);
	}

	/*	private void deleteSelectedItems() {
		for (Quiz s : itemsToDelete)
			quizzes.remove(s);
		QuizAdapter.notifyDataSetChanged();
	} */

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_bar_quizzes, menu);
		getActivity().getActionBar().setTitle("Quizzes");
		super.onCreateOptionsMenu(menu, inflater);
	}

	/* @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_add) {
			startActivity(new Intent(getActivity(), QuizFormActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	} */

}
