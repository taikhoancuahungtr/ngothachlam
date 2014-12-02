package com.example.lab08_p01_dbdemo;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ManageAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manageaccount);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		if(Session.isAddnew) {
			ft.replace(R.id.content, new AddAccountFragment());
		} else {
			ft.replace(R.id.content, new UpdateAccountFragment());
		}
	}
}
