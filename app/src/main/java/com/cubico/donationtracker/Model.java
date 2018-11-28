package com.cubico.donationtracker;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Singleton class currently used for donation validation
 */
public class Model extends Activity {
    private static final Model ourInstance = new Model();

    /**
     * Gets the singleton instance of our model
     * @return the model
     */
    public static Model getInstance() {
        return ourInstance;
    }

    /**
     * Constructor for model
     */
    public Model() {
    }


    /** This method checks if the values entered for adding a donation item are acceptable
     * @param value the value of the donation item
     * @param name the name/short description of the item
     * @param description the long description of the item
     * @return boolean value on if it is a valid donation or not
     */
    public boolean isValidDonation(float value, String name, String description) {
        if (value == 0) {
            return false;
        } else
            return !name.isEmpty() && !description.isEmpty();
    }

    public Orientation centerMap(List<LatLng> locations) {
        if (locations.isEmpty()) {
            return new Orientation(10, 0, 0);
        } else if (locations.size()  == 1) {
            return new Orientation(12, locations.get(0).latitude, locations.get(0).longitude);
        } else {
            double totalLat = 0;
            double totalLng = 0;
            double minLat = locations.get(0).latitude;
            double maxLat = locations.get(0).latitude;
            double minLng = locations.get(0).latitude;
            double maxLng = locations.get(0).latitude;
            for (LatLng l : locations) {
                double lat = l.latitude;
                double lng = l.longitude;
                totalLat += lat;
                totalLng += lng;
                if (lat > maxLat) {
                    maxLat = lat;
                } else if (lat < minLat) {
                    minLat = lat;
                }
                if (lng > maxLng) {
                    maxLng = lng;
                } else if (lng < minLng) {
                    minLng = lng;
                }
            }
            double distance = Math.sqrt(Math.pow((maxLat - minLat), 2) + Math.pow((maxLng - minLng), 2));
            float zoom = (float) (Math.log(distance / (360)) / Math.log(1 / (double) 2));
            Log.d("zoom level", "" + zoom);
            return new Orientation(10.5f, totalLat / locations.size(), totalLng / locations.size());
        }
    }
    public static class Orientation {
        public float zoom;
        public LatLng latLng;
        public Orientation(float zoom, double lat, double lng) {
            this.zoom = zoom;
            this.latLng = new LatLng(lat, lng);
        }
    }
}