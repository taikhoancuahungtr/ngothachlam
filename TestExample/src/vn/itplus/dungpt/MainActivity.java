package vn.itplus.dungpt;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		ArrayList<PersonModel> list = new ArrayList<PersonModel>();
		PersonModel person = new PersonModel("Amirakhan", "0123456789", 
				getResources().getDrawable(R.drawable.aamirkhan));
		list.add(person);
		person = new PersonModel("Barack Obama", "01987654321", 
				getResources().getDrawable(R.drawable.barackobama));
		list.add(person);
		person = new PersonModel("Yoona", "01888888888", 
				getResources().getDrawable(R.drawable.yoona));
		list.add(person);
		PersonAdapter adapter = new PersonAdapter(this, list);
		listView.setAdapter(adapter);
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
