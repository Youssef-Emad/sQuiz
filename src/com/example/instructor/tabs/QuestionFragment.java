package com.example.instructor.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.squiz.R;

public class QuestionFragment extends Fragment {
    public static final String ARG_QUESTION = "question";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_question_fragment_mcq, container, false);
        return rootView;
    }
}
