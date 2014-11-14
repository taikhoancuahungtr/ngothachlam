package com.example.lab04_p01_basicviewdemo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formdangky);

	}
	
	public void submit(View v) {
		EditText txtTaiKhoan = (EditText) findViewById(R.id.txtTaiKhoan);
		String taiKhoan = txtTaiKhoan.getText().toString();
		EditText txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);
		String matKhau = txtMatKhau.getText().toString();
		EditText txtXacNhanMatKhau = (EditText) findViewById(R.id.txtXacNhanMatKhau);
		String xacNhanMatKhau = txtXacNhanMatKhau.getText().toString();
		if(!matKhau.equals(xacNhanMatKhau)) {
			Toast.makeText(getBaseContext(), "Xac nhan mat khau bi sai", Toast.LENGTH_SHORT).show();
			return;
		}
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

