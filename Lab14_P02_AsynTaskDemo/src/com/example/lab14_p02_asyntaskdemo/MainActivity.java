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
			new URL("http://media.doisongphapluat.com/329/2014/8/5/jennifer%20pham%202.jpg"),
			new URL("http://4.bp.blogspot.com/_zMrru98bWEc/TSR0v-x3n7I/AAAAAAAACuw/VVCOtT7C9vo/s1600/jenifer-pham-dao-pho-5.jpg"),
			new URL("http://4.bp.blogspot.com/_zMrru98bWEc/TRRN-XSkzKI/AAAAAAAACqQ/ZlKviGpgwgA/s1600/Jennifer-Pham1.jpg"),
			new URL("http://1.bp.blogspot.com/-3guyPeod9eM/TbD0gAuhfbI/AAAAAAAAEmA/Prb0iEkIZ_A/s1600/Jennifer-Pham-2011-5.JPG"),
			new URL("https://c2.staticflickr.com/4/3542/3681010208_632a4c8786.jpg")
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
