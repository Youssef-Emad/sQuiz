package com.example.student.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.Models.Question;

public class TakeQuizPagerAdapter extends FragmentStatePagerAdapter {

	private int nQuestion, nMCQ;
	private Question[] questions;
	private TakeQuestionFragment[] questionFragments;

	public TakeQuizPagerAdapter(FragmentManager fm, int nQuestion, 
			int nMCQ, Question[] questions) {
		super(fm);
		this.questions = questions;
		this.nQuestion = nQuestion;
		this.nMCQ = nMCQ;
		questionFragments = new TakeQuestionFragment[nQuestion];
	}

	@Override
	public Fragment getItem(int i) {
		if (questionFragments[i] == null) {
			TakeQuestionFragment fragment = new TakeQuestionFragment();
			Bundle args = new Bundle();
			args.putInt(TakeQuestionFragment.ARG_QUESTION, i + 1);
			args.putInt(TakeQuestionFragment.ARG_NQUESTION, nQuestion);
			args.putInt(TakeQuestionFragment.ARG_NMCQ, nMCQ);
			args.putString("text", questions[i].getText());
			args.putStringArray("choices", questions[i].getChoices());
			fragment.setArguments(args);
			questionFragments[i] = fragment;
		}
		return questionFragments[i];
	}

	@Override
	public int getCount() {
		return nQuestion;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Question " + (position + 1);
	}

	public TakeQuestionFragment getFragment(int position) {
		return questionFragments[position];
	}
}