package com.exploreswitzerland.exploreswitzerland;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ExploreLuzernActivity extends ActionBarActivity implements
		OnTabChangeListener, OnPageChangeListener {

	MyPageAdapter pageAdapter;
	private ViewPager mViewPager;
	private TabHost mTabHost;
	private ActionBar actionBar;
	
	private RelativeLayout aLayout;
	private TextView titleTV;
	private RelativeLayout.LayoutParams layoutText;
	
//	public static View toBeScrolled;
	
//	private FragmentManager fragmentManager = getSupportFragmentManager();
//	private FragmentTransaction transaction = fragmentManager.beginTransaction();
	
	Fragment historyFragment = new ExploreLuHistoryFragment();
	Fragment landmarksFragment = new ExploreLuLandmarksFragment();
	Fragment hikingFragment = new ExploreLuHikingFragment();
	Fragment stayFragment = new ExploreLuStayFragment();


//	public static FrameLayout fLuzern;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explore_luzern);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		// Tab Initialization
		initialiseTabHost();

		// Fragments and ViewPager Initialization
		actionBar = getSupportActionBar();
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.action_bar_title, null);

		titleTV = (TextView) v.findViewById(R.id.a_title);
		titleTV.setText(R.string.title_activity_explore_luzern);
		titleTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		
		layoutText = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		
		aLayout = (RelativeLayout) v.findViewById(R.id.a_layout);
		titleTV.post(placeTitleInMiddle);
		titleTV.setLayoutParams(layoutText);
		

		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setHomeAsUpIndicator(R.drawable.back_button_xml);
		actionBar.setCustomView(v, layout);
		

		// actionBar.setHomeAsUpIndicator(R.drawable.back_btn_xml);
		actionBar.setDisplayHomeAsUpEnabled(true);
		List<Fragment> fragments = getFragments();
		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(pageAdapter);

		mViewPager.setOnPageChangeListener(ExploreLuzernActivity.this);
		
		mTabHost.getTabWidget().setDividerDrawable(null);

		Bundle extras = getIntent().getExtras();
		if (extras != null){
		int pageNr = extras.getInt("PosNr");
//
//		Toast.makeText(getApplicationContext(),
//				"Itedm id clicked is " + pageNr, Toast.LENGTH_LONG).show();
		mTabHost.setCurrentTab(pageNr);
		
		} else {mTabHost.setCurrentTab(0);}
		
		
//		fLuzern = (FrameLayout) findViewById(R.id.fragment_container_explore_luzern);
		
	}

	

	private static void AddTab(ExploreLuzernActivity activity, TabHost tabHost,
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
		ExploreLuFragment f1 = new ExploreLuFragment();
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
		ExploreLuzernActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("tab1").setIndicator(vE));
		ExploreLuzernActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("tab2").setIndicator(vA));
		ExploreLuzernActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("tab3").setIndicator(vSy));

		mTabHost.setOnTabChangedListener(this);

	}

	
	public void openDefault(View view) {
		
		View parent = view.getRootView();
		
		TextView bHistory = (TextView) parent
				.findViewById(R.id.custom_nav_button_history);
		TextView bLandmarks = (TextView) parent
				.findViewById(R.id.custom_nav_button_landmarks);

		TextView bHiking = (TextView) parent
				.findViewById(R.id.custom_nav_button_hiking);

		TextView bStay = (TextView) parent
				.findViewById(R.id.custom_nav_button_stay);
//		final Button bBasic = (Button) vFd.findViewById(R.id.explore_luzern_btn_basic);

		Fragment fragmentDefault = new ExploreLuDefaultFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
 		FragmentTransaction transaction = fragmentManager.beginTransaction();
     	transaction.replace(R.id.fragment_container_explore_luzern,
					fragmentDefault);
			transaction.addToBackStack(null);
			
			bHistory.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.history_icn, 0, 0);
			bHistory.setBackgroundResource(R.drawable.tab_bg);
			bHistory.setTextColor(getResources().getColor(R.color.white));
			
			bLandmarks.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.naturelandmark_icn, 0, 0);
			bLandmarks.setBackgroundResource(R.drawable.tab_bg);
			bLandmarks.setTextColor(getResources().getColor(R.color.white));
			
			bHiking.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.hiking_icn, 0, 0);
			bHiking.setBackgroundResource(R.drawable.tab_bg);
			bHiking.setTextColor(getResources().getColor(R.color.white));
			
			bStay.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.stay_icn, 0, 0);
			bStay.setBackgroundResource(R.drawable.tab_bg);
			bStay.setTextColor(getResources().getColor(R.color.white));
			transaction.commit();
	}
	
	public void openAdress(View view) {
		String URL = "";
		
		if (view.getId() == R.id.explore_lu_stay_adress_lucerne) {
			URL = "http://www.marriott.de/hotels/fact-sheet/travel/emlbr-renaissance-lucerne-hotel/";
		} else if (view.getId() == R.id.explore_lu_stay_adress_alpina){
			URL = "http://www.alpina-luzern.ch";
		} else if (view.getId() == R.id.explore_lu_stay_adress_hotel){
			URL = "http://the-hotel.ch";
		} else if (view.getId() == R.id.explore_lu_stay_adress_cascada){
			URL = "http://www.cascada.ch";
		}

		Uri uri = Uri.parse(URL);
        Intent openBrowser = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(openBrowser);
	}
	
	public void openPhone(View view) {
		String phone = "";
		TextView text = (TextView) view;
		
		phone = text.getText().toString();
		
		Intent callIntent = new Intent(Intent.ACTION_CALL);          
        callIntent.setData(Uri.parse("tel:"+phone));          
        startActivity(callIntent);
	}
	
	public void openActivity(View view){
//		Fragment historyFragment = new ExploreLuHistoryFragment();
//		Fragment landmarksFragment = new ExploreLuLandmarksFragment();
//		Fragment hikingFragment = new ExploreLuHikingFragment();
//		Fragment stayFragment = new ExploreLuStayFragment();
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		
		if (view.getId() == R.id.custom_nav_button_history) {
			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
					historyFragment);
//			fragmentTransaction.addToBackStack(null);

			fragmentTransaction.commit();
		} else if (view.getId() == R.id.custom_nav_button_landmarks) {
			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
					landmarksFragment);
//			fragmentTransaction.addToBackStack(null);

			fragmentTransaction.commit();
		} else if (view.getId() == R.id.custom_nav_button_hiking) {
			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
					hikingFragment);
//			fragmentTransaction.addToBackStack(null);

			fragmentTransaction.commit();
		} else if (view.getId() == R.id.custom_nav_button_stay) {
			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
					stayFragment);
//			fragmentTransaction.addToBackStack(null);

			fragmentTransaction.commit();
		}

	}
	
	Runnable placeTitleInMiddle = new Runnable() {

		@SuppressLint("NewApi")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int width1 = titleTV.getWidth();
			int widthLayout = aLayout.getWidth();
			int marginLeft = (int)(((widthLayout/2)-(width1/2))-((width-widthLayout)/2));
			layoutText.setMargins(marginLeft, 0, 0, 0);
		}
	};
	
	
}