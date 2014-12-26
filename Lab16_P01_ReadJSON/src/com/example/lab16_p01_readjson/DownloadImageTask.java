package com.example.lab16_p01_readjson;

import java.io.InputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	
    ImageView iv;	    

	public DownloadImageTask(ImageView iv) {
        this.iv = iv;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlImg = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(urlImg).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }
}