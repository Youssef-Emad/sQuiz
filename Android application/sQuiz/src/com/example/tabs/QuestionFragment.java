package com.example.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.squiz.R;

public class QuestionFragment extends Fragment {
    public static final String ARG_QUESTION = "question";
    public static final String ARG_NMCQ = "nMCQ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	Bundle args = getArguments();
    	int questionNumber = args.getInt(ARG_QUESTION);
    	int nMCQ = args.getInt(ARG_NMCQ);
        View MCQView = inflater.inflate(R.layout.fragment_question_mcq, container, false);
        View ReView = inflater.inflate(R.layout.fragment_question_rearrange, container, false);
        return questionNumber > nMCQ ? ReView : MCQView;
    }
}
