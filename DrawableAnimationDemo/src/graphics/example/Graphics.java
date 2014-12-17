package graphics.example;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Graphics extends Activity {
	
	AnimationDrawable logoAnimation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageView logoImage = (ImageView) findViewById(R.id.iv1);
		logoImage.setBackgroundResource(R.drawable.logo_animation);
		logoAnimation = (AnimationDrawable) logoImage.getBackground();
	}

	public void start(View v) {
		logoAnimation.start();
	}
	
	public void stop(View v) {
		logoAnimation.stop();
	}
}