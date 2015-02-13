package com.example.instructor;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.Models.Question;
import com.example.httpRequest.QuestionsApi;
import com.example.instructor.tabs.QuestionFragment;
import com.example.instructor.tabs.QuestionsPagerAdapter;
import com.example.squiz.R;
import com.example.squiz.WelcomeActivity;
import com.google.gson.JsonObject;

public class CreateQuizDetailsActivity extends FragmentActivity {
	QuestionsPagerAdapter questionPagerAdapter;
	ViewPager mViewPager;
	private Button create;
	private int prevPosition;
	private int nMCQ, nRe, nQuestion;
	private int id;
	private String text, right_answer;
	private String[] choices;
	private Question[] questions;
	private EditText etText, etRight_answer;
	private EditText[] etChoices;
	private RadioGroup rg;
	QuestionsApi task;
	String auth_token_string, email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_details);

		id = getIntent().getExtras().getInt("quizID");
		nMCQ = getIntent().getExtras().getInt("nMCQ");
		nRe = getIntent().getExtras().getInt("nRe");
		nQuestion = nMCQ + nRe;
		questionPagerAdapter = new QuestionsPagerAdapter(getSupportFragmentManager(), nMCQ, nRe);

		choices = new String[4];
		questions = new Question[nQuestion];
		etChoices = new EditText[4];

		final ActionBar actionBar = getActionBar();
		actionBar.setTitle(getIntent().getExtras().getString("quizName"));
		actionBar.setDisplayHomeAsUpEnabled(true);

		mViewPager = (ViewPager) findViewById(R.id.questions_pager);
		mViewPager.setAdapter(questionPagerAdapter);

		RestAdapter restAdapter1= new RestAdapter.Builder()
		.setEndpoint(WelcomeActivity.ENDPOINT)  //call base url
		.setLogLevel(LogLevel.FULL)
		.build();

		task = restAdapter1.create(QuestionsApi.class);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(CreateQuizDetailsActivity.this);
		auth_token_string = settings.getString("authToken", "");
		email=settings.getString("email", "");

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
	public void setCreateButton(Button create) {
		this.create = create;

		this.create.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (nMCQ == nQuestion)
					collectMCQData(nQuestion - 1);
				else
					collectReData(nQuestion - 1);
				
				task.addQuestions(email, auth_token_string, id, questions, new Callback<JsonObject>() {

					@Override
					public void success(JsonObject arg0, Response arg1) {

					}

					@Override
					public void failure(RetrofitError arg0) {

					}
				});
			}
		});
	}

	private void collectMCQData(int pos) {
		QuestionFragment qf = questionPagerAdapter.getFragment(pos);
		View v = qf.getView();

		etText = (EditText) v.findViewById(R.id.editTextQuestionMcqTitle);
		text = etText.getText().toString();
		
		choices = new String[4];

		etChoices[0] = (EditText) v.findViewById(R.id.editTextFirstChoice);
		etChoices[1] = (EditText) v.findViewById(R.id.editTextSecondChoice);
		etChoices[2] = (EditText) v.findViewById(R.id.editTextThirdChoice);
		etChoices[3] = (EditText) v.findViewById(R.id.editTextFourthChoice);

		choices[0] = etChoices[0].getText().toString();
		choices[1] = etChoices[1].getText().toString();
		choices[2] = etChoices[2].getText().toString();
		choices[3] = etChoices[3].getText().toString();

		rg = (RadioGroup) v.findViewById(R.id.radioGroupChoices);

		switch(rg.getCheckedRadioButtonId()) {
		case R.id.radioFirstChoice:
			right_answer = "a";
			break;
		case R.id.radioSecondChoice:
			right_answer = "b";
			break;
		case R.id.radioThirdChoice:
			right_answer = "c";
			break;
		case R.id.radioFourthChoice:
			right_answer = "d";
			break;
		default:
			right_answer = "";
		}

		questions[pos] = new Question(text, choices, right_answer);
	}

	private void collectReData(int pos) {
		QuestionFragment qf = questionPagerAdapter.getFragment(pos);
		View v = qf.getView();

		etText = (EditText) v.findViewById(R.id.editTextQuestionTitleRearrangement);
		text = etText.getText().toString();
		
		choices = new String[4];

		etChoices[0] = (EditText) v.findViewById(R.id.editTextQuestionRearrangementOption1);
		etChoices[1] = (EditText) v.findViewById(R.id.editTextQuestionRearrangementOption2);
		etChoices[2] = (EditText) v.findViewById(R.id.editTextQuestionRearrangementOption3);
		etChoices[3] = (EditText) v.findViewById(R.id.editTextQuestionRearrangementOption4);

		choices[0] = etChoices[0].getText().toString();
		choices[1] = etChoices[1].getText().toString();
		choices[2] = etChoices[2].getText().toString();
		choices[3] = etChoices[3].getText().toString();

		etRight_answer = (EditText) v.findViewById(R.id.editTextQuestionRearrangementAnswer);
		right_answer = etRight_answer.getText().toString();

		questions[pos] = new Question(text, choices, right_answer);
	}
}
