package com.exploreswitzerland.exploreswitzerland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.exploreswitzerland.exploreswitzerland.adapter.MySimpleArrayAdapter;

public class ExploreFragment extends Fragment {
	
	public static Fragment newInstance() {
        ExploreFragment mFrgment = new ExploreFragment();
        return mFrgment;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
		
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		final ListView listview = (ListView) view.findViewById(R.id.listview);
		String [] values = new String[] {"ZÜRICH - ZH", "BERN - BE", "LUCERNE - LU", "URI - UR", "SCHWYZ - SZ", "OBWALDEN - OW",
				"NIDWALDEN - NW", "GLARUS - GL", "ZUG - ZG"};
		
		final ArrayList<String> list = new ArrayList<String>();
		
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    Context exploreContext = getActivity().getApplicationContext();
	    
	    final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(exploreContext, values);
	    listview.setAdapter(adapter);
	    
//	    LayoutInflater inflator = (LayoutInflater) this
//				.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View v = inflator.inflate(R.layout.shadow_view, null);
//	    listview.addHeaderView(v);
//	    listview.addFooterView(new View(getActivity().getApplicationContext()), null, true);
	    
	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    
		@Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
			view.setSelected(true);
	        final String item = (String) parent.getItemAtPosition(position);
	        
	        
	        switch (item) {

			case "LUCERNE - LU":
				Intent intent = new Intent(getActivity().getApplicationContext(), ExploreLuzernActivity.class);
				intent.putExtra("PosNr", (int)0);
				startActivity(intent);
				break;
				
				default:
//					view.animate().setDuration(2000).alpha(0)
//		            .withEndAction(new Runnable() {
//		              @Override
//		              public void run() {
//		                list.remove(item);
//		                adapter.notifyDataSetChanged();
//		                view.setAlpha(1);
//		              }
//		            });
					Toast.makeText(getActivity().getApplicationContext(), "List item "+ item +" clicked",
							   Toast.LENGTH_LONG).show();	
	        }
	        
//	        view.setBackgroundColor(getResources().getColor(R.color.list_bg));
	      }

	    });
		
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	
	private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }

	  }
}
