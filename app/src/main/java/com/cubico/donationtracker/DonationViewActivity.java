package com.cubico.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cubico.donationtracker.POJOs.DonationItem;

public class DonationViewActivity extends AppCompatActivity {

    DonationItem donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_view);

        Bundle bundle = getIntent().getExtras();
        donation = bundle.getParcelable("donation");

        TextView name = (TextView) findViewById(R.id.donation_name);
        name.setText(donation.getName());

        TextView description = (TextView) findViewById(R.id.donation_quantity);
        String descText = "Description: " + donation.getFullDescription() +
                "\nStamp: " + donation.getTimeStamp() +
                "\nLocation: " + donation.getLocation() +
                "\nPrice: " + donation.getValue() +
                "\nType: " + donation.getItemType();
        description.setText(descText);
    }
}