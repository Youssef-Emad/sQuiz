package com.example.student.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.squiz.R;
import com.example.student.TakeQuizActivity;

public class TakeQuestionFragment extends Fragment {
    public static final String ARG_QUESTION = "question";
    public static final String ARG_NQUESTION = "nQuestion";
    public static final String ARG_NMCQ = "nMCQ";
    private View view;
    private Button submit;
    private String text;
    private String[] choices;
    private TextView tvText;
    private TextView[] tvChoices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	Bundle args = getArguments();
    	int questionNumber = args.getInt(ARG_QUESTION);
    	int nQuestion = args.getInt(ARG_NQUESTION);
    	int nMCQ = args.getInt(ARG_NMCQ);
    	text = args.getString("text");
    	choices = args.getStringArray("choices");
    	
    	tvChoices = new TextView[4];
    	
        View MCQView = inflater.inflate(R.layout.fragment_student_question_mcq
        		, container, false);
        View ReView  = inflater.inflate(R.layout.fragment_student_question_rearrange
        		, container, false);
        
        view = questionNumber > nMCQ ? ReView : MCQView;
        
        tvText = (TextView) view.findViewById(R.id.title);
        tvText.setText(text);
        
        tvChoices[0] = (TextView) view.findViewById(R.id.TextViewFirstChoice);
        tvChoices[0].setText(choices[0]);
        
        tvChoices[1] = (TextView) view.findViewById(R.id.TextViewSecondChoice);
        tvChoices[1].setText(choices[1]);
        
        tvChoices[2] = (TextView) view.findViewById(R.id.TextViewThirdChoice);
        tvChoices[2].setText(choices[2]);
        
        tvChoices[3] = (TextView) view.findViewById(R.id.TextViewFourthChoice);
        tvChoices[3].setText(choices[3]);
        
        
        if (questionNumber == nQuestion) {
        	submit = (Button) view.findViewById(R.id.submit);
        	((TakeQuizActivity) getActivity()).setSubmitButton(submit);
        	submit.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
