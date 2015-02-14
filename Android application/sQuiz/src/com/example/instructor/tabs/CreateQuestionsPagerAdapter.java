package com.example.instructor.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CreateQuestionsPagerAdapter extends FragmentStatePagerAdapter {

	private int nMCQ;
	private int nRe;
	private CreateQuestionFragment questionFragments[];

	public CreateQuestionsPagerAdapter(FragmentManager fm, int nMCQ, int nRe) {
		super(fm);
		this.nMCQ = nMCQ;
		this.nRe  = nRe;
		questionFragments = new CreateQuestionFragment[nMCQ + nRe];
	}

	@Override
	public Fragment getItem(int i) {
		if (questionFragments[i] == null) {
			CreateQuestionFragment fragment = new CreateQuestionFragment();
			Bundle args = new Bundle();
			args.putInt(CreateQuestionFragment.ARG_QUESTION, i + 1);
			args.putInt(CreateQuestionFragment.ARG_NMCQ, nMCQ);
			args.putInt(CreateQuestionFragment.ARG_NQuestion, nMCQ + nRe);
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

	public CreateQuestionFragment getFragment(int position) {
		return questionFragments[position];
	}
}