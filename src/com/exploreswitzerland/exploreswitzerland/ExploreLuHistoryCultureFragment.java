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
import android.widget.ImageView;

public class ExploreLuHistoryCultureFragment extends Fragment{

	private int scrollToValue = 0;
	private View imageView;
	
	private SharedPreferences prefs;
	private String searchRequestKey;
	private String searchRequest;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		
		View rootView = inflater.inflate(R.layout.fragment_explore_lu_history_culture, container, false);
		
		
		return rootView;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		String imageViewRes = "explore_lu_culture_";
		
		// SharedPreferences
				prefs = getActivity().getApplicationContext().getSharedPreferences(
						getActivity().getPackageName(),
						Context.MODE_PRIVATE);
				searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

				searchRequest = prefs.getString(searchRequestKey, null);
				if (searchRequest != null && !searchRequest.equals("")) {
		imageView = (ImageView) view.findViewById(getResources().getIdentifier(
				imageViewRes + searchRequest, "id", getActivity().getPackageName()));
		imageView.post(getImageToScroll);
		prefs.edit().putString(searchRequestKey, "").apply();
				}
				super.onViewCreated(view, savedInstanceState);
				}
			
public Runnable getImageToScroll = new Runnable() {
		
		@Override
		public void run() {
			int plusScreen = (int) ((ExploreLuHistoryFragment.sHistory.getHeight()) / 1.6);
			
				scrollToValue = ExploreLuHistoryFragment.toBeScrolled.getTop() + imageView.getTop()
						+ plusScreen;

				ExploreLuHistoryFragment.sHistory.scrollTo(0, scrollToValue);
		}
	};
	
}
