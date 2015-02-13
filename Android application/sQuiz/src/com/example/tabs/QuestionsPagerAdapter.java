package com.example.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class QuestionsPagerAdapter extends FragmentStatePagerAdapter {

	private int nMCQ;
	private int nRe;
	private QuestionFragment questionFragments[];

	public QuestionsPagerAdapter(FragmentManager fm, int nMCQ, int nRe) {
		super(fm);
		this.nMCQ = nMCQ;
		this.nRe  = nRe;
		questionFragments = new QuestionFragment[nMCQ + nRe];
	}

	@Override
	public Fragment getItem(int i) {
		if (questionFragments[i] == null) {
			QuestionFragment fragment = new QuestionFragment();
			Bundle args = new Bundle();
			args.putInt(QuestionFragment.ARG_QUESTION, i + 1);
			args.putInt(QuestionFragment.ARG_NMCQ, nMCQ);
			args.putInt(QuestionFragment.ARG_NQuestion, nMCQ + nRe);
			fragment.setArguments(args);
			questionFragments[i] = fragment;
		}
		return questionFragments[i];
	}

	@Override
	public int getCount() {
		return nMCQ + nRe;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Question " + (position + 1);
	}

	public QuestionFragment getFragment(int position) {
		return questionFragments[position];
	}
}