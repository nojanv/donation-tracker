package com.cubico.donationtracker.dummy;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cubico.donationtracker.POJOs.Location;
import com.cubico.donationtracker.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        Bundle bundle = getIntent().getExtras();
//        Location[] locationArray = (Location[]) bundle.getParcelableArray("locations");
//        for (Location l : locationArray) {
//            locations.add(l);
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
        LatLng afd = new LatLng(33.7541618347168, -84.3774185180664);
        //LatLng afd = new LatLng(locations.get(0).getLat(), locations.get(0).getLongitude());
        LatLng bgclub= new LatLng(33.73181915283203, -84.43971252441406);
        LatLng pathway = new LatLng(33.70866012573242, -84.41853332519531);
        LatLng pavilion = new LatLng(33.80128860473633, -84.25537109375);
        LatLng dd = new LatLng(33.71746826171875, -84.2520980834961);
        LatLng kNFB = new LatLng(33.96921157836914, -84.3687973022461);

        float zoom = 11;

        Marker afdStation4 = mMap.addMarker(new MarkerOptions()
                                            .position(afd)
                                            .title("AFD Station 4")
                                            .snippet("(404) 555 - 3456"));

        Marker boysAndGirlsClub = mMap.addMarker(new MarkerOptions()
                                        .position(bgclub).title("Boys & Girls Club W.W. Woolfolk")
                                        .snippet("(404) 555 - 1234"));

        Marker pathwayUpper = mMap.addMarker(new MarkerOptions()
                                .position(pathway).title("Pathway Upper Room Christian Ministries ")
                                .snippet("(404) 555 - 5432"));

        Marker pavilionOfHope = mMap.addMarker(new MarkerOptions()
                                .position(pavilion).title("Pavilion of Hope Inc")
                                .snippet("(404) 555 - 8765"));

        Marker dAndD = mMap.addMarker(new MarkerOptions()
                                .position(dd).title("D&D Convenience Store")
                                .snippet("(404) 555 - 9876"));

        Marker northFulton = mMap.addMarker(new MarkerOptions()
                                .position(kNFB).title("Keep North Fulton Beautiful")
                                .snippet("(770) 555 - 7321"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(afd, zoom));

    }
}