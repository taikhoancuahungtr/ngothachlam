package com.example.lab08_p01_dbdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		ArrayList<Account> listAccount = new ArrayList<Account>();
		DBAdapter db = new DBAdapter(this);
		db.open();
		Cursor c = db.getAllAccount();
		if(c.moveToFirst()) {
			do {
				Account account = new Account(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
				listAccount.add(account);
			} while(c.moveToNext());			
		}
		db.close();
		ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(this, android.R.layout.simple_list_item_checked, listAccount);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		
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
