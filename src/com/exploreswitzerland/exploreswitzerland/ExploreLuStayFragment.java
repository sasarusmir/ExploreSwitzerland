package com.exploreswitzerland.exploreswitzerland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ExploreLuStayFragment extends Fragment {
	
	private SharedPreferences prefs;
	private String searchRequestKey;
	private String searchRequest;

	private int scrollToValue;
	
	private ScrollView sStay;

	public TextView textViewToScroll;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_explore_lu_stay, container, false);
		
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
		
		bHistory.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.history_icn, 0, 0);
		bHistory.setBackgroundResource(R.drawable.tab_bg);
		bHistory.setTextColor(getResources().getColor(R.color.white));
		
		bLandmarks.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.naturelandmark_icn, 0, 0);
		bLandmarks.setBackgroundResource(R.drawable.tab_bg);
		bLandmarks.setTextColor(getResources().getColor(R.color.white));
		
		bHiking.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.hiking_icn, 0, 0);
		bHiking.setBackgroundResource(R.drawable.tab_bg);
		bHiking.setTextColor(getResources().getColor(R.color.white));
		
		bStay.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.stay_icn_active, 0, 0);
		bStay.setBackgroundResource(R.drawable.tab_bg_active);
		bStay.setTextColor(getResources().getColor(R.color.RedExplore));
		
		sStay = (ScrollView) view.findViewById(R.id.explore_lu_stay_scrollview);
		// SharedPreferences
				prefs = getActivity().getApplicationContext().getSharedPreferences(
						"com.exploreswitzerland.exploreswitzerland",
						Context.MODE_PRIVATE);
				searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

				searchRequest = prefs.getString(searchRequestKey, null);
				if (searchRequest != null && !searchRequest.equals("")) {
					Log.e("LuHiking ", searchRequest);
					String stay = "explore_lu_stay_title_";
					if(!searchRequest.equals("wheretostay")){
					textViewToScroll = (TextView) view.findViewById(getResources().getIdentifier(
							stay + searchRequest, "id", getActivity().getPackageName()));
					sStay.post(scrollRunnable);}
					prefs.edit().putString(searchRequestKey, "").apply();
				
				}


		super.onViewCreated(view, savedInstanceState);
	}
	
	Runnable scrollRunnable = new Runnable() {

		@SuppressLint("NewApi")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int plusScreen = (int) ((sStay.getHeight()) / 1.6);
			Log.e("plusScreen: ", "" + plusScreen);
				scrollToValue = textViewToScroll.getTop()+plusScreen;
				Log.e("toBeScrolled1: ", "" + textViewToScroll.getTop());
				
			sStay.scrollTo(0, scrollToValue);
			Log.e("Scroll to in runnable:", "" + scrollToValue);

		}
	};
	
	}
	
	
