package com.cubico.donationtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cubico.donationtracker.POJOs.DonationItem;
import com.cubico.donationtracker.POJOs.ItemType;

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
        boolean error = false;
        name = mNameView.getText().toString();
        quantity = Integer.parseInt(mQuantityView.getText().toString());
        String type = mTypeSpinner.getSelectedItem().toString();
        if (quantity == 0) {
            mQuantityView.setError(getString(R.string.error_invalid_quantity));
            mQuantityView.requestFocus();
            error = true;
        }
        if (name.length() == 0) {
            mNameView.setError(getString(R.string.error_field_required));
            mNameView.requestFocus();
            error = true;
        }
        for (ItemType t : ItemType.values()) {
            if (type.equals(t.getName())) {
                itemType = t;
            }
        }
        itemType = itemType == null ? ItemType.OTHER : itemType;
        if (!error) {
            DonationItem item = new DonationItem(name, quantity, itemType);
            Intent result = new Intent(AddDonationActivity.this, LocationActivity.class);
            result.putExtra("donation", item);
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }

}
