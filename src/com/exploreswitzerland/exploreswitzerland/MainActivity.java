package com.exploreswitzerland.exploreswitzerland;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	public final static String EXTRA_PAGE = "com.exploreswitzerland.exploreswitzerland.PAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowCustomEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);

		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.action_bar_title, null);

		TextView titleTV = (TextView) v.findViewById(R.id.a_title);
		Typeface font = Typeface.createFromAsset(getAssets(), "Lobster.ttf");
		titleTV.setTypeface(font);
		LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		ab.setCustomView(v, layout);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	/**
	 * Open a new SearchActivity intent
	 * */
	public void openSearch(View view) {
		Intent intent = new Intent(this, SearchActivity.class);
//		Intent intent = new Intent(this, ListViewSearchExample.class);
		startActivity(intent);
		
	}

	public void openExplore(View view) {
		Intent intent = new Intent(this, TabLayoutActivity.class);
		intent.putExtra("PosNr", (int)0);
		startActivity(intent);
	}

	public void openAbout(View view) {

		Intent intent = new Intent(this, TabLayoutActivity.class);
		intent.putExtra("PosNr", (int)1);
		startActivity(intent);
	}

	public void openSymbols(View view) {

		Intent intent = new Intent(this, TabLayoutActivity.class);
		intent.putExtra("PosNr", (int)2);
		startActivity(intent);
	}

}
