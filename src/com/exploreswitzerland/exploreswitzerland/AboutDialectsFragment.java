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
import android.widget.ScrollView;
import android.widget.TextView;

public class AboutDialectsFragment extends Fragment{
	
	private int scrollToValue = 0;
	private View imageView;
	
	private SharedPreferences prefs;
	private String searchRequestKey;
	private String searchRequest;
	
	private String packageName = "com.exploreswitzerland.exploreswitzerland";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		
		View rootView = inflater.inflate(R.layout.fragment_about_dialects, container, false);
		
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
		Log.e("searchRequestDialects: ", searchRequest);
		imageView = (ImageView) view.findViewById(getResources().getIdentifier(
				imageViewRes + searchRequest, "id", packageName));
		imageView.post(getImageToScroll);
		prefs.edit().putString(searchRequestKey, "").apply();
				}
				
TextView textViewBasel = (TextView) view.findViewById(R.id.about_text_basel);
TextView textViewBellinzona = (TextView) view.findViewById(R.id.about_text_bellinzona);
TextView textViewChur = (TextView) view.findViewById(R.id.about_text_chur);
TextView textViewBern = (TextView) view.findViewById(R.id.about_text_bern);
TextView textViewGeneva = (TextView) view.findViewById(R.id.about_text_geneva);
TextView textViewInterlaken = (TextView) view.findViewById(R.id.about_text_interlaken);
TextView textViewLausanne = (TextView) view.findViewById(R.id.about_text_lausanne);
TextView textViewLuzern = (TextView) view.findViewById(R.id.about_text_luzern);
TextView textViewLugano = (TextView) view.findViewById(R.id.about_text_lugano);
TextView textViewStgallen = (TextView) view.findViewById(R.id.about_text_stgallen);
TextView textViewZurich = (TextView) view.findViewById(R.id.about_text_zurich);


		
		setColor(textViewBasel, getResources().getString(R.string.about_text_dialects_basel), "Basel", getResources().getColor(R.color.RedExplore));
		setColor(textViewBellinzona, getResources().getString(R.string.about_text_dialects_bellinzona), "Bellinzona", getResources().getColor(R.color.RedExplore));
		setColor(textViewChur, getResources().getString(R.string.about_text_dialects_chur), "Chur", getResources().getColor(R.color.RedExplore));
		setColor(textViewBern, getResources().getString(R.string.about_text_dialects_bern), "Bern", getResources().getColor(R.color.RedExplore));
		setColor(textViewGeneva, getResources().getString(R.string.about_text_dialects_geneva), "Geneva", getResources().getColor(R.color.RedExplore));
		setColor(textViewInterlaken, getResources().getString(R.string.about_text_dialects_interlaken), "Interlaken", getResources().getColor(R.color.RedExplore));
		setColor(textViewLausanne, getResources().getString(R.string.about_text_dialects_lausanne), "Lausanne", getResources().getColor(R.color.RedExplore));
		setColor(textViewLuzern, getResources().getString(R.string.about_text_dialects_luzern), "Luzern", getResources().getColor(R.color.RedExplore));
		setColor(textViewLugano, getResources().getString(R.string.about_text_dialects_lugano), "Lugano", getResources().getColor(R.color.RedExplore));
		setColor(textViewStgallen, getResources().getString(R.string.about_text_dialects_stgallen), "St. Gallen", getResources().getColor(R.color.RedExplore));
		setColor(textViewZurich, getResources().getString(R.string.about_text_dialects_zurich), "Zurich", getResources().getColor(R.color.RedExplore));
		
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	public void setColor(TextView view, String fulltext, String subtext,
			int color) {
		view.setText(fulltext, TextView.BufferType.SPANNABLE);
	      Spannable str = (Spannable) view.getText();
	      int i = fulltext.indexOf(subtext);
	      str.setSpan(new ForegroundColorSpan(color), i, i+subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
	}
	
	public Runnable getImageToScroll = new Runnable() {
		
		@Override
		public void run() {
			int plusScreen = (int) ((AboutFragment.sAbout.getHeight()) / 2.1);
			
				scrollToValue = AboutFragment.toBeScrolled.getTop() + imageView.getTop()
						+ plusScreen;

				AboutFragment.sAbout.scrollTo(0, scrollToValue);
		}
	};
	
}
