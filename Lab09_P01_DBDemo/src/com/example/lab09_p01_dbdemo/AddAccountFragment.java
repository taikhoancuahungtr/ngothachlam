package com.example.lab09_p01_dbdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddAccountFragment extends Fragment {

	Context context;
	
	public AddAccountFragment(Context context) {
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.addaccount, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		Button btnAdd = (Button) getView().findViewById(R.id.btnUpdate);
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBAdapter db = new DBAdapter(context);
				db.open();
				EditText txtUsername = (EditText) getView().findViewById(R.id.txtUsername);
				EditText txtPass = (EditText) getView().findViewById(R.id.txtPass);
				db.insertAccount(txtUsername.getText().toString(), txtPass.getText().toString(), "");
				db.close();
				getFragmentManager().popBackStackImmediate();
			}
		});
	}
}
