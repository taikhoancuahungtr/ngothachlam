package com.example.lab08_p01_dbdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Session.isAddnew = false;
				start();
			}
		});
	}
	
	private void start() {
		Intent i = new Intent(this, ManageAccountActivity.class);
		startActivity(i);
	}
	
	public void addnew(View v) {
		Session.isAddnew = true;
		start();
	}
}
