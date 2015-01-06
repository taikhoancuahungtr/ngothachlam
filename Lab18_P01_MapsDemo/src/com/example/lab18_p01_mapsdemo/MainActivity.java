package com.example.lab18_p01_mapsdemo;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {

	MapView mapView;
	MapController mc;
	Overlay overlay;
	List<Overlay> listOfOverlays;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);
		
		mapView = (MapView) findViewById(R.id.mapView);
		mc = mapView.getController();
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapView.setTraffic(true);
		
		String toaDo[] = {"21.022792", "105.843610"};
		double lat = Double.parseDouble(toaDo[0]);		 
        double lng = Double.parseDouble(toaDo[1]);
        GeoPoint p = new GeoPoint(
                (int) (lat * 1E6),
                (int) (lng * 1E6)
        	 );
        mc.animateTo(p);
        
        overlay = new MapOverlay(p);
        listOfOverlays = mapView.getOverlays();
        listOfOverlays.add(overlay);
        mapView.invalidate();
	}
	
	private class MapOverlay extends com.google.android.maps.Overlay {
		GeoPoint p;
		public MapOverlay(GeoPoint p) {
			this.p = p;
		}
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			super.draw(canvas, mapView, shadow);
			// ---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);
			// ---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pushpin);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 25, null);
			return true;
		}
	}


	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}	
}
