package com.example.lab10_p01_usingcontentproviderdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        Uri uri = Browser.BOOKMARKS_URI; // content://abc.../bookmark/1
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
//        uri = ContentUris.withAppendedId(uri, 1);
        ContentResolver cv = getContentResolver();
        Cursor c = cv.query(uri, null, null, null, null);
        if(c.moveToFirst()) {
        	do {
        		Toast.makeText(getBaseContext(), c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2), Toast.LENGTH_SHORT).show();
        	} while(c.moveToNext());
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
