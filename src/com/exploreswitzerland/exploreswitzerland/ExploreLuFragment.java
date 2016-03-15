package com.exploreswitzerland.exploreswitzerland;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ExploreLuFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_explore_lu,
				container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

//		final Button bHistory = (Button) view
//				.findViewById(R.id.custom_nav_button_history);
//		final Button bLandmarks = (Button) view
//				.findViewById(R.id.custom_nav_button_landmarks);
//
//		final Button bHiking = (Button) view
//				.findViewById(R.id.custom_nav_button_hiking);
//
//		final Button bStay = (Button) view
//				.findViewById(R.id.custom_nav_button_stay);
//
		final Fragment fragmentDefault = new ExploreLuDefaultFragment();
//
////		bHistory.setOnClickListener(this);
//		bLandmarks.setOnClickListener(this);
//		bHiking.setOnClickListener(this);
//		bStay.setOnClickListener(this);

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fragmentTransaction.add(R.id.fragment_container_explore_luzern,
				fragmentDefault);

		fragmentTransaction.commit();

		super.onViewCreated(view, savedInstanceState);
	}

//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//
//		Fragment historyFragment = new ExploreLuHistoryFragment();
//		Fragment landmarksFragment = new ExploreLuLandmarksFragment();
//		Fragment hikingFragment = new ExploreLuHikingFragment();
//		Fragment stayFragment = new ExploreLuStayFragment();
//
//		FragmentManager fragmentManager = getFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager
//				.beginTransaction();
//
//		switch (v.getId()) {
//
////		case R.id.custom_nav_button_history:
////			
////			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
////					historyFragment);
////			fragmentTransaction.addToBackStack(null);
////
////			fragmentTransaction.commit();
////			break;
//
//		case R.id.custom_nav_button_landmarks:
//			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
//					landmarksFragment);
//			fragmentTransaction.addToBackStack(null);
//
//			fragmentTransaction.commit();
//			break;
//
//		case R.id.custom_nav_button_hiking:
//			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
//					hikingFragment);
//			fragmentTransaction.addToBackStack(null);
//
//			fragmentTransaction.commit();
//			break;
//
//		case R.id.custom_nav_button_stay:
//			fragmentTransaction.replace(R.id.fragment_container_explore_luzern,
//					stayFragment);
//			fragmentTransaction.addToBackStack(null);
//
//			fragmentTransaction.commit();
//			break;
//		}
//	}

}
