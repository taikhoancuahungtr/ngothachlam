package com.example.lab06_p01_persistencedemo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class IODemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iodemo);
		
		
	}
	
	public void readFromFile(View v) {
		TextView textView = (TextView) findViewById(R.id.textView1);
		
		try {
			FileInputStream fIs = openFileInput("ioTest.txt");
			BufferedReader bf = new BufferedReader(new InputStreamReader(fIs));
			String content = "";
			String line = null;
			while((line = bf.readLine()) != null) {
				content += line + "\n";
			}
			textView.setText(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveToFile(View v) {
		EditText editText = (EditText) findViewById(R.id.editText1);
		
		try {
			FileOutputStream fOs = openFileOutput("ioTest.txt", MODE_WORLD_WRITEABLE);
			PrintWriter pw = new PrintWriter(fOs);
			pw.println(editText.getText().toString());
			pw.print("End of file");
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
