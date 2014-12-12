package com.example.lab11_p01_menuactionbardemo;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Window;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        
        setContentView(R.layout.activity_main);
        
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#330000ff")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#550000ff")));
        
//        ActionBar actionBar = getActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(150, 215, 242)));
//        actionBar.show();
        
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        editText1.setOnCreateContextMenuListener(this);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	getMenuInflater().inflate(R.menu.contextmenu, menu);
    }

    public void showPopupMenu(View v) {
		PopupMenu popupMenu = new PopupMenu(getBaseContext(), v);
		MenuInflater inflater = popupMenu.getMenuInflater();
		inflater.inflate(R.menu.popupmenu, popupMenu.getMenu());
		popupMenu.show();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Bat su kien Enter vao EditText Search
        View menusearch = menu.findItem(R.id.menu0).getActionView();
        final EditText txtSearch = (EditText) menusearch.findViewById(R.id.txtSearch);
        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				Toast.makeText(getBaseContext(), txtSearch.getText().toString(), Toast.LENGTH_SHORT).show(); 
				return false;
			}
		});
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Toast.makeText(this, "Click Setting", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu1) {
        	Toast.makeText(this, "Click Menu 1", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu2) {
        	Toast.makeText(this, "Click Menu 2", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu3) {
        	Toast.makeText(this, "Click Menu 3", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu4) {
        	Toast.makeText(this, "Click Menu 4", Toast.LENGTH_SHORT).show(); 
            return true;
        } else if(id == R.id.menu5) {
        	Toast.makeText(this, "Click Menu 5", Toast.LENGTH_SHORT).show(); 
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
