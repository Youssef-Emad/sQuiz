package com.example.Student;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.instructor.tabs.TabsPagerAdapter;
import com.example.squiz.R;


@SuppressWarnings("deprecation")
public class AfterLoginStudentActivity extends FragmentActivity implements ActionBar.TabListener {
	private ViewPager viewPager;
	private ActionBar actionBar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_afterlogin);
		Toast.makeText(AfterLoginStudentActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
		
		actionBar = getActionBar();
		actionBar.setTitle("sQuiz me!");
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager()));
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("Groups").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Quizzes").setTabListener(this));
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}

			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
	
}
