package com.example.lab14_p02_asyntaskdemo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
	}
	
	String PATH;
	
	public void startDownload(View v) throws MalformedURLException {
		// Tao thu muc chua cac file anh se down ve the nho
		PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/images";
		File f = new File(PATH);
		if(!f.exists()) {
			f.mkdir();
		}
		
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
			
			GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
			gridLayout.removeAllViews();
			int column = 3;
			gridLayout.setColumnCount(column);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					gridLayout.getWidth() / column, 3 * gridLayout.getWidth() / (2 * column));
			File f = new File(PATH);
			for (File file : f.listFiles()) {
				if(file.isFile()) {
					if(file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
						ImageView imageView = new ImageView(getBaseContext());
						imageView.setLayoutParams(params);
						imageView.setScaleType(ScaleType.FIT_XY);
						Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
						imageView.setImageBitmap(bmp);
						gridLayout.addView(imageView);
					}
				}
			}
		}
	}
	
	public void download(URL url) {
		try {
			// Thiet lap ket noi toi dia chi cua file
			URLConnection urlConn = url.openConnection();
			// Tao doi tuong InputStream de doc du lieu
			InputStream in = urlConn.getInputStream();
			String fileName = System.currentTimeMillis() + getExtension(url.toString());
			// Tao ra file trong the nho
			FileOutputStream fos = new FileOutputStream(PATH + "/" + fileName);
			// Copy file
			BufferedInputStream bis = new BufferedInputStream(in);
			int n = 0;
			byte[] b = new byte[4096];
			while((n = bis.read(b)) > 0) {
				fos.write(b, 0, n);
			}
			fos.flush();
			bis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	String getExtension(String link) {
		return link.substring(link.lastIndexOf('.'), link.length()).toLowerCase();
	}
}
