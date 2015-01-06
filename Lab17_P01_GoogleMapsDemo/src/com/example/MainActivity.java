package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {

	MapView mapView;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.mapView);
		mc = mapView.getController();
		
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapView.setTraffic(true);
		
		String toaDo[] = {"1.352566007", "103.78921587"};
		double lat = Double.parseDouble(toaDo[0]);
		double lng = Double.parseDouble(toaDo[1]);
		GeoPoint p = new GeoPoint(
					(int) (lat * 1E6),
					(int) (lng * 1E6)
				);
		mc.animateTo(p);
		mc.setZoom(13);
		
		//---Add a location marker---
		overlay = new MapOverlay(p);
		listOfOverlays = mapView.getOverlays();
		listOfOverlays.add(overlay);
		
		mapView.invalidate();
	}
	
	MapController mc;
	List<Overlay> listOfOverlays;
	MapOverlay overlay;
	
	public void zoomIn(View v) {
		mc.zoomIn();
	}
	public void zoomOut(View v) {
		mc.zoomOut();
	}
	
	private class MapOverlay extends com.google.android.maps.Overlay {
		
		GeoPoint p;
		
		public MapOverlay(GeoPoint p) {
			this.p = p;
		}
		
        @Override
        public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
            super.draw(canvas, mapView, shadow);

            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(p, screenPts);

            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pushpin);
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 25, null);
            return true;
        }
        
        @Override
        public boolean onTouchEvent(MotionEvent event, MapView mapView) {
        	//---when user lifts his finger---
        	if (event.getAction() == 1) {
        		GeoPoint p = mapView.getProjection().fromPixels(
        				(int) event.getX(),
        				(int) event.getY()
        			);

        		overlay = new MapOverlay(p);
        		listOfOverlays.add(overlay);
        		
        		mapView.invalidate();
        		
//		    Toast.makeText(getBaseContext(), "Location: " + 
//        		p.getLatitudeE6() / 1E6 + "," + 
//            	p.getLongitudeE6() / 1E6 , Toast.LENGTH_SHORT).show();
        		 
        		Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        		try {
        			List<Address> addresses = geoCoder.getFromLocation(
        					p.getLatitudeE6()  / 1E6,
        					p.getLongitudeE6() / 1E6, 1);

        			String add = "";
        			if (addresses.size() > 0) {
        				for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); i++) {
        					add += addresses.get(0).getAddressLine(i) + "\n";
        				}
        			}
        			Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
        		}
        		catch (IOException e) {
        			e.printStackTrace();
        		}
        		return true;

        	}
        	return false;
        }
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}	
}
