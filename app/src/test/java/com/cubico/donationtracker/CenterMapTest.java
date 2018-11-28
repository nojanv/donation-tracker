package com.cubico.donationtracker;

import com.cubico.donationtracker.Model.Orientation;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CenterMapTest {
    Model model;
    ArrayList<LatLng> empty;
    ArrayList<LatLng> one;
    ArrayList<LatLng> several;

    @Before
    public void setUp() {
        model = Model.getInstance();
        empty = new ArrayList<>();
        one = new ArrayList<>();
        one.add(new LatLng(25, 50));
        several = new ArrayList<>();
        several.add(new LatLng(0, 0));
        several.add(new LatLng(0.00001f, 0));
        several.add(new LatLng(0.00001f, 0.00001f));
        several.add(new LatLng(0, 0.00001f));
    }

    @Test
    public void testEmpty() {
        Orientation orientation = model.centerMap(empty);
        assertEquals(10, orientation.zoom, 0);
        assertEquals(0, orientation.latLng.latitude, 0);
        assertEquals(0, orientation.latLng.longitude, 0);
    }

    @Test
    public void testOne() {
        Orientation orientation = model.centerMap(one);
        assertEquals(12, orientation.zoom, 0);
        assertEquals(25, orientation.latLng.latitude, 0);
        assertEquals(50, orientation.latLng.longitude, 0);
    }

    @Test
    public void testSeveral() {
        Orientation orientation = model.centerMap(several);
        assertEquals(10.5, orientation.zoom, 0);
        assertEquals(0.000005, orientation.latLng.latitude, 0.01);
        assertEquals(0.000005, orientation.latLng.longitude, 0.01);
    }
}
