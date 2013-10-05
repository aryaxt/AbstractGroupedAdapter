/*  
 *	AbstractGroupedAdapter.java
 *
 * 	Created by Aryan Ghassemi on Oct 5, 2013.
 *	Copyright Aryan Ghassemi. All rights reserved.
 *
 *  https://github.com/aryaxt/AbstractGroupedAdapter
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.aryaxt.groupedadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

public abstract class AbstractGroupedAdapter<H, I> extends ArrayAdapter<Object>
{
	protected Map<H, List<I>> itemsMap;
	protected List<Object> items;
	protected LayoutInflater layoutInflater;
	protected Context context;
	private int count = 0;
	
	public AbstractGroupedAdapter(Context context) {
		super(context, 0);
		
		this.layoutInflater = LayoutInflater.from(context);
		this.context = context;
		this.items = new ArrayList<Object>();
	}
	
	protected View getReusableView(View view, int viewId) {
		if (view != null && view.getId() == viewId)
			return view;
		
		return layoutInflater.inflate(viewId, null);
	}
	
	@SuppressWarnings("unchecked")
    @Override
	public I getItem(int position) {
		return (I) items.get(position);
	}
	
	@SuppressWarnings("unchecked")
    public H getHeader(int position) {
		return (H) items.get(position);
	}
	
	protected boolean isItemHeader(int position) {
		Object object = items.get(position);
		return (itemsMap.keySet().toArray()[0].getClass() == object.getClass()) ? true : false;
	}
	
	@Override
	public int getCount() {
		return count;
	}
	
	@Override
	public boolean areAllItemsEnabled () {
		return false;
	}
	
	@Override
	public boolean isEnabled(int position) {
		return (isItemHeader(position)) ? false : true;
	}
	
	private void populateItemsAndCount() {
		count = 0;
		
		for (H h : itemsMap.keySet()) {
			items.add(h);
			count++;
			
			for (I i : itemsMap.get(h)) {
				items.add(i);
				count++;
			}
		}
	}

    @SuppressLint("NewApi")
    public void setData(Map<H, List<I>> map) {
		this.itemsMap = new HashMap<H, List<I>>(map);
		populateItemsAndCount();
		
		super.clear();
		super.addAll(items);
	}
}
