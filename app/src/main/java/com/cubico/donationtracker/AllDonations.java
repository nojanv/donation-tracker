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

/**
 * A class that comprises of all donations
 */
public class AllDonations extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView listView;
    private ArrayList<Location> list;
    private ArrayList<DonationItem> list2;

    private DonationAdapter donationAdapter;

    private Location location;

    private Spinner modeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_donations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.allDonations);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Locations");
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        ArrayAdapter<Location> adapter = new LocationAdapter(list, getApplicationContext());
        donationAdapter = new DonationAdapter(list2, this);


        ref.addValueEventListener(new ValueEventListener() {
            /**
             * Updates data on screen when database changed
             * @param dataSnapshot is the snapshot of data used by methof
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    location = ds.getValue(Location.class);
                    list.add(location);
                    if (location.getDonations() != null) {
                        list2.addAll(location.getDonations());
                    }
                }

                listView.setAdapter(donationAdapter);
            }

            /**
             * Do nothing when cancelled
             * @param databaseError is the error that is passed through to the method
             */
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        SearchView searchDonations = (SearchView) findViewById(R.id.donationSearch2);
        searchDonations.setOnQueryTextListener(this);

        modeSpinner = (Spinner) findViewById(R.id.searchMode2);
        ArrayAdapter<CharSequence> accAdapter = ArrayAdapter.createFromResource(AllDonations.this,
                R.array.searchMode_array, android.R.layout.simple_spinner_item);
        accAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(accAdapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Gets selected string when clicked and sets as filter mode
             * @param parent in which the class exists
             * @param view in which the method is being called
             * @param position in the listview
             * @param id id of the item within the list
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mode = modeSpinner.getSelectedItem().toString();
                donationAdapter.getFilter().setMode("By Name".equals(mode));
            }

            /**
             * DOes nothing when nothing is done to spinner
             * @param parent on which nothing is selected
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    /**
     * Does action once searchview is submitted, must have in order to implement query text listener
     * @param s is the string passed to the method once submitted
     * @return false (void method that always returns false)
     */
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    /**
     * Takes char sequence from edit text every time the text is altered and updates filter
     * @param s passed to the method everytime the query text is altered
     * @return false (void method that always returns false)
     */
    @Override
    public boolean onQueryTextChange(String s) {
        donationAdapter.getFilter().filter(s);
        if (donationAdapter.getFilter().isEmpty()) {
            Toast.makeText(AllDonations.this,
                            "No items match this text",
                            Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}

