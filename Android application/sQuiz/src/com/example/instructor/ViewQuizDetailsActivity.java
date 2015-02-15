package com.example.instructor;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.Models.PublishInfo;
import com.example.Models.Question;
import com.example.httpRequest.QuestionsApi;
import com.example.instructor.tabs.InstructorGroupFragment;
import com.example.instructor.tabs.ViewQuestionsPagerAdapter;
import com.example.squiz.R;
import com.example.squiz.WelcomeActivity;
import com.google.gson.JsonObject;

public class ViewQuizDetailsActivity extends FragmentActivity {
	private ViewQuestionsPagerAdapter questionPagerAdapter;
	private ViewPager mViewPager;
	private Button publish;
	private int nQuestion;
	private int quizID;
	private PublishInfo pi;
	QuestionsApi task;
	String auth_token_string, email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_details);

		quizID = getIntent().getExtras().getInt("quizID");
		nQuestion = getIntent().getExtras().getInt("nQuestion");

		mViewPager = (ViewPager) findViewById(R.id.questions_pager);

		pi = new PublishInfo();

		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)
		.setLogLevel(LogLevel.FULL)
		.build();
		task = restAdapter1.create(QuestionsApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ViewQuizDetailsActivity.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");

		task.instructorGetQuestions(email, auth_token_string, quizID,
				"instructor", new Callback<Question[]>() {

			@Override
			public void success(Question[] arg0, Response arg1) {
				questionPagerAdapter = new 
						ViewQuestionsPagerAdapter(getSupportFragmentManager(), nQuestion, arg0);
				mViewPager.setAdapter(questionPagerAdapter);
			}

			@Override
			public void failure(RetrofitError arg0) {

			}
		});
	}

	public void setPublishButton(Button publish) {
		this.publish = publish;
		this.publish.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				alert();
			}
		});
	}

	private void alert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ViewQuizDetailsActivity.this);

		int n = InstructorGroupFragment.groups.size();
		CharSequence[] items = new CharSequence[n];

		for (int i = 0; i < n; i++) {
			items[i] = InstructorGroupFragment.groups.get(i).toString();
		}

		builder.setTitle(R.string.publish_title)
		.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				pi.setGroup_id(InstructorGroupFragment.groups.get(which).getId());
				alertDate();
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	private void alertDate() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final DatePicker picker = new DatePicker(this);
		picker.setCalendarViewShown(false);

		builder.setTitle("Pick expire date");
		builder.setView(picker);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				int day    = picker.getDayOfMonth();
				String d   = (day < 10 ? "0" : "") + Integer.toString(day);
				int month  = picker.getMonth() + 1;
				String m   = (month < 10 ? "0" : "") + Integer.toString(month);
				String y   = Integer.toString(picker.getYear());
				String arg = y + "-" + m + "-" + d + " ";
				alertTime(arg);
			}
		});

		builder.show();
	}

	private void alertTime(final String date) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final TimePicker picker = new TimePicker(this);
		picker.setIs24HourView(true);

		builder.setTitle("Pick expire time");
		builder.setView(picker);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String hour = picker.getCurrentHour().toString();
				String min  = picker.getCurrentMinute().toString();
				String expiryDate = date + hour + ":" + min + ":00 +0200";
				pi.setExpiry_date(expiryDate);
				publishQuiz();
			}

		});

		builder.show();
	}

	private void publishQuiz() {
		task.publishQuiz(email, auth_token_string, pi, quizID, 
				new Callback<JsonObject>() {
			
			@Override
			public void failure(RetrofitError arg0) {
				try {
					JsonObject obj=(JsonObject) arg0.getBody();
					String text=obj.get("error").toString();
					text=text.replace(':', ' ').replaceAll("\"", "");
					Toast.makeText(ViewQuizDetailsActivity.this, 
							text, Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					publishQuiz();
				}
			}
			
			@Override
			public void success(JsonObject arg0, Response arg1) {
				Toast.makeText(ViewQuizDetailsActivity.this, 
						"Published successfully", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
