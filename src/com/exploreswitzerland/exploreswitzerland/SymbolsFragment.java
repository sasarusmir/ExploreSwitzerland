package com.exploreswitzerland.exploreswitzerland;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SymbolsFragment extends Fragment {
	
	private SharedPreferences prefs;
	
	private String searchRequestKey;
	private String searchRequest;
	
	private int scrollToValue;
	
	private ScrollView sSymbols;
	private View toBeScrolled;
	
	private String[] searchSymbols = {"swisscross", "nationalflag", "alphorn", "saintbernarddog",
			"chocolate", "absinth", "watchmaking", "swissarmyknife"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_symbols, container, false);
		/*
DatabaseHandler db = new DatabaseHandler(getActivity());

		Log.d("Insert: ", "Inserting ..");
		db.addText(new Text("SWISS CRO", "The Swiss Cross, as it is often called by the native population, is a generalization of the coat of arms of canton Schwyz, one of the three founding members of the Swiss confederation back in 1291.\n \n Schwyz apparently played a leading role in the early days, so its name soon was being used for the confederacy as a whole. When the old Swiss went to battles, the soldiers of each federal state had their own style of clothing and carried their own cantonal banner with them. As the confederation grew, they needed a common symbol to recognize friend from foe and applied white stripes in the form of a cross on their clothes and helmets.\n \n During the middle ages, Switzerland was first part of Charlemagne\'s great continental European empire (around A.D. 800). His sons split the empire in three parts, the eastern part (including Italy, the Alps and Germany) was called Holy Roman Empire of German Nation.\n\n When the German emperor once went to Italy around 1230, soldiers from Schwyz accompanied him. It is reported that he was very pleased about the support they gave him and granted them the privilege to add a white cross to their red banner.\n \n An old legend says that one of the last Roman emperors, Constantin, used a banner with a cross symbol when he defeated his rival Maxentius in 312. It is very likely that the cross symbol was taken over by Charlemagne and his successors as a sign of their leadership over the \"Christian continent\""));
		db.addText(new Text("Proba2", "Proba neki novi još malo duži veeliki text..ne znam koliko je ograniceno"));
		db.addText(new Text("Proba3", "Proba neki veeliki 3 text..ne znam koliko je ograniceno"));
		
		Log.d("Reading: ", "Reading all texts ..");
		List<Text> texts = db.getAllTexts();
		
		Text texttitle1 = db.getText(19);
		
		String titletext = texttitle1.get_title();
		String texttext = texttitle1.get_text();
		
		String log = "Id: "+texttitle1.get_id()+" ,Title: " + texttitle1.get_title() + " , Text: " + texttitle1.get_text();
		Log.d("Title: ", log);
		
		TextView title = (TextView) rootView.findViewById(R.id.symbols_title_swisscross);
		TextView text = (TextView) rootView.findViewById(R.id.symbols_text_swisscross);
		
		
		
		title.setText(titletext);
		text.setText(texttext);
		
//		for (Text txt : texts) {
//			String log = "Id: "+txt.get_id()+" ,Title: " + txt.get_title() + " , Text: " + txt.get_text();
//			Log.d("Title: ", log);
//			
////			View vS = inflator.inflate(R.layout.fragment_symbols, null);
//			
//			String titletext = txt.get_title();
//			String texttext = txt.get_text();
//			TextView title = (TextView) rootView.findViewById(R.id.symbols_title_swisscross);
//			TextView text = (TextView) rootView.findViewById(R.id.symbols_text_swisscross);
//			
//			
////			TextView titleProba = new TextView(getActivity());
////			
////			titleProba.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
////			((LinearLayout) rootView).addView(titleProba);
////			
////			TextView textProba = new TextView(getActivity());
////			
////			textProba.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
////			((LinearLayout) rootView).addView(textProba);
//			
//			title.setText(texttitle1.get_title());
//			text.setText(texttitle1.get_text());
//		}
		*/
		return rootView;
	}
	
	
	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		String imageView = "symbols_photo_";
		
		sSymbols = (ScrollView) view.findViewById(R.id.symbols_scrollview);
		
		// SharedPreferences
				prefs = getActivity().getApplicationContext().getSharedPreferences(
						"com.exploreswitzerland.exploreswitzerland",
						Context.MODE_PRIVATE);
				searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

				searchRequest = prefs.getString(searchRequestKey, null);
				if (searchRequest != null && !searchRequest.equals("")) {
					for (String a : searchSymbols){
						if (searchRequest.equals(a)){
					if (searchRequest.equals("swisscross")){
						toBeScrolled = null;
					} else if(searchRequest.equals("nationalflag")){
						toBeScrolled = view.findViewById(R.id.symbols_title_flag);
					} else {
						toBeScrolled = view.findViewById(getResources().getIdentifier(imageView+searchRequest, "id", getActivity().getPackageName()));
					}
					
					sSymbols.post(scrollSymbols);
					prefs.edit().putString(searchRequestKey, "").apply();
					
					}
					}
					
				}
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	Runnable scrollSymbols = new Runnable() {
		
		@Override
		public void run() {
			int plusScreen = (int) ((sSymbols.getHeight()) / 2.1);
			if(toBeScrolled != null)
			scrollToValue = toBeScrolled.getTop() + plusScreen;
		sSymbols.scrollTo(0, scrollToValue);
		}
	};
	
}
