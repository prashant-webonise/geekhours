package com.jwetherell.augmented_reality.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.jwetherell.augmented_reality.R;
import com.jwetherell.augmented_reality.ui.IconMarker;
import com.jwetherell.augmented_reality.ui.Marker;

/**
 * This class should be used as a example local data source. It is an example of
 * how to add data programatically. You can add data either programatically,
 * SQLite or through any other source.
 *
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class LocalDataSource extends DataSource {

    private final Context context;
    private List<Marker> cachedMarkers = new ArrayList<Marker>();
    private static Bitmap icon = null;

    public LocalDataSource(Context context) {
        if (context == null) throw new NullPointerException();
        this.context = context;

        createIcon(context.getResources());
    }

    protected void createIcon(Resources res) {
        if (res == null) throw new NullPointerException();

        icon = BitmapFactory.decodeResource(res, R.drawable.terrorist);

        getMarkers();
    }

    public List<Marker> getMarkers() {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                generateRandomLocations(location.getLatitude(), location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });


        generateRandomLocations(18.510627, 73.777369);



//        Marker atl = new IconMarker("OSAMA", 18.520430, 73.856744, 0, Color.DKGRAY, icon);
//        cachedMarkers.add(atl);
//        atl = new IconMarker("OBAMA", 18.510662, 73.777420, 0, Color.DKGRAY, icon);
//        cachedMarkers.add(atl);
//        atl = new IconMarker("ICON", 18.510776, 73.777762, 0, Color.DKGRAY, icon);
//        cachedMarkers.add(atl);
//        atl = new IconMarker("SHUBHAM", 18.508287, 73.775127, 0, Color.DKGRAY, icon);
//        cachedMarkers.add(atl);
//        atl = new IconMarker("PRASHANT", 18.520430, 73.856744, 0, Color.DKGRAY, icon);
//        cachedMarkers.add(atl);
//
//        /*
//         * Marker lon = new IconMarker(
//         * "I am a really really long string which should wrap a number of times on the screen."
//         * , 39.95335, -74.9223445, 0, Color.MAGENTA, icon);
//         * cachedMarkers.add(lon); Marker lon2 = new IconMarker(
//         * "2: I am a really really long string which should wrap a number of times on the screen."
//         * , 39.95334, -74.9223446, 0, Color.MAGENTA, icon);
//         * cachedMarkers.add(lon2);
//         */
//
//        /*
//         * float max = 10; for (float i=0; i<max; i++) { Marker marker = null;
//         * float decimal = i/max; if (i%2==0) marker = new Marker("Test-"+i,
//         * 39.99, -75.33+decimal, 0, Color.LTGRAY); marker = new
//         * IconMarker("Test-"+i, 39.99+decimal, -75.33, 0, Color.LTGRAY, icon);
//         * cachedMarkers.add(marker); }
//         */
//
        ARData.addMarkers(cachedMarkers);
        return cachedMarkers;
    }

    private void generateRandomLocations(double latitude, double longitude) {

        Log.d("", "@@@@@@@@");
        Random random = new Random();
        for (int i = 0; i < 20; i++) {

            // Convert radius from meters to degrees
            double radiusInDegrees = 9000 / 111000f;

            double u = random.nextDouble();
            double v = random.nextDouble();
            double w = radiusInDegrees * Math.sqrt(u);
            double t = 2 * Math.PI * v;
            double x = w * Math.cos(t);
            double y = w * Math.sin(t);

            // Adjust the x-coordinate for the shrinking of the east-west distances
            double new_x = x / Math.cos(latitude);

            double foundLongitude = new_x + longitude;
            double foundLatitude = y + latitude;


            IconMarker atl = new IconMarker("OSAMA " + i, foundLatitude, foundLongitude, 0, Color.DKGRAY, icon);
            cachedMarkers.add(atl);


            System.out.println("Longitude: " + foundLongitude + "  Latitude: " + foundLatitude );
        }

        ARData.addMarkers(cachedMarkers);

    }
}
