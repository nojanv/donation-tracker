package com.cubico.donationtracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


 //{@link LocationDetails.OnFragmentInteractionListener} interface
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LocationDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "location";
    private static final String ARG_PARAM2 = "userr";

    // TODO: Rename and change types of parameters
    private Location location;
    private User user;

//    private OnFragmentInteractionListener mListener;

    static final int CREATE_DONATION_REQUEST = 1;

    public LocationDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param location Parameter 1.
     * @param user Parameter 2.
     * @return A new instance of fragment LocationDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationDetails newInstance(Location location, User user) {
        LocationDetails fragment = new LocationDetails();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, location);
        args.putParcelable(ARG_PARAM2, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            location = getArguments().getParcelable(ARG_PARAM1);
            user = getArguments().getParcelable(ARG_PARAM2);

        }
//        //Code from LocationDetailsActivity////////////////////////////////////////////////
//        setContentView(R.layout.fragment_location_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Bundle bundle = getIntent().getExtras();
//        Location location = bundle.getParcelable("location");
//        User user = bundle.getParcelable("user");



        /*Button donation = getView().findViewById(R.id.addDonation);
        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent intent = new Intent(LocationDetails.this, AddDonationActivity.class);
                startActivityForResult(intent, CREATE_DONATION_REQUEST);*//*
            }
        });*/
        /////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


//        Bundle bundle = getArguments();
//        Location location = bundle.getParcelable("location");
//        User user = bundle.getParcelable("user");
        View view = inflater.inflate(R.layout.fragment_location_details, container, false);



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("userInfo", String.format("%s, %s, %s", user.getName(), user.getPassword(), user.getAccountType().getName()));
        View view = getView();
        TextView name = view.findViewById(R.id.flocationName);
        name.setText(location.getName());
        TextView type = view.findViewById(R.id.flocationType);
        type.setText(location.getType().getName());
        TextView message = view.findViewById(R.id.fmessage);
        message.setText(String.format("Call %s to get started on your donation.", location.getPhone()));
        TextView address = view.findViewById(R.id.flocationAddress);
        address.setText(location.getAddress());
        TextView cityStateZip = view.findViewById(R.id.flocationCityStateZip);
        cityStateZip.setText(String.format("%s, %s %d", location.getCity(), location.getState(), location.getZip()) );
        TextView latLong = view.findViewById(R.id.flocationLatLong);
        latLong.setText(String.format("%s, %s", location.getLat(), location.getLongitude()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
