package com.example.lab12_p01_customlistviewdemo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
	
	public static class ViewHolder {
		public static TextView txtName;
		public static TextView txtAuthor;
		public static TextView txtYear;
		public static TextView txtTime;
		public static CheckBox chkBox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder viewHolder;
		final SongModel song = list.get(position);
		if(convertView == null) {
			viewHolder = new ViewHolder();
			v = inflater.inflate(R.layout.row, parent, false);
			viewHolder.txtName = (TextView) v.findViewById(R.id.txtName);
			viewHolder.txtAuthor = (TextView) v.findViewById(R.id.txtAuthor);
			viewHolder.txtYear = (TextView) v.findViewById(R.id.txtYear);
			viewHolder.txtTime = (TextView) v.findViewById(R.id.txtTime);
			viewHolder.chkBox = (CheckBox) v.findViewById(R.id.chkBox);
			viewHolder.chkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					song.setSelected(isChecked);
				}
			});
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}

		if(list.size() > 0) {
			viewHolder.txtName.setText(song.getName());
			viewHolder.txtAuthor.setText(song.getAuthor());
			viewHolder.txtYear.setText(song.getYear() + "");
			viewHolder.txtTime.setText(song.getTime());
		} else {
			viewHolder.txtName.setText("No Data");
		}
		
		return v;
	}
}
