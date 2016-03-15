package com.exploreswitzerland.exploreswitzerland;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchActivity extends ActionBarActivity implements
		OnClickListener {

	MyPageAdapter pageAdapter;
	
	private RelativeLayout aLayout;
	private TextView titleTV;
	private RelativeLayout.LayoutParams layoutText;
	
	private ActionBar actionBar;

	private ListView lv;
	private EditText et;
	private String listview_array[] = { "Economy", "Climate", "Politics",
			"Culture", "must see", "dialects & cities", "Basel", "Bellinzona", 
			"Chur", "Bern", "Geneva", "Interlaken", "Lausanne", "Luzern", "Lugano", "St. Gallen (Sankt Gallen)","Zurich",
			"Grindelwald", "Spiez","Rhine Falls","Zermatt", "Swiss cross", "National flag", "Alphorn", "Saint Bernard dog",
			"Chocolate", "Absinth", "watchmaking", "Swiss Army Knife",
			"Lucerne (Canton)", "History (Lucerne)", "Culture Landmarks (Lucerne)",
			"Chapel Bridge (Lucerne)", "Water Tower (Lucerne)", "Lion Monument (Lucerne)", "Natural Landmarks (Lucerne)",
			"Lake of Lucerne (Lucerne)", "Reuss river", "Mount Pilatus (Lucerne)", "Hiking spots (Lucerne)",
			"Mount Pilatus (Hiking, Lucerne)", "Mount Rigi (Hiking, Lucerne)", "Where to stay (Lucerne)",
			"Renaissance Lucerne Hotel (Lucerne)", "Hotel Alpina (Lucerne)", "The Hotel (Lucerne)", "Cascada Swiss Quality Hotel (Lucerne)"};
	
	private String[] searchAbout = {"Economy", "Climate", "Politics",
			"Culture", "must see", "dialects & cities", "Basel", "Bellinzona", 
			"Chur", "Bern", "Geneva", "Interlaken", "Lausanne", "Luzern", "Lugano", "St. Gallen (Sankt Gallen)","Zurich",
			"Grindelwald", "Spiez","Rhine Falls","Zermatt"};
	
	private String[] searchSymbols = {"Swiss cross", "National flag", "Alphorn", "Saint Bernard dog",
			"Chocolate", "Absinth", "watchmaking", "Swiss Army Knife"};
	
	private String[] searchLuzern = {"Lucerne (Canton)", "History (Lucerne)", "Culture Landmarks (Lucerne)",
			"Chapel Bridge (Lucerne)", "Water Tower (Lucerne)", "Lion Monument (Lucerne)", "Natural Landmarks (Lucerne)",
			"Lake of Lucerne (Lucerne)", "Reuss river", "Mount Pilatus (Lucerne)", "Hiking spots (Lucerne)",
			"Mount Pilatus (Hiking, Lucerne)", "Mount Rigi (Hiking, Lucerne)", "Where to stay (Lucerne)",
			"Renaissance Lucerne Hotel (Lucerne)", "Hotel Alpina (Lucerne)", "The Hotel (Lucerne)", "Cascada Swiss Quality Hotel (Lucerne)"};
	
	private ArrayList<String> array_sort = new ArrayList<String>();
	int textlength = 0;

	String searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		// Fragments and ViewPager Initialization
		actionBar = getSupportActionBar();
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.action_bar_title, null);

		setUpTitle(v);
		
		LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setCustomView(v, layout);

		 actionBar.setHomeAsUpIndicator(R.drawable.back_button_xml);
//		actionBar.setDisplayHomeAsUpEnabled(true);

		lv = (ListView) findViewById(R.id.ListView01);
		et = (EditText) findViewById(R.id.search_edit_text);
		// lv.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, listview_array));

		lv.setBackgroundColor(getResources().getColor(R.color.white));

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				 String item = (String) parent.getItemAtPosition(position);
				/*
				 * et.setText(item); et.setCursorVisible(false);
				 */

				// SharedPreferences
				prefs = getApplicationContext().getSharedPreferences(
						"com.exploreswitzerland.exploreswitzerland",
						Context.MODE_PRIVATE);

