package com.cubico.donationtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cubico.donationtracker.POJOs.DonationItem;
import com.cubico.donationtracker.POJOs.ItemType;
import com.cubico.donationtracker.POJOs.Location;
import com.cubico.donationtracker.POJOs.Model;

import java.util.Date;

/**
 * Activity to add donation
 */
public class AddDonationActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mNameView;
    private EditText mQuantityView;
    private Spinner mTypeSpinner;
    private AutoCompleteTextView mLocationView;
    private AutoCompleteTextView mFullDescriptionView;
    private EditText mValueView;

    private View mRegisterFormView;

    private int quantity;
    private ItemType itemType;

    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        mNameView = findViewById(R.id.item_description);
        //mLocationView = (AutoCompleteTextView) findViewById(R.id.item_location);
        mFullDescriptionView = findViewById(R.id.full_description);
        mValueView = findViewById(R.id.value);

        mTypeSpinner = findViewById(R.id.item_type);
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
        itemType = (itemType == null) ? ItemType.CLOTHING : itemType;

        Button createDonation = findViewById(R.id.create_donation_button);
        createDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDonation();
            }
        });
        Button cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            location = bundle.getParcelable("location");
        }
    }

    private void createDonation() {
        String name = mNameView.getText().toString();
        //location = mLocationView.getText().toString();
        float value = Float.parseFloat(mValueView.getText().toString());

        String fullDescription = mFullDescriptionView.getText().toString();
        Date date = new Date();
        String timeStamp = date.toString();
        String type = mTypeSpinner.getSelectedItem().toString();

        Model validService = new Model();

        if (validService.validDonation(value, name, fullDescription) && (location != null)) {
            itemType = (itemType == null) ? ItemType.OTHER : itemType;
            Parcelable item = new DonationItem(
                    name,
                    timeStamp,
                    location.toString(),
                    fullDescription,
                    value,
                    itemType);
            Intent result = new Intent(AddDonationActivity.this, LocationActivity.class);
            result.putExtra("donation", item);
            setResult(Activity.RESULT_OK, result);
            finish();
        }


//        if (!isValueValid(value)) {
//            mValueView.setError(getString(R.string.error_invalid_quantity));
//            mValueView.requestFocus();
//            error = true;
//        }

//        if (value == 0) {
//            mValueView.setError(getString(R.string.error_invalid_quantity));
//            mValueView.requestFocus();
//            error = true;
//        }
//        if (name.length() == 0) {
//            mNameView.setError(getString(R.string.error_field_required));
//            mNameView.requestFocus();
//            error = true;
//        }
//        for (ItemType t : ItemType.values()) {
//            if (type.equals(t.getName())) {
//                itemType = t;
//            }
//        }
//
//        itemType = itemType == null ? ItemType.OTHER : itemType;
//        if (!error) {
//            DonationItem item = new DonationItem(
//                    name,
//                    timeStamp,
//                    location.toString(),
//                    fullDescription,
//                    value,
//                    itemType);
//            Intent result = new Intent(AddDonationActivity.this, LocationActivity.class);
//            result.putExtra("donation", item);
//            setResult(Activity.RESULT_OK, result);
//            finish();
//        }
    }

}