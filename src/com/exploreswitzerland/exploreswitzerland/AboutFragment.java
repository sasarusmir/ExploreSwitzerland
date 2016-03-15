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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class AboutFragment extends Fragment implements OnClickListener {

	public static boolean clickedClimate = false;
	public static boolean clickedPolitics = false;
	public static boolean clickedCulture = false;
	public static boolean clickedEconomy = false;
	public static boolean clickedDialects = false;
	public static boolean clickedMust = false;

   private String packageName = "com.exploreswitzerland.exploreswitzerland";

	private String searchRequestKey;
	private String searchRequest;

	private int scrollToValue;

	public static View toBeScrolled;


	public static ScrollView sAbout;
	private LinearLayout lClimate;
	private LinearLayout lPolitics;
	private LinearLayout lCulture;
	private LinearLayout lEconomy;
	private LinearLayout lDialects;
	private LinearLayout lMustsee;

	private SharedPreferences prefs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_about, container,
				false);

		return rootView;
	}

	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		lClimate = (LinearLayout) view.findViewById(R.id.about_layout_climate);
		lClimate.setOnClickListener(this);

		lPolitics = (LinearLayout) view
				.findViewById(R.id.about_layout_politics);
		lPolitics.setOnClickListener(this);

		lCulture = (LinearLayout) view.findViewById(R.id.about_layout_culture);
		lCulture.setOnClickListener(this);

		lEconomy = (LinearLayout) view.findViewById(R.id.about_layout_economy);
		lEconomy.setOnClickListener(this);

		lDialects = (LinearLayout) view
				.findViewById(R.id.about_layout_dialects);
		lDialects.setOnClickListener(this);

		lMustsee = (LinearLayout) view.findViewById(R.id.about_layout_mustsee);
		lMustsee.setOnClickListener(this);

		sAbout = (ScrollView) view.findViewById(R.id.about_scroll_layout);

