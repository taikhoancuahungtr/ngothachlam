package com.example.lab16_p01_readjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new LoadMovie(this).execute();
	}
	
	class LoadMovie extends AsyncTask<Void, Void, Void> {

		Context context;
		
		public LoadMovie(Context context) {
			this.context = context;
		}

		@Override
		protected Void doInBackground(Void... params) {
			String url1 = "http://api.androidhive.info/json/movies.json";
			// Hoac link sau thi phai xu ly khac mot chut
			String url2 = "http://api.androidhive.info/contacts/";
					
			JSONArray a = getJSONArrayFromUrl(url1);
			for (int i = 0; i < a.length(); i++) {
				try {
					JSONObject o = a.getJSONObject(i);
					Log.v("INFO", o.getString("title") + " : " + o.getString("rating"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}
	
	JSONArray getJSONArrayFromUrl(String url) {
		InputStream is = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder content = new StringBuilder();
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
			String line = "";
			while((line = bf.readLine()) != null) {
				content.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JSONArray jsonArray = null;
			try {
				is.close();
				bf.close();
				jsonArray = new JSONArray(content.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return jsonArray;
		}
	}
}
