package com.cubico.donationtracker.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cubico.donationtracker.POJOs.Location;
import com.cubico.donationtracker.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LocationDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationDetails extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "location";

    private Location location;



    public LocationDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param location Parameter 1.
     * @return A new instance of fragment LocationDetails.
     */
    public static LocationDetails newInstance(Location location) {
        LocationDetails fragment = new LocationDetails();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, location);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            location = getArguments().getParcelable(ARG_PARAM1);
        }
//        //Code from LocationDetailsActivity////////////////////////////////////////////////
//        setContentView(R.layout.fragment_location_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_details, container, false);

    }



    @SuppressWarnings("FeatureEnvy")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        TextView name = view.findViewById(R.id.locationName);
        name.setText(location.getName());
        TextView type = view.findViewById(R.id.locationType);
        type.setText(location.getType().getName());
        TextView message = view.findViewById(R.id.message);
        message.setText(String.format("Call %s to get started on your donation.",
                location.getPhone()));
        TextView address = view.findViewById(R.id.locationAddress);
        address.setText(location.getAddress());
        TextView cityStateZip = view.findViewById(R.id.locationCityStateZip);
        cityStateZip.setText(String.format("%s, %s %d",
                location.getCity(),
                location.getState(),
                location.getZip()) );
        TextView latLong = view.findViewById(R.id.locationLatLong);
        latLong.setText(String.format("%s, %s",
                location.getLat(),
                location.getLongitude()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
