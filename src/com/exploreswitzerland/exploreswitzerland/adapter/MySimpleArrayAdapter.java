package com.exploreswitzerland.exploreswitzerland.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exploreswitzerland.exploreswitzerland.R;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MySimpleArrayAdapter(Context context, String[] values) {
		super(context, R.layout.list_item_custom, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_item_custom, parent,
				false);
		TextView textViewLabel = (TextView) rowView
				.findViewById(R.id.list_label);
		TextView textViewDescription = (TextView) rowView
				.findViewById(R.id.list_capital);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.list_icon);
		textViewLabel.setText(values[position]);
		
		
		String s = values[position];
		if (s.startsWith("ZÜRICH")) {
			imageView.setImageResource(R.drawable.explore_list_view_a);
			textViewDescription.setText(" | Capital: Zürich");
		} else if (s.startsWith("BERN")) {
			imageView.setImageResource(R.drawable.explore_list_view_b);
			textViewDescription.setText(" | Capital: Bern");
		} else if (s.startsWith("LUCERNE")) {
			imageView.setImageResource(R.drawable.explore_list_view_c);
			textViewDescription.setText(" | Capital: Lucerne");
		} else if (s.startsWith("URI")) {
			imageView.setImageResource(R.drawable.explore_list_view_d);
			textViewDescription.setText(" | Capital: Altdorf");
		} else if (s.startsWith("SCHWYZ")) {
			imageView.setImageResource(R.drawable.explore_list_view_e);
			textViewDescription.setText(" | Capital: Schwyz");
		} else if (s.startsWith("OBWALDEN")) {
			imageView.setImageResource(R.drawable.explore_list_view_f);
			textViewDescription.setText(" | Capital: Sarnen");
		} else if (s.startsWith("NIDWALDEN")) {
			imageView.setImageResource(R.drawable.explore_list_view_g);
			textViewDescription.setText(" | Capital: Stans");
		} else if (s.startsWith("GLARUS")) {
			imageView.setImageResource(R.drawable.explore_list_view_h);
			textViewDescription.setText(" | Capital: Glarus");
		} else {
			imageView.setImageResource(R.drawable.explore_list_view_i);
			textViewDescription.setText(" | Capital: Zug");
		}

		return rowView;
	}
}
