package com.example.lab09_p01_dbdemo;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListAccountFragment extends Fragment {

	Context context;
	
	public ListAccountFragment(Context context) {
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.listaccount, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		final ArrayList<Account> listAccount = new ArrayList<Account>();
		DBAdapter db = new DBAdapter(context);
		db.open();
		Cursor c = db.getAllAccount();
		if(c.moveToFirst()) {
			do {
				Account account = new Account(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
				listAccount.add(account);
			} while(c.moveToNext());			
		}
		db.close();
		ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(context, 
				android.R.layout.simple_list_item_checked, listAccount);
		
		ListView listView = (ListView) getView().findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
		        ft.replace(R.id.frame, new UpdateAccountFragment(context, listAccount.get(position)));
		        ft.addToBackStack(null);
		        ft.commit();
			}
		});
		
		Button btnAddnew = (Button) getView().findViewById(R.id.btnUpdate);
		btnAddnew.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
		        ft.replace(R.id.frame, new AddAccountFragment(context));
		        ft.addToBackStack(null);
		        ft.commit();
			}
		});
		
		Button btnExit = (Button) getView().findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.exit(0);
			}
		});
	}
}
