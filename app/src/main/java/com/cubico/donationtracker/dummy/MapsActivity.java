package com.cubico.donationtracker.dummy;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cubico.donationtracker.Model;
import com.cubico.donationtracker.Model.Orientation;
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

/**
 * The activity for our maps.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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
        assert bundle != null;
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

        GoogleMap mMap = googleMap;

        List<Marker> markers = new ArrayList<>();
        List<LatLng> latLngs = new ArrayList<>();

        for (Location l : locationList) {
            LatLng latLng = new LatLng(l.getLat(), l.getLongitude());
            latLngs.add(latLng);
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title((String) l.getName())
                    .snippet(l.getPhone()));
            markers.add(marker);
        }
        Model model = Model.getInstance();
        Orientation orientation = model.centerMap(latLngs);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(orientation.latLng, orientation.zoom));

    }
}