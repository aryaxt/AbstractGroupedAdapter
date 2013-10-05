AbstractGroupedAdapter
======================

A simple way to implement grouped ListView on Android

Step 1
------------------
Create 2 xml files 1 representing the header, and another representing the row

Header

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent" 
    android:minHeight="45dp"
    android:background="#FFFFCC" >

    <TextView
        android:id="@+id/txtHeaderTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>
```

Row

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:minHeight="35dp" >

    <TextView
        android:id="@+id/txtPersonName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"  />

</RelativeLayout>
```
Step 2
------------------
Create a new Adapter inheriting from AbstractGroupedAdapter.

AbstractGroupedAdapter takes 2 generic types, the first one represents the object used to populate the header, the second one represents the object that is used to populate each row.
```java
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
```

Step 3
------------------
Create a Map with your data and pass it to the adapter

```java
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
```
