package com.cubico.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;

/**
 * Class representing welcome activity of app
 */
public class  WelcomeActivity extends AppCompatActivity {

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // if the user is on the welcome page, signout current firebase user
        // (BTW firebase auto-logins in upon registration)
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* VideoView vv = (VideoView) findViewById(R.id.videoView);

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;

        Uri uri = Uri.parse(path);

        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();*/

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(loginIntent);
            }
        });
    }

}
