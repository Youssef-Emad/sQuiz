package com.example.instructor.tabs;

import com.example.Models.Question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewQuestionsPagerAdapter extends FragmentStatePagerAdapter {

	private int nQuestion;
	private Question[] questions;

	public ViewQuestionsPagerAdapter(FragmentManager fm, int nQuestion, 
			Question[] questions) {
		super(fm);
		this.questions = questions;
		this.nQuestion = nQuestion;
	}

	@Override
	public Fragment getItem(int i) {
		ViewQuestionFragment fragment = new ViewQuestionFragment();
		Bundle args = new Bundle();
		args.putInt(ViewQuestionFragment.ARG_QUESTION, i + 1);
		args.putInt(ViewQuestionFragment.ARG_NQUESTION, nQuestion);
		args.putString("text", questions[i].getText());
		args.putStringArray("choices", questions[i].getChoices());
		args.putString("right_answer", questions[i].getRight_answer());
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return nQuestion;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Question " + (position + 1);
	}
}