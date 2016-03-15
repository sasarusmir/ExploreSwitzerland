package com.exploreswitzerland.exploreswitzerland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ExploreLuHistoryFragment extends Fragment implements OnClickListener {
	
	private boolean clickedHistory = false;
	private boolean clickedCulture = false;
	
	private String searchRequestKey;
	private String searchRequest;

	private int scrollToValue;

	public static View toBeScrolled;


	public static ScrollView sHistory;
	private LinearLayout lHistory;
	private LinearLayout lCultureLandmarks;
	
	private SharedPreferences prefs;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_explore_lu_history, container, false);
		
		return rootView;
		}
	
	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		View parent = view.getRootView();
		
		TextView bHistory = (TextView) parent
				.findViewById(R.id.custom_nav_button_history);
		TextView bLandmarks = (TextView) parent
				.findViewById(R.id.custom_nav_button_landmarks);

		TextView bHiking = (TextView) parent
				.findViewById(R.id.custom_nav_button_hiking);

		TextView bStay = (TextView) parent
				.findViewById(R.id.custom_nav_button_stay);
		
		bHistory.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.history_icn_active, 0, 0);
		bHistory.setBackgroundResource(R.drawable.tab_bg_active);
		bHistory.setTextColor(getResources().getColor(R.color.RedExplore));
		
		bLandmarks.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.naturelandmark_icn, 0, 0);
		bLandmarks.setBackgroundResource(R.drawable.tab_bg);
		bLandmarks.setTextColor(getResources().getColor(R.color.white));
		
		bHiking.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.hiking_icn, 0, 0);
		bHiking.setBackgroundResource(R.drawable.tab_bg);
		bHiking.setTextColor(getResources().getColor(R.color.white));
		
		bStay.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.stay_icn, 0, 0);
		bStay.setBackgroundResource(R.drawable.tab_bg);
		bStay.setTextColor(getResources().getColor(R.color.white));
//		bLandmarks.setBackgroundResource(R.drawable.explore_nav_b);
//		bHiking.setBackgroundResource(R.drawable.explore_nav_c);
//		bStay.setBackgroundResource(R.drawable.explore_nav_d);
		
		lHistory = (LinearLayout) view.findViewById(R.id.explore_luzern_layout_history);
		lCultureLandmarks = (LinearLayout) view.findViewById(R.id.explore_luzern_layout_culturelandmarks);
		
		lHistory.setOnClickListener(this);
		lCultureLandmarks.setOnClickListener(this);
		
		sHistory = (ScrollView) view.findViewById(R.id.explore_lu_history_scrollview);
		
		// SharedPreferences
				prefs = getActivity().getApplicationContext().getSharedPreferences(
						"com.exploreswitzerland.exploreswitzerland",
						Context.MODE_PRIVATE);
				searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

				searchRequest = prefs.getString(searchRequestKey, null);
				if (searchRequest != null && !searchRequest.equals("")) {
					String fragmentContainer = "fragment_container_explore_luzern_history_";
					String button = "explore_luzern_btn_";
					
					String[] exploreLuHistory = {"history", "culturelandmarks"};
					
					String[] exploreLuHistoryCulture = {"chapelbridge", "watertower", "lionmonument"};
					
					if(searchRequest.equals("history")) {
						clickedHistory = true;
					} else if (searchRequest.equals("culturelandmarks")) {
						clickedCulture = true;
					}
					
					
					for (String a : exploreLuHistory){
					if (searchRequest.equals(a)) {
						addFragmentsInActivity(
								getResources().getIdentifier(
										fragmentContainer + searchRequest, "id",
										getActivity().getPackageName()),
								getNewFragment(searchRequest),
								(Button) view.findViewById(getResources()
										.getIdentifier(button + searchRequest,
												"id", getActivity().getPackageName())));
						toBeScrolled = view.findViewById(getResources().getIdentifier(
								fragmentContainer + searchRequest, "id", getActivity().getPackageName()));
						sHistory.post(scrollRunnable);
						prefs.edit().putString(searchRequestKey, "").apply();
						
					}
					}
					
					for (String b : exploreLuHistoryCulture) {
						if (searchRequest.equals(b)) {
							addFragmentsInActivity(
									getResources().getIdentifier(
											fragmentContainer + "culturelandmarks", "id",
											getActivity().getPackageName()), getNewFragment("culturelandmarks"),
									(Button) view.findViewById(getResources()
											.getIdentifier(button + "culturelandmarks", "id",
													getActivity().getPackageName())));
							toBeScrolled = view.findViewById(R.id.fragment_container_explore_luzern_history_culturelandmarks);
							clickedCulture = true;
							Log.e("searchRequestHistory in Culture: ", searchRequest);
						} 
					}
					 
				}
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	
	@Override
	public void onStop() {
	    super.onStop();
	    Log.i("onStop is called", "onStop");
	    clickedHistory = false;
	    clickedCulture = false;
	} 

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Button btnHistoryHistory = (Button) v.findViewById(R.id.explore_luzern_btn_history);
		Button btnHistoryCulture = (Button) v.findViewById(R.id.explore_luzern_btn_culturelandmarks);
		
		switch (v.getId()) {

		case R.id.explore_luzern_layout_history:

			if (clickedHistory) {
				hideFragment(R.id.fragment_container_explore_luzern_history_history,
						btnHistoryHistory);
			} else {
				addFragmentsInActivity(R.id.fragment_container_explore_luzern_history_history,
						new ExploreLuHistoryHistoryFragment(), btnHistoryHistory);
			}
			clickedHistory = true;
			break;
			
		case R.id.explore_luzern_layout_culturelandmarks:

			if (clickedCulture) {
				hideFragment(R.id.fragment_container_explore_luzern_history_culturelandmarks,
						btnHistoryCulture);
			} else {
				addFragmentsInActivity(R.id.fragment_container_explore_luzern_history_culturelandmarks,
						new ExploreLuHistoryCultureFragment(), btnHistoryCulture);
			}
			clickedCulture = true;
			break;
		}
		
	}
	
	public void addFragmentsInActivity(int id, Fragment fragment, Button button) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(id, fragment);
		fragmentTransaction.commit();
		button.setBackgroundResource(R.drawable.details_btn_active);
	}
	
	public void hideFragment(int id, Button button) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		Fragment fragment = fragmentManager.findFragmentById(id);

		if (fragment.isHidden()) {
			fragmentTransaction.show(fragment);
			button.setBackgroundResource(R.drawable.details_btn_active);
		} else {
			fragmentTransaction.hide(fragment);
			button.setBackgroundResource(R.drawable.details_btn_inactive);
		}
		fragmentTransaction.commit();

	}
	
	public Fragment getNewFragment(String fragment) {
		Fragment next = null;
		switch (fragment) {
		case "history":
			next = new ExploreLuHistoryHistoryFragment();
			break;
		case "culturelandmarks":
			next = new ExploreLuHistoryCultureFragment();
			break;
		}
		return next;
	}
	
	Runnable scrollRunnable = new Runnable() {

		@SuppressLint("NewApi")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int plusScreen = (int) ((sHistory.getHeight()) / 1.82);
				scrollToValue = toBeScrolled.getTop() + plusScreen;
				Log.e("toBeScrolled1: ", "" + toBeScrolled.getTop());
				Log.e("plusScreen: ", "" + plusScreen);
			sHistory.scrollTo(0, scrollToValue);
			Log.e("Scroll to in runnable:", "" + scrollToValue);

		}
	};
	
	}
	
	
