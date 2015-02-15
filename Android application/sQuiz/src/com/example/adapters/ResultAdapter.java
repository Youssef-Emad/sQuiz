package com.example.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.Models.Result;
import com.example.squiz.R;

public class ResultAdapter extends ArrayAdapter<Result> {

	private Context context;
	private List<Result> list;
	
	public ResultAdapter(Context context, int resource,
			List<Result> objects) {
		super(context, resource, objects);
		this.context = context;
		this.list = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.table, parent, false);
		
		TextView tv1 = (TextView) view.findViewById(R.id.textView1);
		TextView tv2 = (TextView) view.findViewById(R.id.textView2);
		Result item = list.get(position);
		tv1.setText(item.getStudent());
		if (item.getResult() == -1)
			tv2.setText("");
		else
			tv2.setText(Integer.toString(item.getResult()));
		return view;
	}

}
