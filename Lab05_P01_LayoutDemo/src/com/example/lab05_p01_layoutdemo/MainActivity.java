package com.example.lab05_p01_layoutdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	public void startGame(View v) {
		txtTimeOut = (EditText) findViewById(R.id.txtTimeOut);
		txtTimeOut.setEnabled(false);
		timeOut = Integer.parseInt(txtTimeOut.getText().toString());

		Handler countDown = new Handler() {
			int t = timeOut;
			public void handleMessage(android.os.Message msg) {
				if(t == -1) {
					EditText txtResult = (EditText) findViewById(R.id.txtResult);
					txtResult.setEnabled(false);
					Button btnOK = (Button) findViewById(R.id.btnOK);
					btnOK.setEnabled(false);
					return;
				}
				txtTimeOut.setText(t-- + "");
				this.sendEmptyMessageDelayed(0, 1000);
			}
		};
		
		countDown.sendEmptyMessage(0);
	}
	
	int timeOut;
	EditText txtTimeOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamedemo);
		
		
		
		// Demo gridlayout
//		int n = 4;
//		int m = 5;
//		GridLayout gridLayout = (GridLayout) findViewById(R.id.GridLayout1);
//		gridLayout.setColumnCount(n);
//		gridLayout.setRowCount(m);
//		
//		for (int i = 0; i < n * m; i++) {
//			ImageButton imageButton = new ImageButton(getBaseContext());
//			imageButton.setImageResource(R.drawable.ic_launcher);
//			gridLayout.addView(imageButton);
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
