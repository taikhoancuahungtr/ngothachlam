package com.example.lab19_smsdemo;

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

	PendingIntent sentPI, deliveredPI;
	BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
	String SENT = "SMS_Sent";
	String DELIVERED = "SMS_Delivered";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	smsSentReceiver = new BroadcastReceiver() {			
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "Sent", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
						Toast.makeText(getBaseContext(), "GENERIC_FAILURE", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_NO_SERVICE:
						Toast.makeText(getBaseContext(), "No Service", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_NULL_PDU:
						Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_RADIO_OFF:
						Toast.makeText(getBaseContext(), "Radio off", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		};
		
		smsDeliveredReceiver = new BroadcastReceiver() {			
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "Delivered", Toast.LENGTH_SHORT).show();
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
    
    @Override
    protected void onPause() {
    	super.onPause();
    	unregisterReceiver(smsSentReceiver);
    	unregisterReceiver(smsDeliveredReceiver);
    }
    
    void sendSMS(String phoneNumber, String message) {
    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
    
    public void sendSMS(View v) {
    	EditText txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
    	EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
		sendSMS(txtPhoneNumber.getText().toString(), txtMessage.getText().toString());
		
		Intent i = new Intent(android.content.Intent.ACTION_VIEW);
		i.putExtra("address", "5556; 5558; 5560");
		i.putExtra("sms_body", "Hello my friends!");
		i.setType("vnd.android-dir/mms-sms");
		startActivity(i);
	}
}
