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
					QuestionFragment qf = questionPagerAdapter.getFragment(position - 1);
					if (position > nMCQ)
						collectReData();
					else
						collectMCQData();
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
}
