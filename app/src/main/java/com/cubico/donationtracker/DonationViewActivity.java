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
        TextView quantity = (TextView) findViewById(R.id.donation_quantity);
        quantity.setText(donation.getQuantity() + "");
    }
}
