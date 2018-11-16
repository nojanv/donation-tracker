package com.cubico.donationtracker.dummy;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.cubico.donationtracker.POJOs.Location;
import com.cubico.donationtracker.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Location> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        locationList = bundle.getParcelableArrayList("locations");



        //locationList.addAll(locations);

//        for (Location l : locations) {
//            locationList.add(l);
//        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        ArrayList<LatLng> markers = new ArrayList<>();

        for (Location l : locationList) {
            LatLng latLng = new LatLng(l.getLat(), l.getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(l.getName())
                    .snippet(l.getPhone()));
        }

        float zoom = 11;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.8, -84.3774185180664), zoom));

    }
}