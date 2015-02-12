package com.example.tabs;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class QuestionsPagerAdapter extends FragmentStatePagerAdapter {
	
	private int nMCQ;
	private int nRe;

	public QuestionsPagerAdapter(FragmentManager fm, int nMCQ, int nRe) {
		super(fm);
		this.nMCQ = nMCQ;
		this.nRe  = nRe;
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new QuestionFragment();
		Bundle args = new Bundle();
		args.putInt(QuestionFragment.ARG_QUESTION, i + 1);
		args.putInt(QuestionFragment.ARG_NMCQ, nMCQ);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return nMCQ + nRe;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Question " + (position + 1);
	}
}