//				switch (item) {
//				case "dialects & cities":
//					item=item.substring(0,8);
//					startSearchedActivity(item);
//					break;
//				default:
////					et.setText(item);
//					startSearchedActivity(item);
//					break;
//				}
				
				for(String a : searchAbout){
					if (item.equals("dialects & cities")){
						item=item.substring(0,8);
						startSearchedActivity(item);
					}else if(item.equals(a)){
						startSearchedActivity(item);
					}
				}
				
				for (String b : searchSymbols){
					if (item.equals(b))
						startSearchedActivitySymbols(item);
					
				}
				
				for (String c : searchLuzern){
					if (item.equals(c))
						startSearchedActivityLucerne(item);
				}

			}

		});

		et.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				// Abstract Method of TextWatcher Interface.
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Abstract Method of TextWatcher Interface.
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				textlength = et.getText().length();
				array_sort.clear();
				for (int i = 0; i < listview_array.length; i++) {
					if (textlength <= listview_array[i].length()) {
						if (et.getText()
								.toString()
								.equalsIgnoreCase(
										listview_array[i].substring(0,
												textlength))) {
							array_sort.add(listview_array[i]);
						}
					}
				}
				if (textlength == 0) {
					array_sort.clear();
				}
				lv.setAdapter(new ArrayAdapter<String>(SearchActivity.this,
						android.R.layout.simple_list_item_1, array_sort));
			}
		});
		et.setOnClickListener(this);

	}

	public void openTab(View view) {

		int pos = 0;

		if (view.getId() == R.id.search_open_explore) {
			pos = 0;
		} else if (view.getId() == R.id.search_open_about) {
			pos = 1;
		} else {
			pos = 2;
		}

		Intent intent = new Intent(this, TabLayoutActivity.class);
		intent.putExtra("PosNr", (int) pos);
		startActivity(intent);
	}

	public void openSearch(View view) {
		onSearchRequested();
	}

	@Override
	public void onClick(View v) {
		et.setCursorVisible(true);
	}

	@SuppressLint("NewApi")
	public void startSearchedActivity(String item) {
		if(item.equals("St. Gallen (Sankt Gallen)")){
			prefs.edit().putString(searchRequestKey, "stgallen").apply();
		}else{
		prefs.edit().putString(searchRequestKey, item.replace(" ", "").toLowerCase()).apply();
		}
		Intent intent = new Intent(getApplicationContext(),
				TabLayoutActivity.class);
		intent.putExtra("PosNr", (int) 1);
		startActivity(intent);
	}
	
	@SuppressLint("NewApi")
	public void startSearchedActivitySymbols(String item){
		prefs.edit().putString(searchRequestKey, item.replace(" ", "").toLowerCase()).apply();
		Intent intent = new Intent(getApplicationContext(),
				TabLayoutActivity.class);
		intent.putExtra("PosNr", (int) 2);
		startActivity(intent);
	}

	@SuppressLint("NewApi")
	public void startSearchedActivityLucerne(String item){
		String itemString = item;
		if(item.equals("Mount Pilatus (Hiking, Lucerne)"))
			itemString = "mountpilatushi";
		else if(item.endsWith(" (Lucerne)"))
			itemString = item.replace(" (Lucerne)", "");
		else if (item.endsWith(" (Hiking, Lucerne)"))
			itemString = item.replace(" (Hiking, Lucerne)", "");
		else if (item.equals("Lucerne (Canton)"))
			itemString = "";
		
		Log.e("searchRequest Search", itemString);
		prefs.edit().putString(searchRequestKey, itemString.replace(" ", "").toLowerCase()).apply();
		Intent intent = new Intent(getApplicationContext(),
				ExploreLuzernActivity.class);
		intent.putExtra("PosNr", (int) 0);
		startActivity(intent);
	}
	
	public void setUpTitle(View v){
		titleTV = (TextView) v.findViewById(R.id.a_title);
		titleTV.setText(R.string.title_activity_search);
		titleTV.setTextAppearance(getApplicationContext(), android.R.attr.textAppearanceLarge);
		titleTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		layoutText = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		
		aLayout = (RelativeLayout) v.findViewById(R.id.a_layout);
		titleTV.post(placeTitleInMiddle);
		titleTV.setLayoutParams(layoutText);
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