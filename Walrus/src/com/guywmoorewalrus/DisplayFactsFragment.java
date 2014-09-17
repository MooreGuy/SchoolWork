package com.guywmoorewalrus;

import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup; 

public class DisplayFactsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater infater, ViewGroup container,
								Bundle savedInstanceState) {
		
		//Inflate the layout for this fragment
		return infater.inflate(R.layout.fragment_display_facts, container, false);
	}
}