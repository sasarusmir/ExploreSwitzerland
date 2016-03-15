package com.exploreswitzerland.exploreswitzerland;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class TabLayoutActivity extends ActionBarActivity implements
		OnTabChangeListener, OnPageChangeListener {

	MyPageAdapter pageAdapter;
	private ViewPager mViewPager;
	private TabHost mTabHost;
	private ActionBar actionBar;

	private TextView titleTV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_layout);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		// Tab Initialization
		initialiseTabHost();

		// Fragments and ViewPager Initialization
		actionBar = getSupportActionBar();
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.action_bar_title, null);

		titleTV = (TextView) v.findViewById(R.id.a_title);
		Typeface font = Typeface.createFromAsset(getAssets(), "Lobster.ttf");
		titleTV.setTypeface(font);
		LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setCustomView(v, layout);

		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeAsUpIndicator(R.drawable.back_button_xml);
		List<Fragment> fragments = getFragments();
		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(pageAdapter);

		mViewPager.setOnPageChangeListener(TabLayoutActivity.this);

		mTabHost.getTabWidget().setDividerDrawable(null);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
		int pageNr = extras.getInt("PosNr");

//		Toast.makeText(getApplicationContext(), "Itedm id clicked is "+ pageNr,
//				   Toast.LENGTH_LONG).show();
		mTabHost.setCurrentTab(pageNr);
		} else {mTabHost.setCurrentTab(0);}

		//
		// View vAbout = inflator.inflate(R.layout.fragment_about, null);
		// Button btnClimate = (Button)
		// vAbout.findViewById(R.id.about_btn_climate);
		// btnClimate.setOnClickListener(this);
		
		
	}

	@Override
	protected void onStart() {
		super.onStart();

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);

		Bundle extras = getIntent().getExtras();
		if (extras != null){
		int pageNr = extras.getInt("PosNr");
		mTabHost.setCurrentTab(pageNr);}

	}
	
	

	private static void AddTab(TabLayoutActivity activity, TabHost tabHost,
			TabHost.TabSpec tabSpec) {
		tabSpec.setContent(new MyTabFactory(activity));
		tabHost.addTab(tabSpec);
	}

	// Manages the Tab changes, synchronizing it with Pages
	public void onTabChanged(String tag) {
		int pos = this.mTabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(pos);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	// Manages the Page changes, synchronizing it with Tabs
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		int pos = this.mViewPager.getCurrentItem();
		this.mTabHost.setCurrentTab(pos);
	}

	@Override
	public void onPageSelected(int arg0) {
	}

	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();

		// TODO Put here your Fragments
		ExploreFragment f1 = (ExploreFragment) ExploreFragment.newInstance();
		AboutFragment f2 = new AboutFragment();
		SymbolsFragment f3 = new SymbolsFragment();
		fList.add(f1);
		fList.add(f2);
		fList.add(f3);

		return fList;
	}

	private void initialiseTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		LayoutInflater inflatorTab = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View vE = inflatorTab.inflate(R.layout.action_bar_tab_explore, null);
		View vA = inflatorTab.inflate(R.layout.action_bar_tab_about, null);
		View vSy = inflatorTab.inflate(R.layout.action_bar_tab_symbols, null);

		// TODO Put here your Tabs
		TabLayoutActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("tab1").setIndicator(vE));
		TabLayoutActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("tab2").setIndicator(vA));
		TabLayoutActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("tab3").setIndicator(vSy));

		mTabHost.setOnTabChangedListener(this);

	}
	
/*
	public void openSearch(View view){
		Intent intent = new Intent(this, SampleSearchActivity.class);
		intent.putExtra(SearchManager.QUERY, "Cross");
		onSearchRequested();
	}
	*/
	
}