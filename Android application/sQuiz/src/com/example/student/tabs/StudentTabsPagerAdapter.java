package com.example.student.tabs;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

 
public class StudentTabsPagerAdapter extends FragmentPagerAdapter {
 
    public StudentTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public ListFragment getItem(int index) {
        switch (index) {
        case 0:
            return new StudentGroupFragment();
        case 1:
            return new StudentQuizzFragment();
        }
        
        return null;
    }
 
    @Override
    public int getCount() {
        return 2;
    }
 
}