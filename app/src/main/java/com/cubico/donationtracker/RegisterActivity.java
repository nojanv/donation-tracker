package com.cubico.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cubico.donationtracker.POJOs.AccountType;
import com.cubico.donationtracker.POJOs.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Activity that handles registering our users
 */
public class RegisterActivity extends AppCompatActivity{


    // UI references.
    private AutoCompleteTextView mNameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mConfrimPasswordView;
    private Spinner mAccountSpinner;

    // User variables
    private String name;
    private String email;
    private String accountType;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference userRef = database.getReference("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mNameView = (AutoCompleteTextView) findViewById(R.id.name);
        mPasswordView = (EditText) findViewById(R.id.password);
        mConfrimPasswordView = (EditText) findViewById(R.id.confirm_password);



        mAccountSpinner = (Spinner) findViewById(R.id.acc_type);
        ArrayAdapter<CharSequence> accAdapter = ArrayAdapter.createFromResource(this,
                R.array.acctypes_array, android.R.layout.simple_spinner_item);
        accAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountSpinner.setAdapter(accAdapter);

        accountType = mAccountSpinner.getSelectedItem().toString();


        Button mEmailRegisterButton = (Button) findViewById(R.id.email_register_button);

        mEmailRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

        View mRegisterFormView = findViewById(R.id.register_form);
        View mProgressView = findViewById(R.id.register_progress);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String TAG = null;
                if(user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }

        };



    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean isPasswordValid(CharSequence password) {
        return password.length() > 6;
    }

    private boolean isEmailValid(CharSequence email) {
        return (email != null) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    @Nullable
    private
    View focusView;

    /**
     * Creates the new user account with email and password.

     */
    private void attemptRegistration() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mConfrimPasswordView.setError(null);
        mNameView.setError(null);

        name = mNameView.getText().toString();
        email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String cPassword = mConfrimPasswordView.getText().toString();
        accountType = mAccountSpinner.getSelectedItem().toString();

        boolean cancel = false;
        focusView = null;

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_password_short));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid matching password, if the user entered one.
        if (TextUtils.isEmpty(cPassword) || !password.equals(cPassword)) {
            mConfrimPasswordView.setError(getString(R.string.error_unmatched_passwords));
            focusView = mConfrimPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        //Check for valid name.
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {


            Log.d("TAG", email);
            Log.d("TAG", password);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // store the additional fields in firebase as well
                            AccountType type = null;
                            for (AccountType t : AccountType.values()) {
                                if (t.getName().equals(accountType)) {
                                    type = t;
                                }
                            }
                            type = (type == null) ? AccountType.USER : type;
                            User newUser = new User(name, email, type);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newUser)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this,
                                                getString(R.string.registration_success),
                                                Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),
                                                LoginActivity.class));
                                    } else {
                                        Log.d("TAG", task.getException() + "");
                                        Toast.makeText(RegisterActivity.this,
                                                getString(R.string.firebase_inner_error),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else if (!task.isSuccessful()){
                            Log.d("EXCEPTION", task.getException() + "");
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                mEmailView.setError(getString(R.string.error_email_exists));
                                focusView = mEmailView;
                                focusView.requestFocus();
                            }

                        }
                        }
                    });
        }
    }
}

