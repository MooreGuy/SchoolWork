package com.guywmoore.walrus;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestFragment extends Fragment
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		
		ListView listView= (ListView) inflater.inflate(R.layout.fragment_test, container, false);
		/*
		String[] myStrings;
		myStrings = getResources().getStringArray(R.array.test_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1 , myStrings);
		ListView list = (ListView)  listView.findViewById(R.id.list1);
		list.setAdapter(adapter);
		*/
		return listView;
	}

}
