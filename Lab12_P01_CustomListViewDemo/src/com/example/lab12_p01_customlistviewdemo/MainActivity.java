package com.example.lab12_p01_customlistviewdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	ArrayList<SongModel> list;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        list = new ArrayList<SongModel>();
        list.add(new SongModel("song 1", "3:05", "Author 1", 2012));
        list.add(new SongModel("song 2", "4:05", "Author 2", 2011));
        list.add(new SongModel("song 3", "5:05", "Author 3", 2010));
        list.add(new SongModel("song 4", "6:05", "Author 4", 2012));
        list.add(new SongModel("song 5", "7:05", "Author 5", 2011));
        list.add(new SongModel("song 6", "8:05", "Author 6", 2010));
        SongAdapter adapter = new SongAdapter(getBaseContext(), list);
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }
    
    public void showListSelectedSong(View v) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).isSelected()) {
				Toast.makeText(getBaseContext(), list.get(i).toString(), Toast.LENGTH_SHORT).show();
			}
		}
	}
}
