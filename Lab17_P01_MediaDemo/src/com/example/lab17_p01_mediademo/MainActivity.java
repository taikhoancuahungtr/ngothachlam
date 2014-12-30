package com.example.lab17_p01_mediademo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void playVideo(View v) {
//		String url = "http://channelz2.r15s91.vcdn.vn/zv/18b37ff14af9ff78be84ffa328a0d343/54a26990/2014/12/30/8/1/81d21be5cd03bf457df70eae0e4e838c.mp4";
		String url = "android.resource://" + getPackageName() + "/" + R.raw.famous;
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
