package com.example.lab05_p01_layoutdemo;

import java.util.Random;

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
import android.widget.TextView;

public class MainActivity extends Activity {

	Random r = new Random();
	int a, b, diemSo, soCauHoi;
	
	public void pressOK(View v) {
		int result = Integer.parseInt(txtResult.getText().toString());
		if(a + b == result) {
			diemSo++;
		}
		sinhCauHoi();
	}
	
	void sinhCauHoi() {
		a = r.nextInt(50);
		b = r.nextInt(50);
		txtA.setText(a + "");
		txtB.setText(b + "");
		soCauHoi++;
	}
	
	public void startGame(View v) {
		txtResult.setEnabled(true);
		btnOK.setEnabled(true);
		txtTimeOut = (EditText) findViewById(R.id.txtTimeOut);
		txtTimeOut.setEnabled(false);
		timeOut = Integer.parseInt(txtTimeOut.getText().toString());
		
		sinhCauHoi();

		Handler countDown = new Handler() {
			int t = timeOut;
			public void handleMessage(android.os.Message msg) {
				if(t == -1) {
					txtResult.setEnabled(false);
					btnOK.setEnabled(false);
					lblTimeOut.setText("Ket Qua");
					txtTimeOut.setText(diemSo + "/" + soCauHoi + "/" + timeOut);
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
	Button btnOK;
	EditText txtResult;
	TextView txtA, txtB, lblTimeOut;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamedemo);

		txtResult = (EditText) findViewById(R.id.txtResult);
		btnOK = (Button) findViewById(R.id.btnOK);
		lblTimeOut = (TextView) findViewById(R.id.lblTimeOut);
		txtResult.setEnabled(false);
		btnOK.setEnabled(false);
		txtA = (TextView) findViewById(R.id.txtA);
		txtB = (TextView) findViewById(R.id.txtB);
		
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
