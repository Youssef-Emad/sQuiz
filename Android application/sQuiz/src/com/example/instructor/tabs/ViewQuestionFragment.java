package com.example.instructor.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.instructor.ViewQuizDetailsActivity;
import com.example.squiz.R;

public class ViewQuestionFragment extends Fragment {
    public static final String ARG_QUESTION = "question";
    public static final String ARG_NQUESTION = "nQuestion";
    private Button publish;
    private String text, right_answer;
    private String[] choices;
    private TextView tvText, tvRight_answer;
    private TextView[] tvChoices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	Bundle args = getArguments();
    	int questionNumber = args.getInt(ARG_QUESTION);
    	int nQuestion = args.getInt(ARG_NQUESTION);
    	text = args.getString("text");
    	right_answer = args.getString("right_answer");
    	choices = args.getStringArray("choices");
    	
    	tvChoices = new TextView[4];
    	
        View view = inflater.inflate(R.layout.fragment_instructor_question, container, false);
        
        tvText = (TextView) view.findViewById(R.id.title);
        tvText.setText(text);
        
        tvRight_answer = (TextView) view.findViewById(R.id.rightAnswer);
        tvRight_answer.setText("Right answer: " + right_answer);
        
        tvChoices[0] = (TextView) view.findViewById(R.id.TextViewFirstChoice);
        tvChoices[0].setText(choices[0]);
        
        tvChoices[1] = (TextView) view.findViewById(R.id.TextViewSecondChoice);
        tvChoices[1].setText(choices[1]);
        
        tvChoices[2] = (TextView) view.findViewById(R.id.TextViewThirdChoice);
        tvChoices[2].setText(choices[2]);
        
        tvChoices[3] = (TextView) view.findViewById(R.id.TextViewFourthChoice);
        tvChoices[3].setText(choices[3]);
        
        if (questionNumber == nQuestion) {
        	publish = (Button) view.findViewById(R.id.publish);
        	((ViewQuizDetailsActivity) getActivity()).setPublishButton(publish);
        	publish.setVisibility(View.VISIBLE);
        }
        return view;
    }
    
}
