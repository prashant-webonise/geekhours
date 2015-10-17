package com.jwetherell.augmented_reality.data;

import android.content.res.Resources;

public class CustomPlacesDataSource extends GooglePlacesDataSource {


    public CustomPlacesDataSource(Resources res) {
        super(res);
    }

    public CustomPlacesDataSource(Resources res, String TYPES) {
        super(res);
        this.TYPES = TYPES;
    }

    @Override
    public String createRequestURL(double lat, double lon, double alt, float radius, String locale) {
        try {
            return URL + "location=" + lat + "," + lon + "&radius=" + (radius * 1000.0f) + "&TYPES=" + TYPES + "&sensor=true&key=" + key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
