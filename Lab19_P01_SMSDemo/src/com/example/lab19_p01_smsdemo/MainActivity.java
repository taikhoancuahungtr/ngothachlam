package com.example.lab19_p01_smsdemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		smsSent = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		smsDelivered = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
		
		smsSentReceiver = new BroadcastReceiver() {			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
						Toast.makeText(getBaseContext(), "GENERIC FAILURE", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_NULL_PDU:
						Toast.makeText(getBaseContext(), "NULL PDU", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_RADIO_OFF:
						Toast.makeText(getBaseContext(), "RADIO OFF", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		};
		
		smsDeliveredReceiver = new BroadcastReceiver() {			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "SMS Delivered", Toast.LENGTH_SHORT).show();
						break;
					case Activity.RESULT_CANCELED:
						Toast.makeText(getBaseContext(), "Not delivered", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		};
		
		registerReceiver(smsSentReceiver, new IntentFilter(SENT));
		registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));		
	}
	
	protected void onPause() {
		super.onPause();
		unregisterReceiver(smsSentReceiver);
		unregisterReceiver(smsDeliveredReceiver);
	};
	
	PendingIntent smsSent, smsDelivered;
	String SENT = "SMS_SENT";
	String DELIVERED = "SMS_DELIVERED";
	BroadcastReceiver smsSentReceiver, smsDeliveredReceiver; 
	
	public void sendSMS(View v) {
//		EditText txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
//		EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
//		SmsManager smsManager = SmsManager.getDefault();
//		smsManager.sendTextMessage(txtPhoneNumber.getText().toString(), null, 
//				txtMessage.getText().toString(), smsSent, smsDelivered);
		
		Intent i = new Intent(android.content.Intent.ACTION_VIEW);		 
        i.putExtra("address", "5554; 5558; 5560");
        i.putExtra("sms_body", "Hello my friends!");
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);
	}
}
