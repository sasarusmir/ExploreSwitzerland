package com.exploreswitzerland.exploreswitzerland;

import java.util.logging.Logger;

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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ExploreLuDefaultFragment extends Fragment {

	private SharedPreferences prefs;

	private String searchRequestKey;
	private String searchRequest;

	Fragment historyFragment = new ExploreLuHistoryFragment();
	Fragment landmarksFragment = new ExploreLuLandmarksFragment();
	Fragment hikingFragment = new ExploreLuHikingFragment();
	Fragment stayFragment = new ExploreLuStayFragment();

	public static FrameLayout fLuzern;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_explore_lu_default,
				container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		//
		// LayoutInflater inflater =
		// (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//
		//
		// View v = (LinearLayout)inflater.inflate(R.layout.fragment_explore_lu,
		// null);
		//
		// final Button bHistory = (Button) v
		// .findViewById(R.id.custom_nav_button_history);
		// final Button bLandmarks = (Button) v
		// .findViewById(R.id.custom_nav_button_landmarks);
		//
		// final Button bHiking = (Button) v
		// .findViewById(R.id.custom_nav_button_hiking);
		//
		// final Button bStay = (Button)
		// v.findViewById(R.id.custom_nav_button_stay);
		//
		final Button bDef = (Button) view
				.findViewById(R.id.explore_luzern_btn_basic);

		// bHistory.setBackgroundResource(R.drawable.explore_nav_a);
		// bLandmarks.setBackgroundResource(R.drawable.explore_nav_b_active);
		// bHiking.setBackgroundResource(R.drawable.explore_nav_c_active);
		// bStay.setBackgroundResource(R.drawable.explore_nav_d);
		bDef.setBackgroundResource(R.drawable.explore_basic_info_btn_active);
		
		fLuzern = (FrameLayout) getActivity().findViewById(R.id.fragment_container_explore_luzern);
		
		// SharedPreferences
		prefs = getActivity().getApplicationContext().getSharedPreferences(
				"com.exploreswitzerland.exploreswitzerland",
				Context.MODE_PRIVATE);
		searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

		searchRequest = prefs.getString(searchRequestKey, null);
		if (searchRequest != null && !searchRequest.equals("")) {
			
			String button = "about_btn_";

			String[] luzernCulture = { "history", "culturelandmarks",
					"chapelbridge", "watertower", "lionmonument" };
			String[] luzernLandmarks = { "naturallandmarks",
					"lakeoflucerne", "reussriver", "mountpilatus"};
			String[] luzernHiking = { "hikingspots",
					"mountpilatushi", "mountrigi"};
			String[] luzernStay = {"wheretostay",
					"renaissancelucernehotel", 
					"hotelalpina", "thehotel", "cascadaswissqualityhotel"};

//			TextView buttonHistory = (TextView) findViewById(R.id.custom_nav_button_history);

			for (String a : luzernCulture) {
				if (searchRequest.equals(a)) {
					Log.e("SearchRequest in Default", searchRequest);
					 replaceFragment(R.id.fragment_container_explore_luzern,
					 historyFragment);
				}
			}
			
			for (String b : luzernLandmarks) {
				if (searchRequest.equals(b)) {
					Log.e("SearchRequest in Default L", searchRequest);
					 replaceFragment(R.id.fragment_container_explore_luzern,
					 landmarksFragment);
					
				}
			}
			
			for (String c : luzernHiking) {
				if (searchRequest.equals(c)) {
					Log.e("SearchRequest in Default H", searchRequest);
					 replaceFragment(R.id.fragment_container_explore_luzern,
					 hikingFragment);
					
				}
			}
			
			for (String d : luzernStay) {
				if (searchRequest.equals(d)) {
					Log.e("SearchRequest in Default S", searchRequest);
					 replaceFragment(R.id.fragment_container_explore_luzern,
					 stayFragment);
					
				}
			}

		}

		super.onViewCreated(view, savedInstanceState);
	}
	
	@SuppressLint("NewApi")
	private void replaceFragment(int fragmentContainer,
			Fragment fragment) {
//		prefs.edit().putString(searchRequestKey, "").apply();
		FragmentManager fManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fTransaction = fManager.beginTransaction();
		
		fTransaction.replace(fragmentContainer, fragment);
		fTransaction.commit();
		
	}
	

}
