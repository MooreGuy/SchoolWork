package com.guywmoorewalrus;

import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TitleFragment extends Fragment
{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
								Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_activity_title, container,false);
	}
}