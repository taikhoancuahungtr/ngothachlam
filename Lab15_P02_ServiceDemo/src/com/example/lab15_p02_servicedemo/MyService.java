package com.example.lab15_p02_servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
//		int result = download();
		
		new Downloader().execute();
		
//		handler1.sendEmptyMessage(0);
		
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service stoped", Toast.LENGTH_SHORT).show();
		
//		handler1.sendEmptyMessage(1000);
	}
	
	int download() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			return 1000;
		}
	}
	
	Handler handler1 = new Handler() {
		public void handleMessage(android.os.Message msg) {
//			msg.what
		};
	};
	
	Handler handler2 = new Handler() {
		public void handleMessage(android.os.Message msg) {
//			msg.what
		};
	};
	
	class Downloader extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... params) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				return 1000;
			}
		}
	}
}
