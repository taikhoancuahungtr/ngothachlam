package com.example.lab16_p01_readjson;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieAdapter extends BaseAdapter {

	Context context;
	List<Movies> listMovies;
	LayoutInflater inflater;
	
	public MovieAdapter(Context context, List<Movies> listMovies) {
		this.context = context;
		this.listMovies = listMovies;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listMovies.size();
	}

	@Override
	public Object getItem(int position) {
		return listMovies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		public TextView txtTitle;
		public ImageView iv;
		public TextView txtRate;
		public TextView txtYear;
		public TextView txtGenre;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder;
		if(convertView == null) {
			row = inflater.inflate(R.layout.row, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
			holder.iv = (ImageView) row.findViewById(R.id.imageView1);
			holder.txtRate = (TextView) row.findViewById(R.id.txtRate);
			holder.txtYear = (TextView) row.findViewById(R.id.txtYear);
			holder.txtGenre = (TextView) row.findViewById(R.id.txtGenre);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		if(listMovies.size() > 0) {
			Movies movies = listMovies.get(position);
			holder.txtTitle.setText(movies.getTitle());
			new DownloadImageTask(holder.iv).execute(movies.getImageUrl());
			holder.txtRate.setText(movies.getRating() + "");
			holder.txtYear.setText(movies.getReleaseYear() + "");
			holder.txtGenre.setText(movies.getGenre());
		} else {
			holder.txtTitle.setText("No Data");
		}
		
		return row;
	}
}
