package com.aryaxt.groupedadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends AbstractGroupedAdapter<String, Person> {

	public MyAdapter(Context context) {
		super(context);
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
	
		if (this.isItemHeader(position)) {
			String header = this.getHeader(position);
			
			view = getReusableView(view, R.layout.header);
			TextView textView = (TextView) view.findViewById(R.id.txtHeaderTitle);
			textView.setText(header);
		}
		else {
			Person person = this.getItem(position);
			
			view = getReusableView(view, R.layout.row);
			TextView textView = (TextView) view.findViewById(R.id.txtPersonName);
			textView.setText(person.getName());
		}

		return view;
	}
}
