package com.example.lab17_p01_mediademo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		copyFromAssetToSDCard();		
		
	}
	
	public void stopMusic(View v) {
		player.stop();
		seekBar.setProgress(0);
	}
	
	public void seekToRight(View v) {
		int curr = player.getCurrentPosition();
		int newPosition = curr + 5 * duration / 100;
		player.seekTo(newPosition);
	}
	
	public void seekToLeft(View v) {
		int curr = player.getCurrentPosition();
		int newPosition = curr - 5 * duration / 100;
		player.seekTo(newPosition);
	}
	
	MediaPlayer player;
	int duration;
	
	public void playMusic(View v) {
		String url = "android.resource://" + getPackageName() + "/" + R.raw.giacmocothat;
		Uri uri = Uri.parse(url);
		player = new MediaPlayer();
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			player.setDataSource(this, uri);
			player.prepare();
			player.start();
			duration = player.getDuration();
			
			seekBar = (SeekBar) findViewById(R.id.seekBar1);
			seekBar.setMax(duration);
			
			new UpdateSeekBar().execute();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	SeekBar seekBar;
	
	class UpdateSeekBar extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			while(player.isPlaying()) {
				publishProgress();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			seekBar.setProgress(player.getCurrentPosition());			
		}
	}
	
	String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyMusics";
	
	void copyFromAssetToSDCard() {
		File f = new File(folderPath);
		if(!f.exists()) {
			f.mkdir();
		}
		
		String[] listFiles;
		AssetManager assetManager = getAssets();
		try {
			listFiles = assetManager.list("");
			for (String fileName : listFiles) {
				if(!new File(fileName).isFile()) {
					continue;
				}
				InputStream in = assetManager.open(fileName);
				File file = new File(folderPath, fileName);
				OutputStream out = new FileOutputStream(file);
				copyFile(in, out);
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
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
		String url = "android.resource://" + getPackageName() + "/" + R.raw.famous;		
//		String url = folderPath + "/famous.3gp";		
		
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
		
		videoView.setVideoURI(uri);

		videoView.requestFocus();
		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				progressDialog.dismiss();
				videoView.start();
			}
		});
	}
}
