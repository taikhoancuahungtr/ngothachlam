package com.example;

import android.os.Bundle;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/*
 * This code is the demo for the Mobiletuts+ tutorial:
 *  - Android SDK: Creating a Simple Property Animation
 *  
 *  Sue Smith
 *  February 2013
 */
public class PropertyAnimatedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_property_animated);

		// get the wheel view
		ImageView wheel = (ImageView)findViewById(R.id.wheel);
		//load the wheel spinning animation
		AnimatorSet wheelSet = (AnimatorSet) 
				AnimatorInflater.loadAnimator(this, R.animator.wheel_spin);
		//the the view as target
		wheelSet.setTarget(wheel);
		//start the animation
		wheelSet.start();

		//get the sun view
		ImageView sun = (ImageView)findViewById(R.id.sun);
		//load the sun movement animation
		AnimatorSet sunSet = (AnimatorSet) 
				AnimatorInflater.loadAnimator(this, R.animator.sun_swing);
		//set the view as target
		sunSet.setTarget(sun);
		//start the animation
		sunSet.start();
				
		//create a value animator to darken the sky as we move towards and away from the sun
		//passing the view, property and to-from values
		ValueAnimator skyAnim = ObjectAnimator.ofInt(findViewById(R.id.car_layout), "backgroundColor", 
						Color.rgb(0x66, 0xcc, 0xff), Color.rgb(0x00, 0x66, 0x99));
		//set same duration and animation properties as others
		skyAnim.setDuration(3000);
		skyAnim.setEvaluator(new ArgbEvaluator());
		skyAnim.setRepeatCount(ValueAnimator.INFINITE);
		skyAnim.setRepeatMode(ValueAnimator.REVERSE);
		//start the animation
		skyAnim.start();

		//create an object animator to move the cloud
		//passing the view, property and to value only
		ObjectAnimator cloudAnim = ObjectAnimator.ofFloat
				(findViewById(R.id.cloud1), "x", -350);
		//same duration and other properties as rest
		cloudAnim.setDuration(3000);
		cloudAnim.setRepeatCount(ValueAnimator.INFINITE);
		cloudAnim.setRepeatMode(ValueAnimator.REVERSE);
		//start the animation
		cloudAnim.start();

		//create an object animator for second cloud
		//same as previous except movement distance
		ObjectAnimator cloudAnim2 = ObjectAnimator.ofFloat
				(findViewById(R.id.cloud2), "x", 0, -350);
		cloudAnim2.setDuration(3000);
		cloudAnim2.setRepeatCount(ValueAnimator.INFINITE);
		cloudAnim2.setRepeatMode(ValueAnimator.REVERSE);
		cloudAnim2.start();
	}
	
	public void viewAnimationDemo(View v) {
		ImageView spaceshipImage = (ImageView) findViewById(R.id.wheel);
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.animator.decelerate_interpolator);
		hyperspaceJumpAnimation.setRepeatCount(ValueAnimator.INFINITE);
		hyperspaceJumpAnimation.setRepeatMode(ValueAnimator.RESTART);
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
	}
}
