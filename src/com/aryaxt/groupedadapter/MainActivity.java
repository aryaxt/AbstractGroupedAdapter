package com.aryaxt.groupedadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView)this.findViewById(R.id.listview);
		
		Map<String, List<Person>> data = new HashMap<String, List<Person>>();
		
		List<Person> aPersons = new ArrayList<Person>();
		aPersons.add(new Person("Aryan"));
		aPersons.add(new Person("Alex"));
		aPersons.add(new Person("Andrew"));
		data.put("A", aPersons);
		
		List<Person> bPersons = new ArrayList<Person>();
		bPersons.add(new Person("Bob"));
		bPersons.add(new Person("Bastard"));
		bPersons.add(new Person("Banana"));
		bPersons.add(new Person("Brandon"));
		data.put("B", bPersons);
		
		MyAdapter adapter = new MyAdapter(this);
		adapter.setData(data);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
