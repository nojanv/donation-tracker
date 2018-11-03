package com.cubico.donationtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cubico.donationtracker.POJOs.DonationItem;
import com.cubico.donationtracker.POJOs.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AllDonations extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<Location> list;
    ArrayList<DonationItem> list2;
    ArrayAdapter<Location> adapter;

    DonationAdapter donationAdapter;

    public Location location;
    public List<DonationItem> donations;


    SearchView searchDonations;
    Spinner modeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_all_donations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

}

