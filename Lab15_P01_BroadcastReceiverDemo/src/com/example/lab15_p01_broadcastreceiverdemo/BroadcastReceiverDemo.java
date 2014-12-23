package com.example.lab15_p01_broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiverDemo extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Received message", Toast.LENGTH_SHORT).show();
	}
}
