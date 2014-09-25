package com.example.walrus;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestFragment extends ListFragment
{
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		ArrayList<String> values = new ArrayList<String>();
		String[] xmlStrings = getResources().getStringArray(R.array.my_strings);
		for(int x = 0; x < xmlStrings.length; x++)
		{
			values.add(xmlStrings[x]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
		
		setListAdapter(adapter);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_test, container, false);
	}
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		
	}
}
