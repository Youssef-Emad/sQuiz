package com.example.instructor.tabs;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class QuestionsPagerAdapter extends FragmentStatePagerAdapter {
	
	private int numberOfQuestions;

	public QuestionsPagerAdapter(FragmentManager fm, int numberOfQuestions) {
		super(fm);
		this.numberOfQuestions = numberOfQuestions;
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new QuestionFragment();
		Bundle args = new Bundle();
		args.putInt(QuestionFragment.ARG_QUESTION, i + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return numberOfQuestions;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Question " + (position + 1);
	}
}