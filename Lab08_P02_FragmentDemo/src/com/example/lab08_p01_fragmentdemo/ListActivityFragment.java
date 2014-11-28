package com.example.lab08_p01_fragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivityFragment extends Fragment {

	Context c;
	
	public ListActivityFragment() {}
	
	public ListActivityFragment(Context c) {
		this.c = c;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		int n = 5;
		Student[] a = new Student[n];
		for (int i = 0; i < n; i++) {
			a[i] = new Student(123 + i, "Name " + i, 55 + 3 * i, "DH " + i);
		}
		
		ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(c, android.R.layout.simple_list_item_checked);
		ListView listView = (ListView) getView().findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
	}
}
