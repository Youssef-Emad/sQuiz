package com.example.squiz;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.Models.Question;
import com.example.tabs.QuestionFragment;
import com.example.tabs.QuestionsPagerAdapter;

public class QuizDetailsActivity extends FragmentActivity {
	QuestionsPagerAdapter questionPagerAdapter;
	ViewPager mViewPager;
	private Button create;
	private int prevPosition;
	private int nMCQ, nRe, nQuestion;
	private String text, right_answer;
	private String[] choices;
	private Question[] questions;
	private EditText etText, etRight_answer;
	private EditText[] etChoices;
	private RadioGroup rg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_details);

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
				QuestionFragment qf = questionPagerAdapter.getFragment(nQuestion - 1);
				TextView tv = (TextView) qf.getView().findViewById(R.id.QuestionMcqTitle);
				tv.setText("beep");
			}
		});
	}

	private void collectMCQData(int pos) {
		QuestionFragment qf = questionPagerAdapter.getFragment(pos);
		View v = qf.getView();

		etText = (EditText) v.findViewById(R.id.QuestionMcqTitle);
		text = etText.getText().toString();

		etChoices[0] = (EditText) v.findViewById(R.id.editTextFirstChoice);
		etChoices[1] = (EditText) v.findViewById(R.id.editTextSecondChoice);
		etChoices[2] = (EditText) v.findViewById(R.id.editTextThirdChoice);
		etChoices[3] = (EditText) v.findViewById(R.id.editTextFourthChoice);

		choices[0] = etChoices[0].getText().toString();
		choices[1] = etChoices[1].getText().toString();
		choices[2] = etChoices[2].getText().toString();
		choices[3] = etChoices[3].getText().toString();

		switch(rg.getCheckedRadioButtonId()) {
		case R.id.radioFirstChoice:
			right_answer = "a";
		case R.id.radioSecondChoice:
			right_answer = "b";
		case R.id.radioThirdChoice:
			right_answer = "c";
		case R.id.radioFourthChoice:
			right_answer = "d";
		}
		
		questions[pos] = new Question(text, choices, right_answer);
	}

	private void collectReData(int pos) {

	}
}
