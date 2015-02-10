package com.example.tabs;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class QuestionsPagerAdapter extends FragmentStatePagerAdapter {

	public QuestionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public android.support.v4.app.Fragment getItem(int i) {
		Fragment fragment = new QuestionFragment();
		Bundle args = new Bundle();
		args.putInt(QuestionFragment.ARG_QUESTION, i + 1); // Our object is just an integer :-P
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// For this contrived example, we have a 100-object collection.
		return 100;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Question " + (position + 1);
	}
}