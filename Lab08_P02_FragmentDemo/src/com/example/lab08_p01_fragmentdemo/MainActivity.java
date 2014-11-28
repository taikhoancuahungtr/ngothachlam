package com.example.lab08_p01_fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
public static DetailActivityFragment detail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		boolean dung = getResources().getBoolean(R.bool.dung);
		
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		if(dung) {
			ft.replace(R.id.listFrame, new ListActivityFragment(getBaseContext()));
		} else {
			ft.replace(R.id.listFrame, new ListActivityFragment(getBaseContext()));
			detail = new DetailActivityFragment(null);
			ft.replace(R.id.detailFrame, detail);
		}
		ft.commit();
	}
}
