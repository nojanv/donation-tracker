package com.cubico.donationtracker.POJOs;

import android.app.Activity;

import java.util.HashMap;

public class Model extends Activity {
    private static final Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    private final HashMap<String, String> credentials;

    public Model() {
        credentials = new HashMap<>();
    }

    public HashMap<String, String> getCredentials() {
        return credentials;
    }

    public void addUser(String email, String password) {
        credentials.put(email, password);
    }
    public boolean checkEmail(String email) {
        return credentials.containsKey(email);
    }
    public boolean checkPassword(String email, String password) {
        return credentials.get(email).equals(password);
    }

    public boolean validDonation(float value, CharSequence name, CharSequence description) {
        if (value == 0) {
            return false;
        } else
            return name.length() != 0 && description.length() != 0;
    }
}