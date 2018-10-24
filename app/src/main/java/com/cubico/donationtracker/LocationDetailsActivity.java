package com.cubico.donationtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class LocationDetailsActivity extends AppCompatActivity {

    static final int CREATE_DONATION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        Location location = bundle.getParcelable("location");
        User user = bundle.getParcelable("user");
        TextView name = findViewById(R.id.locationName);
        name.setText(location.getName());
        TextView type = findViewById(R.id.locationType);
        type.setText(location.getType().getName());
        TextView message = findViewById(R.id.message);
        message.setText(String.format("Call %s to get started on your donation.", location.getPhone()));
        TextView address = findViewById(R.id.locationAddress);
        address.setText(location.getAddress());
        TextView cityStateZip = findViewById(R.id.locationCityStateZip);
        cityStateZip.setText(String.format("%s, %s %d", location.getCity(), location.getState(), location.getZip()) );
        TextView latLong = findViewById(R.id.locationLatLong);
        latLong.setText(String.format("%s, %s", location.getLat(), location.getLongitude()));

        Button donation = findViewById(R.id.addDonation);
        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationDetailsActivity.this, AddDonationActivity.class);
                startActivityForResult(intent, CREATE_DONATION_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_DONATION_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                DonationItem item = bundle.getParcelable("donation");
                TextView itemText = findViewById(R.id.donationText);
                itemText.setText(String.format("%s, %s, %s", item.getName(), item.getQuantity(), item.getItemType().getName()));
            }
        }
    }
}