package com.example.student;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Models.Question;
import com.example.httpRequest.QuestionsApi;
import com.example.squiz.R;
import com.example.squiz.WelcomeActivity;
import com.example.student.tabs.TakeQuestionFragment;
import com.example.student.tabs.TakeQuizPagerAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TakeQuizActivity extends FragmentActivity {

	private TakeQuizPagerAdapter questionPagerAdapter;
	private ViewPager mViewPager;
	private Button submit;
	private int nQuestion, nMCQ, prevPosition, duration;
	private int quizID;
	QuestionsApi task;
	String auth_token_string, email;
	private String[] answers;
	private TextView timer;
	private String quizName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_quiz);

		quizID    = getIntent().getExtras().getInt("quizID");
		nQuestion = getIntent().getExtras().getInt("nQuestion");
		nMCQ      = getIntent().getExtras().getInt("nMCQ");
		duration  = getIntent().getExtras().getInt("duration");
		quizName  = getIntent().getExtras().getString("quizName");

		ActionBar actionBar = getActionBar();
		
		actionBar.setTitle(quizName);

		answers = new String[nQuestion];

		mViewPager = (ViewPager) findViewById(R.id.questions_pager);
		timer = (TextView) findViewById(R.id.timer);
		
		String time = (duration < 10 ? "0" : "") + Integer.toString(duration) + ":00";
		timer.setText(time);

		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)
		.setLogLevel(LogLevel.FULL)
		.build();
		task = restAdapter1.create(QuestionsApi.class);
		SharedPreferences settings = 
				PreferenceManager.getDefaultSharedPreferences(TakeQuizActivity.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");

		task.studentGetQuestions(email, auth_token_string, quizID,
				"student", new Callback<Question[]>() {

					@Override
					public void success(Question[] arg0, Response arg1) {
						questionPagerAdapter = new TakeQuizPagerAdapter
								(getSupportFragmentManager(), nQuestion, nMCQ, arg0);
						mViewPager.setAdapter(questionPagerAdapter);
						
						new CountDownTimer(duration * 60 * 1000, 1000) {

						     public void onTick(long millisUntilFinished) {
						         long sec = millisUntilFinished / 1000;
						         long min = sec / 60;
						         sec %= 60;
						         String s = (sec < 10 ? "0" : "") + Integer.toString((int) sec);
						         String m = (min < 10 ? "0" : "") + Integer.toString((int) min);
						         timer.setText(m + ":" + s);
						     }

						     public void onFinish() {
						         alertTimeOut();
						     }
						  }.start();
					}
					@Override
					public void failure(RetrofitError arg0) {
						JsonObject type=new JsonObject() ;
						JsonObject obj=(JsonObject) arg0.getBodyAs(type.getClass());
						String text=obj.get("error").toString();
						text=text.replace(':', ' ').replaceAll("\"", "");
						Toast.makeText(TakeQuizActivity.this,text, Toast.LENGTH_SHORT).show();
					}
				});

		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position > 0 && position > prevPosition) {
					if (position - 1 < nMCQ)
						collectMCQData(position - 1);
					else
						collectReData(position - 1);
				}
				prevPosition = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private void collectMCQData(int pos) {
		TakeQuestionFragment f = questionPagerAdapter.getFragment(pos);
		View v = f.getView();
		RadioGroup rg = (RadioGroup) v.findViewById(R.id.radioGroupChoices);
		switch (rg.getCheckedRadioButtonId()) {
		case R.id.radioFirstChoice:
			answers[pos] = "a";
			break;
		case R.id.radioSecondChoice:
			answers[pos] = "b";
			break;
		case R.id.radioThirdChoice:
			answers[pos] = "c";
			break;
		case R.id.radioFourthChoice:
			answers[pos] = "d";
			break;
		default:
			answers[pos] = "";
		}
	}
	
	private void collectReData(int pos) {
		TakeQuestionFragment f = questionPagerAdapter.getFragment(pos);
		View v = f.getView();
		EditText et = (EditText) v.findViewById(R.id.rightAnswer);
		answers[pos] = et.getText().toString();
	}

	public void setSubmitButton(Button submit) {
		this.submit = submit;
		this.submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				submitSolution();
			}
		});
	}
	
	private void alertTimeOut() {
		AlertDialog.Builder builder = new AlertDialog.Builder(TakeQuizActivity.this);
		builder.setMessage("Time Out!")
		.setTitle("Alert Message")
		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				submitSolution();
			}
		});
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	private void submitSolution() {
		if (nMCQ == nQuestion)
			collectMCQData(nQuestion - 1);
		else
			collectReData(nQuestion - 1);
		
		Gson gson = new Gson();
		String ans = gson.toJson(answers);
		
		JsonParser jp = new JsonParser();
		JsonObject jo = new JsonObject();
		jo.add("answers", jp.parse(ans));
		
		task.mark(email, auth_token_string, jo, quizID, new Callback<JsonObject>() {

			@Override
			public void failure(RetrofitError arg0) {
				JsonObject obj=(JsonObject) arg0.getBody();
				String text=obj.get("error").toString() ;
				text=text.replace(':', ' ').replaceAll("\"", "");
				Toast.makeText(TakeQuizActivity.this,
						text, Toast.LENGTH_SHORT).show();
				finish();
			}

			@Override
			public void success(JsonObject arg0, Response arg1) {
				Toast.makeText(TakeQuizActivity.this, 
						"Your answers have been submitted successfully", Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}
}
