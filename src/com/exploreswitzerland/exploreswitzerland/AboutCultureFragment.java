package com.exploreswitzerland.exploreswitzerland;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutCultureFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		
		View rootView = inflater.inflate(R.layout.fragment_about_culture, container, false);
		
		
		return rootView;
	}
	
	
	
}
