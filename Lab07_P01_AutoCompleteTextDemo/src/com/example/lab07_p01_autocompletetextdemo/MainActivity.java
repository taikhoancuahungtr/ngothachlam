package com.example.lab07_p01_autocompletetextdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity {

	private static final String[] COUNTRIES = {"Viet Nam", "Vienne", "Thuy Dien", "Lao", "Campuchia"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), 
				android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		
		AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) 
				findViewById(R.id.autoCompleteTextView1);
		autoCompleteTextView.setAdapter(adapter);
		
		MultiAutoCompleteTextView multiAutoCompleteTextView = 
				(MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
		multiAutoCompleteTextView.setAdapter(adapter);
		multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
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
