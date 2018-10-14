package com.cubico.donationtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent welcomeIntent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(welcomeIntent);*/
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        user = bundle == null ? User.DEFAULT : (User) bundle.getParcelable("user");

        Context context = getApplicationContext();
        List<String> locationData = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("LocationData.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                locationData.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        List<Location> locations = new ArrayList<>();
        for (String locationElement : locationData) {
            String[] data = locationElement.split(",");
            LocationType locationType = null;
            for (LocationType type : LocationType.values()) {
                if (type.getName().equals(data[8])) {
                    locationType = type;
                }
            }
            locations.add(new Location(toInt(data[0]), data[1], toFloat(data[2]), toFloat(data[3]), data[4], data[5], data[6], toInt(data[7]), locationType, data[9], data[10]));
        }
        for (Location location : locations) {
            Log.d("Location", location.toString());
        }

        final LocationAdapter adapter = new LocationAdapter(locations, getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.locationList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location selected = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, LocationDetailActivity.class);
                intent.putExtra("location", selected);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }
    public int toInt(String s) {
        return Integer.parseInt(s);
    }
    public float toFloat(String s) {
        return Float.parseFloat(s);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
