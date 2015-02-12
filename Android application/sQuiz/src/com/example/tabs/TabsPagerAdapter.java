package com.example.tabs;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public ListFragment getItem(int index) {
        switch (index) {
        case 0:
            return new GroupFragment();
        case 1:
            return new QuizFragment();
        }
        
        return null;
    }
 
    @Override
    public int getCount() {
        return 2;
    }
 
}