package com.example.lab04_p01_basicviewdemo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.formdangky);
		setContentView(R.layout.countdown);
		
		tv = (TextView) findViewById(R.id.textView1);
		countDown.sendEmptyMessage(0);
	}
	
	TextView tv;
	int timeOut = 10;
	Handler countDown = new Handler() {
		int i = timeOut;
		public void handleMessage(android.os.Message msg) {
			tv.setText(i-- + "");
			if(i == -1) {
				return;
			}
			this.sendEmptyMessageDelayed(0, 1000);
		}
	};
	
	public void submit(View v) {
		EditText txtTaiKhoan = (EditText) findViewById(R.id.txtTaiKhoan);
		String taiKhoan = txtTaiKhoan.getText().toString();
		EditText txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);
		String matKhau = txtMatKhau.getText().toString();
		EditText txtXacNhanMatKhau = (EditText) findViewById(R.id.txtXacNhanMatKhau);
		String xacNhanMatKhau = txtXacNhanMatKhau.getText().toString();
		if(!matKhau.equals(xacNhanMatKhau)) {
			Toast.makeText(getBaseContext(), "Xac nhan mat khau bi sai", Toast.LENGTH_SHORT).show();
			txtMatKhau.setText("");
			txtXacNhanMatKhau.setText("");
			txtMatKhau.requestFocus();
			return;
		}
		EditText txtMail = (EditText) findViewById(R.id.txtEmail);
		String email = txtMail.getText().toString();
		RadioButton rbNam = (RadioButton) findViewById(R.id.rbNam);
		String gioiTinh = "Nam";
		if(rbNam.isChecked() == false) {
			gioiTinh = "Nu";
		}
		
		String thongTinDangKy = "Tai Khoan: " + taiKhoan + "\n" 
							  + "Mat Khau: " + matKhau + "\n"
							  + "email: " + email + "\n"
							  + "Gioi Tinh: " + gioiTinh;
		Toast.makeText(getBaseContext(), thongTinDangKy, Toast.LENGTH_SHORT).show();
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

