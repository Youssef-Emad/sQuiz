package com.example.instructor.tabs;

import com.example.Models.Question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewQuestionsPagerAdapter extends FragmentStatePagerAdapter {

	private int nMCQ;
	private int nRe;
	private Question[] questions;

	public ViewQuestionsPagerAdapter(FragmentManager fm, Question[] questions) {
		super(fm);
		this.questions = questions;
	}

	@Override
	public Fragment getItem(int i) {
		CreateQuestionFragment fragment = new CreateQuestionFragment();
		Bundle args = new Bundle();
		args.putInt(CreateQuestionFragment.ARG_QUESTION, i + 1);
		args.putInt(CreateQuestionFragment.ARG_NMCQ, nMCQ);
		args.putString("text", questions[i].getText());
		args.putStringArray("choices", questions[i].getChoices());
		args.putString("right_answer", questions[i].getRight_answer());
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