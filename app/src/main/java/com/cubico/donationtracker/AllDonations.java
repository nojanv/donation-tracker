package com.cubico.donationtracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cubico.donationtracker.Fragments.DonationsFragment;
import com.cubico.donationtracker.POJOs.AccountType;
import com.cubico.donationtracker.POJOs.DonationItem;
import com.cubico.donationtracker.POJOs.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.cubico.donationtracker.LocationActivity.CREATE_DONATION_REQUEST;

public class AllDonations extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<Location> list;
    ArrayList<DonationItem> list2;
    ArrayAdapter<Location> adapter;
    //DonationAdapter adapter2;

    DonationAdapter donationAdapter;

    public Location location;
    public List<DonationItem> donations;
    public AccountType accountType;

    private DonationsFragment.DonationAddListener mListener;

    ListView donationListView;

    SearchView searchDonations;
    Spinner modeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_all_donations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        listView = findViewById(R.id.allDonations);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Locations");
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        adapter = new LocationAdapter(list, getApplicationContext());
        donationAdapter = new DonationAdapter(list2, this);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Location location = ds.getValue(Location.class);
                    list.add(location);
                    if (location.getDonations() != null) {
                        list2.addAll(location.getDonations());
                    }
                }

                listView.setAdapter(donationAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        searchDonations = (SearchView) findViewById(R.id.donationSearch2);
        searchDonations.setOnQueryTextListener(this);

        modeSpinner = (Spinner) findViewById(R.id.searchMode2);
        ArrayAdapter<CharSequence> accAdapter = ArrayAdapter.createFromResource(AllDonations.this,
                R.array.searchMode_array, android.R.layout.simple_spinner_item);
        accAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(accAdapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mode = modeSpinner.getSelectedItem().toString();
                donationAdapter.getFilter().setMode(mode.equals("By Name"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        donationAdapter.getFilter().filter(s);
        if (donationAdapter.getFilter().isEmpty()) {
            Toast.makeText(AllDonations.this, "No items match this text", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_DONATION_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                DonationItem item = bundle.getParcelable("donation");
                donationAdded(item);
            }
        }
    }

    public void donationAdded(DonationItem item) {
        if (mListener != null) {
            mListener.onDonationAdd(item);
        }
    }

    public void updateDonations(ArrayList<DonationItem> donations) {
        donationAdapter = new DonationAdapter(donations, AllDonations.this);
        listView.setAdapter(donationAdapter);
    }



//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof DonationsFragment.DonationAddListener) {
//            mListener = (DonationsFragment.DonationAddListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
    public interface DonationAddListener {
        void onDonationAdd(DonationItem item);
    }
}

