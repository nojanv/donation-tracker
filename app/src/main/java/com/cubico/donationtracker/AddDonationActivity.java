package com.cubico.donationtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddDonationActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mNameView;
    private EditText mQuantityView;
    private Spinner mTypeSpinner;
    private View mRegisterFormView;

    // User variables
    private String name;
    private int quantity;
    private ItemType itemType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        mNameView = (AutoCompleteTextView) findViewById(R.id.item_name);
        mQuantityView = (EditText) findViewById(R.id.quantity);



        mTypeSpinner = (Spinner) findViewById(R.id.item_type);
        ArrayAdapter<CharSequence> accAdapter = ArrayAdapter.createFromResource(this,
                R.array.itemtypes_array, android.R.layout.simple_spinner_item);
        accAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(accAdapter);

        String type = mTypeSpinner.getSelectedItem().toString();
        for (ItemType t : ItemType.values()) {
            if (type.equals(t.getName())) {
                itemType = t;
            }
        }
        itemType = itemType == null ? ItemType.FOOD : itemType;

        Button createDonation = findViewById(R.id.create_donation_button);
        createDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDonation();
            }
        });

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    private void createDonation() {
        name = mNameView.getText().toString();
        quantity = Integer.parseInt(mQuantityView.getText().toString());
        String type = mTypeSpinner.getSelectedItem().toString();
        for (ItemType t : ItemType.values()) {
            if (type.equals(t.getName())) {
                itemType = t;
            }
        }
        itemType = itemType == null ? ItemType.FOOD : itemType;
        /*Log.d("type", type);
        if (itemType == null) {
            itemType = ItemType.FOOD;
        }
        Log.d("typeCorrected", itemType.getName());*/
        DonationItem item = new DonationItem(name, quantity, itemType);
        Intent result = new Intent(AddDonationActivity.this, LocationDetailsActivity.class);
        result.putExtra("donation", item);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

}
