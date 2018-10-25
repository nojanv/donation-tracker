package com.cubico.donationtracker.POJOs;

import java.util.HashMap;

public class Model {
    private static final Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    HashMap<String, String> credentials;

    private Model() {
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
}