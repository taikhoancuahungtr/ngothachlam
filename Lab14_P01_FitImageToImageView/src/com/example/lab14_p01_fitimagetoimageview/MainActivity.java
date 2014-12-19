package com.example.lab14_p01_fitimagetoimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void fitImageToImageView(View v) {
		EditText txtRow = (EditText) findViewById(R.id.txtRow);
		EditText txtColumn = (EditText) findViewById(R.id.txtColumn);
		int row = Integer.parseInt(txtRow.getText().toString());
		int column = Integer.parseInt(txtColumn.getText().toString());
		GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
		gridLayout.removeAllViews();
		gridLayout.setRowCount(row);
		gridLayout.setColumnCount(column);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				gridLayout.getWidth() / column, gridLayout.getHeight() / row);
		for (int i = 0; i < row * column; i++) {
			ImageView imageView = new ImageView(getBaseContext());
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setLayoutParams(params);
			imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
			gridLayout.addView(imageView);			
		}
	}
}
