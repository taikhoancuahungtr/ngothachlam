package com.example.lab09_p01_dbdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UpdateAccountFragment extends Fragment {

	Context context;
	Account a;
	
	public UpdateAccountFragment(Context context, Account a) {
		this.context = context;
		this.a = a;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.updateaccount, container, false);
	}
	
	EditText txtID;
	EditText txtUsername;
	EditText txtPass;
	EditText txtConfirm;
	
	@Override
	public void onStart() {
		super.onStart();
		
		txtID = (EditText) getView().findViewById(R.id.txtID);
		txtUsername = (EditText) getView().findViewById(R.id.txtUsername);
		txtPass = (EditText) getView().findViewById(R.id.txtPass);
		txtConfirm = (EditText) getView().findViewById(R.id.txtConfirm);
		
		txtID.setText(a.getId() + "");
		txtUsername.setText(a.getUsername());
		txtPass.setText(a.getPassword());
		txtConfirm.setText(a.getPassword());
		
		Button btnUpdate = (Button) getView().findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBAdapter db = new DBAdapter(context);
				db.open();
				int id = a.getId();
				db.updateAccount(id, txtUsername.getText().toString(), txtPass.getText().toString(), "");
				db.close();
				getFragmentManager().popBackStackImmediate();
			}
		});
	}
}
