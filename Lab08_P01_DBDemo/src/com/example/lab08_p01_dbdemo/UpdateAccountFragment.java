package com.example.lab08_p01_dbdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UpdateAccountFragment extends Fragment {

	Context context;
	
	public UpdateAccountFragment(Context context) {
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.updateaccount, container, false);
	}
}
