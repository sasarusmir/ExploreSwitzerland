package com.exploreswitzerland.exploreswitzerland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutMustseeFragment extends Fragment{

	private int scrollToValue = 0;
	private View imageView;
	
	private SharedPreferences prefs;
	private String searchRequestKey;
	private String searchRequest;
	
	private String packageName = "com.exploreswitzerland.exploreswitzerland";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		
		View rootView = inflater.inflate(R.layout.fragment_about_mustsee, container, false);
		
		
		return rootView;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		
String imageViewRes = "about_photo_";
		
		// SharedPreferences
				prefs = getActivity().getApplicationContext().getSharedPreferences(
						packageName,
						Context.MODE_PRIVATE);
				searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

				searchRequest = prefs.getString(searchRequestKey, null);
				if (searchRequest != null && !searchRequest.equals("")) {
		imageView = (ImageView) view.findViewById(getResources().getIdentifier(
				imageViewRes + searchRequest, "id", packageName));
		imageView.post(getImageToScroll);
		prefs.edit().putString(searchRequestKey, "").apply();
				}

TextView textViewGrindewald = (TextView) view.findViewById(R.id.about_text_grindewald);
TextView textViewSpiez = (TextView) view.findViewById(R.id.about_text_spiez);
TextView textViewRhine = (TextView) view.findViewById(R.id.about_text_rhine);
TextView textViewZermatt = (TextView) view.findViewById(R.id.about_text_zermatt);

		
		setColor(textViewGrindewald, getResources().getString(R.string.about_text_mustsee_grindewald), "Grindelwald", getResources().getColor(R.color.RedExplore));
		setColor(textViewSpiez, getResources().getString(R.string.about_text_mustsee_spiez), "Spiez", getResources().getColor(R.color.RedExplore));
		setColor(textViewRhine, getResources().getString(R.string.about_text_mustsee_rhine), "Rhine Falls", getResources().getColor(R.color.RedExplore));
		setColor(textViewZermatt, getResources().getString(R.string.about_text_mustsee_zermatt), "Zermatt", getResources().getColor(R.color.RedExplore));
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	public void setColor(TextView view, String fulltext, String subtext,
			int color) {
		// TODO Auto-generated method stub
		view.setText(fulltext, TextView.BufferType.SPANNABLE);
	      Spannable str = (Spannable) view.getText();
	      int i = fulltext.indexOf(subtext);
	      str.setSpan(new ForegroundColorSpan(color), i, i+subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
	}
	
public Runnable getImageToScroll = new Runnable() {
		
		@Override
		public void run() {
			int plusScreen = (int) ((AboutFragment.sAbout.getHeight()) / 2.2);
			
				scrollToValue = AboutFragment.toBeScrolled.getTop() + imageView.getTop()
						+ plusScreen;

				AboutFragment.sAbout.scrollTo(0, scrollToValue);
		}
	};
	
}
