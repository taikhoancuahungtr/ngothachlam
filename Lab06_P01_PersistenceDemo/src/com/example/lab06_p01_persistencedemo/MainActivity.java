package com.example.lab06_p01_persistencedemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	EditText editText;
	SeekBar seekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferencedemo);
		
		editText = (EditText) findViewById(R.id.editText1);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				editText.setTextSize(progress);
			}
		});
		
		SharedPreferences sharedPreferences = getSharedPreferences("SateOfMainActivity", MODE_PRIVATE);
		editText.setTextSize(sharedPreferences.getInt("FontSize", 14));
		seekBar.setProgress(sharedPreferences.getInt("FontSize", 14));
		editText.setText(sharedPreferences.getString("Content", "Test"));
	}
	
	public void onSave(View v) {
//		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		SharedPreferences sharedPreferences = getSharedPreferences("SateOfMainActivity", MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString("Content", editText.getText().toString());
		editor.putInt("FontSize", seekBar.getProgress());
		editor.apply();
	}
}
