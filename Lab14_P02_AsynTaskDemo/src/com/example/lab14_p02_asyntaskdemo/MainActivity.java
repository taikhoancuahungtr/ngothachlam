package com.example.lab14_p02_asyntaskdemo;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
	}
	
	public void startDownload(View v) throws MalformedURLException {
		URL[] urls = {
			new URL("Link 1"),
			new URL("Link 2"),
			new URL("Link 3"),
			new URL("Link 4"),
			new URL("Link 5")
		};
		new DownloadImage().execute(urls);
	}
	
	class DownloadImage extends AsyncTask<URL, Integer, Long> {
		
		@Override
		protected Long doInBackground(URL... arr) {
			for (int i = 0; i < arr.length; i++) {
				download(arr[i]);
				publishProgress((i + 1) * 100 / arr.length);
			}			
			return (long) arr.length;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(values[0]);
		}
		
		@Override
		protected void onPostExecute(Long result) {
			Toast.makeText(getBaseContext(), "Downloaded :" + result + " files", Toast.LENGTH_LONG).show();
		}
	}
	
	public void download(URL url) {
		// Viet sau
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
