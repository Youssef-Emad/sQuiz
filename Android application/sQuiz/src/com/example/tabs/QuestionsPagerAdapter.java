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
	public Fragment getItem(int i) {
		Fragment fragment = new QuestionFragment();
		Bundle args = new Bundle();
		args.putInt(QuestionFragment.ARG_QUESTION, i + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return 100;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Question " + (position + 1);
	}
}