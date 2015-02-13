package com.example.instructor.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.instructor.CreateQuizDetailsActivity;
import com.example.squiz.R;

public class QuestionFragment extends Fragment {
    public static final String ARG_QUESTION = "question";
    public static final String ARG_NMCQ = "nMCQ";
    public static final String ARG_NQuestion = "nQuestion";
    private Button create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	Bundle args = getArguments();
    	int questionNumber = args.getInt(ARG_QUESTION);
    	int nMCQ = args.getInt(ARG_NMCQ);
    	int nQuestion = args.getInt(ARG_NQuestion);
        View MCQView = inflater.inflate(R.layout.fragment_question_mcq, container, false);
        View ReView = inflater.inflate(R.layout.fragment_question_rearrange, container, false);
        View rootView = questionNumber > nMCQ ? ReView : MCQView;
        
        if (questionNumber == nQuestion) {
        	create = (Button) rootView.findViewById(R.id.create);
        	((CreateQuizDetailsActivity) getActivity()).setCreateButton(create);
        	create.setVisibility(View.VISIBLE);
        }
        return rootView;
    }
}
