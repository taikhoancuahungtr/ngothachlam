package vn.itplus.dungpt;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class YeuThichActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yeuthich);
		
		ArrayList<String> list = new ArrayList<String>();
		DBAdapter db = new DBAdapter(getBaseContext());
        db.open();
        Cursor c = db.getAllAccount();
        c.moveToFirst();
        while(c.moveToNext()) {
        	list.add(c.getString(0));
        }
        db.close(); 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}
}
