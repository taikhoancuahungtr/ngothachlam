package com.example.lab08_p01_fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailActivityFragment extends Fragment {

	Student s;
	
	public DetailActivityFragment(Student s) {
		this.s = s;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.detail, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		if(s == null) {
			return;
		}
		TextView txtID = (TextView) getView().findViewById(R.id.txtID);
		TextView txtName = (TextView) getView().findViewById(R.id.txtName);
		TextView txtScore = (TextView) getView().findViewById(R.id.txtScore);
		TextView txtSchool = (TextView) getView().findViewById(R.id.txtSchool);
		txtID.setText(s.getID() + "");
		txtName.setText(s.getName());
		txtScore.setText(s.getScore() + "");
		txtSchool.setText(s.getSchool());
	}
	
	public void callFromListFragment(Student s) {
		this.s = s;
		onStart();
	}
}
