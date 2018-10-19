package com.cubico.donationtracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class AddDonationActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mNameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mConfrimPasswordView;
    private Spinner mAccountSpinner;
    private View mProgressView;
    private View mRegisterFormView;

    // User variables
    private String name;
    private String email;
    private String accountType;
    private String password;
    private String cPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        /*mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mNameView = (AutoCompleteTextView) findViewById(R.id.name);
        mPasswordView = (EditText) findViewById(R.id.password);
        mConfrimPasswordView = (EditText) findViewById(R.id.confirm_password);



        mAccountSpinner = (Spinner) findViewById(R.id.acc_type);
        ArrayAdapter<CharSequence> accAdapter = ArrayAdapter.createFromResource(this,
                R.array.acctypes_array, android.R.layout.simple_spinner_item);
        accAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountSpinner.setAdapter(accAdapter);

        accountType = mAccountSpinner.getSelectedItem().toString();*/

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
    }

}
