package com.guywmoore.walrus;

import android.support.v7.app.ActionBarActivity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity
{

	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState != null)
		{
			return;
		}
		context = (Context)this;
		final Button testButton = (Button) findViewById(R.id.button1);
		SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_high_score), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(null, R.string.saved_high_score);
		testButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Log.i("Walrus", "Hello!");
				SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_high_score), Context.MODE_PRIVATE);
				int highScore = 
				
			}
		});
		/*
		TestFragment testFragment = new TestFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.add(R.id.main_container, testFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
