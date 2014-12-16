package com.example.lab12_p01_customlistviewdemo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SongAdapter extends BaseAdapter {

	Context context;
	ArrayList<SongModel> list;
	LayoutInflater inflater;
	
	public SongAdapter(Context context, ArrayList<SongModel> list) {
		this.context = context;
		this.list = list;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = inflater.inflate(R.layout.row, parent, false);
		SongModel song = list.get(position);
		TextView txtName = (TextView) row.findViewById(R.id.txtName);
		TextView txtAuthor = (TextView) row.findViewById(R.id.txtAuthor);
		TextView txtYear = (TextView) row.findViewById(R.id.txtYear);
		TextView txtTime = (TextView) row.findViewById(R.id.txtTime);
		txtName.setText(song.getName());
		txtAuthor.setText(song.getAuthor());
		txtYear.setText(song.getYear() + "");
		txtTime.setText(song.getTime());
		
		return row;
	}
}