//		SearchHelper searchHelper = new SearchHelper(view, getActivity().getApplicationContext());
//		searchHelper.scrollFragment();
		
		// SharedPreferences
		prefs = getActivity().getApplicationContext().getSharedPreferences(
				"com.exploreswitzerland.exploreswitzerland",
				Context.MODE_PRIVATE);
		searchRequestKey = "com.exploreswitzerland.exploreswitzerland.searchRequest";

		searchRequest = prefs.getString(searchRequestKey, null);
		if (searchRequest != null && !searchRequest.equals("")) {
			String fragmentContainer = "fragment_container_about_";
			String button = "about_btn_";
			
			String[] about = { "climate", "politics", "culture", "economy",
					"dialects", "mustsee" };
			String[] aboutDialects = { "basel", "bellinzona", "chur", "bern",
					"geneva", "interlaken", "lausanne", "luzern", "lugano",
					"stgallen", "zurich" };
			String[] aboutMustSee = { "grindelwald", "spiez", "rhinefalls", "zermatt"};
			
			if (searchRequest.equals("culture")) {
				clickedCulture = true;
			}

			else if (searchRequest.equals("climate")) {
				
				clickedClimate = true;
			} else if (searchRequest.equals("politics")) {
				clickedPolitics = true;
			} else if (searchRequest.equals("economy")) {
				clickedEconomy = true;
			} else if (searchRequest.equals("dialects")) {
				clickedDialects = true;
			} else if (searchRequest.equals("mustsee")) {
				clickedMust = true;
			}
			
			for (String a : about) {
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
					sAbout.post(scrollRunnable);
					prefs.edit().putString(searchRequestKey, "").apply();
				}
			}

			for (String b : aboutDialects) {
				if (searchRequest.equals(b)) {
					addFragmentsInActivity(
							getResources().getIdentifier(
									fragmentContainer + "dialects", "id",
									getActivity().getPackageName()), getNewFragment("dialects"),
							(Button) view.findViewById(getResources()
									.getIdentifier(button + "dialects", "id",
											getActivity().getPackageName())));
					toBeScrolled = view.findViewById(R.id.fragment_container_about_dialects);
					clickedDialects = true;
					Log.e("searchRequestDialects in About: ", searchRequest);
				} 
			}

			for (String c : aboutMustSee) {
				if (searchRequest.equals(c)) {
					addFragmentsInActivity(
							getResources().getIdentifier(
									fragmentContainer + "mustsee", "id",
									getActivity().getPackageName()), getNewFragment("mustsee"),
							(Button) view.findViewById(getResources()
									.getIdentifier(button + "mustsee", "id",
											getActivity().getPackageName())));
					toBeScrolled = view.findViewById(R.id.fragment_container_about_mustsee);
					clickedMust = true;

				} 
			}
			

		}

		
	}
	
	@Override
	public void onStop() {
	    super.onStop();
	    Log.i("onStop is called", "onStop");
	    clickedClimate = false;
	    clickedCulture = false;
	    clickedDialects = false;
	    clickedEconomy = false;
	    clickedMust = false;
	    clickedPolitics = false;
	} 
	/*
	@Override
    public void onDestroyView() {
        super.onDestroyView();
clickedClimate = false;
clickedCulture = false;
clickedDialects = false;
clickedEconomy = false;
clickedMust = false;
clickedPolitics = false;
Log.i("onDestroyView is called", "onDestroyView");
	}
	*/

	// public interface MyFragInterface {
	//
	// public void addFragmentsInActivity(int id, Fragment fragment, Button
	// button);
	//
	// public void hideFragment(int id, Button button);
	//
	// }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Button buttonClimate = (Button) v.findViewById(R.id.about_btn_climate);
		Button buttonPolitics = (Button) v
				.findViewById(R.id.about_btn_politics);
		Button buttonCulture = (Button) v.findViewById(R.id.about_btn_culture);
		Button buttonEconomy = (Button) v.findViewById(R.id.about_btn_economy);
		Button buttonDialects = (Button) v
				.findViewById(R.id.about_btn_dialects);
		Button buttonMustsee = (Button) v.findViewById(R.id.about_btn_mustsee);

		switch (v.getId()) {

		case R.id.about_layout_climate:

			if (clickedClimate) {
				hideFragment(R.id.fragment_container_about_climate,
						buttonClimate);
				// clickedClimate = false;
			} else {
				addFragmentsInActivity(R.id.fragment_container_about_climate,
						new AboutClimateFragment(), buttonClimate);

			}
			clickedClimate = true;
			break;

		case R.id.about_layout_politics:

			if (clickedPolitics) {
				hideFragment(R.id.fragment_container_about_politics,
						buttonPolitics);
			} else {
				addFragmentsInActivity(R.id.fragment_container_about_politics,
						new AboutPoliticsFragment(), buttonPolitics);
			}
			clickedPolitics = true;
			break;
		case R.id.about_layout_culture:

			if (clickedCulture) {
				hideFragment(R.id.fragment_container_about_culture,
						buttonCulture);
			} else {
				addFragmentsInActivity(R.id.fragment_container_about_culture,
						new AboutCultureFragment(), buttonCulture);
			}
			clickedCulture = true;
			break;

		case R.id.about_layout_economy:

			if (clickedEconomy) {
				hideFragment(R.id.fragment_container_about_economy,
						buttonEconomy);
				clickedEconomy = false;
			} else {
				addFragmentsInActivity(R.id.fragment_container_about_economy,
						new AboutEconomyFragment(), buttonEconomy);
				clickedEconomy = true;
			}

			break;
		case R.id.about_layout_dialects:

			if (clickedDialects) {
				hideFragment(R.id.fragment_container_about_dialects,
						buttonDialects);
			} else {
				addFragmentsInActivity(R.id.fragment_container_about_dialects,
						new AboutDialectsFragment(), buttonDialects);
			}
			clickedDialects = true;
			break;

		case R.id.about_layout_mustsee:

			if (clickedMust) {
				hideFragment(R.id.fragment_container_about_mustsee,
						buttonMustsee);
			} else {
				addFragmentsInActivity(R.id.fragment_container_about_mustsee,
						new AboutMustseeFragment(), buttonMustsee);
			}
			clickedMust = true;
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

	public void removeFragmentsInActivity(int id, Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
	}

	Runnable scrollRunnable = new Runnable() {

		@SuppressLint("NewApi")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int plusScreen = (int) ((sAbout.getHeight()) / 2.2);
				scrollToValue = toBeScrolled.getTop() + plusScreen;
				Log.e("toBeScrolled1: ", "" + toBeScrolled.getTop());
				Log.e("plusScreen: ", "" + plusScreen);
			sAbout.scrollTo(0, scrollToValue);
			Log.e("Scroll to in runnable:", "" + scrollToValue);

		}
	};

	public Fragment getNewFragment(String fragment) {
		Fragment next = null;
		switch (fragment) {
		case "climate":
			next = new AboutClimateFragment();
			break;
		case "politics":
			next = new AboutPoliticsFragment();
			break;
		case "culture":
			next = new AboutCultureFragment();
			break;
		case "economy":
			next = new AboutEconomyFragment();
			break;
		case "dialects":
			next = new AboutDialectsFragment();
			break;
		case "mustsee":
			next = new AboutMustseeFragment();
			break;
		}
		return next;
	}

}
