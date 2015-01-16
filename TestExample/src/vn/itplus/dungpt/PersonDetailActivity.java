package vn.itplus.dungpt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PersonDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.persondetail);
		
		String name = getIntent().getStringExtra("Name");
		String phone = getIntent().getStringExtra("Phone");
		
		TextView txtName = (TextView) findViewById(R.id.textView1);
		txtName.setText(name);
		TextView txtPhone = (TextView) findViewById(R.id.textView2);
		txtPhone.setText(phone);
	}
}
