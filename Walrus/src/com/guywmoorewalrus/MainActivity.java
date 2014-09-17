package com.guywmoorewalrus;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	
	private Button factsButton;
	private Button picsButton;
	private Intent goToFacts;
	private Intent goToPictures;
	private Fragment mainMenu;
	private Fragment title;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.w("Walrus", "we got this far");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainMenu = new Fragment();
		title = new Fragment();
		if(savedInstanceState == null)
		{
			getFragmentManager().beginTransaction().add(R.id.main_activity_container, mainMenu).commit();
			getFragmentManager().beginTransaction().add(R.id.main_activity_container, title).commit();
		}
		
		goToFacts = new Intent(this, DisplayFactsActivity.class);
		goToPictures = new Intent(this, DisplayPicturesActivity.class);
		
		factsButton = (Button) findViewById(R.id.button_walrus_facts);
		picsButton = (Button) findViewById(R.id.button_walrus_pics);
		
		factsButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(goToFacts);
				
			}
		});
		picsButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(goToPictures);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
