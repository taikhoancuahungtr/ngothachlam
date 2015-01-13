package com.example.lab19_p01_smsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void sendSMS(View v) {
		EditText txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
		EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(txtPhoneNumber.getText().toString(), null, 
				txtMessage.getText().toString(), null, null);
	}
}
