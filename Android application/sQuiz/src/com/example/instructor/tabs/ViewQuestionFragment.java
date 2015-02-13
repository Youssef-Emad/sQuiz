package com.example.instructor.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.instructor.ViewQuizDetailsActivity;
import com.example.squiz.R;

public class ViewQuestionFragment extends Fragment {
    public static final String ARG_QUESTION = "question";
    public static final String ARG_NQUESTION = "nQuestion";
    private Button publish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	Bundle args = getArguments();
    	int questionNumber = args.getInt(ARG_QUESTION);
    	int nQuestion = args.getInt(ARG_NQUESTION);
    	
        View view = inflater.inflate(R.layout.fragment_instructor_question, container, false);
        
        if (questionNumber == nQuestion) {
        	publish = (Button) view.findViewById(R.id.publish);
        	((ViewQuizDetailsActivity) getActivity()).setPublishButton(publish);
        	publish.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
