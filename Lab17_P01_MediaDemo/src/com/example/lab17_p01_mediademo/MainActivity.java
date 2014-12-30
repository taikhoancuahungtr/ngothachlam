package com.example.lab17_p01_mediademo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		copyFromAssetToSDCard();
		
	}
	
	String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Musics";
	
	void copyFromAssetToSDCard() {
		File f = new File(folderPath);
		if(!f.exists()) {
			f.mkdir();
		}
		
		String[] listFiles;
		AssetManager assetManager = getAssets();
		try {
			listFiles = assetManager.list("");
			InputStream in = null;
			OutputStream out = null;
			for (String fileName : listFiles) {
				if(!new File(fileName).isFile()) {
					continue;
				}
				in = assetManager.open(fileName);
				File file = new File(folderPath, fileName);
				out = new FileOutputStream(file);
				copyFile(in, out);
			}
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] b = new byte[4096];
		int n;
		while((n = in.read(b)) > 0) {
			out.write(b, 0, n);
		}
		out.flush();
	}
	
	public void playVideo(View v) {
//		String url = "http://channelz2.r15s91.vcdn.vn/zv/18b37ff14af9ff78be84ffa328a0d343/54a26990/2014/12/30/8/1/81d21be5cd03bf457df70eae0e4e838c.mp4";
//		String url = "android.resource://" + getPackageName() + "/" + R.raw.famous;		
		String url = folderPath + "/famous.3gp";		
		
		Uri uri = Uri.parse(url);
		
		final VideoView videoView = (VideoView) findViewById(R.id.videoView1);
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Buffering...");
		progressDialog.show();
		
		// Tao ra MediaController 
		MediaController mediaController = new MediaController(this);
		// Neo vao VideoView
		mediaController.setAnchorView(videoView);
		videoView.setMediaController(mediaController);
		
		videoView.requestFocus();
		videoView.setVideoURI(uri);
		
		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				progressDialog.dismiss();
				videoView.start();
			}
		});
	}
}
