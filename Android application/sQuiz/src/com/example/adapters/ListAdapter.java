package com.example.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.squiz.R;

public class ListAdapter<T> extends ArrayAdapter<T> {
	private Context context;
	private List<T> list;

	public ListAdapter(Context context, int resource,
			List<T> objects) {
		super(context, resource, objects);
		this.context = context;
		this.list = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.custom_list_item, parent, false);
		
		TextView tv = (TextView) view.findViewById(R.id.list_text);
		T item = list.get(position);
		tv.setText(item.toString());
		return view;
	}
	

}
