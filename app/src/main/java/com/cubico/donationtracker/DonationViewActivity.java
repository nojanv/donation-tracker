package com.cubico.donationtracker;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            Intent intent = NavUtils.getParentActivityIntent(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            NavUtils.navigateUpTo(this, intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}