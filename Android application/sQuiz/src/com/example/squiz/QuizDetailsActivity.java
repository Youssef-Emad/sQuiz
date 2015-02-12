package com.example.squiz;

import com.example.tabs.QuestionsPagerAdapter;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class QuizDetailsActivity extends FragmentActivity {
	QuestionsPagerAdapter questionPagerAdapter;
	ViewPager mViewPager;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);
        
        int nMCQ = getIntent().getExtras().getInt("nMCQ");
        int nRe = getIntent().getExtras().getInt("nRe");
        questionPagerAdapter = new QuestionsPagerAdapter(getSupportFragmentManager(), nMCQ, nRe);

        final ActionBar actionBar = getActionBar();
        actionBar.setTitle(getIntent().getExtras().getString("quizName"));

        actionBar.setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.questions_pager);
        mViewPager.setAdapter(questionPagerAdapter);
    }
}
