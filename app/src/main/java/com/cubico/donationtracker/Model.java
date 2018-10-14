package com.cubico.donationtracker;

import java.util.HashMap;

public class Model {
    private static final Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    HashMap<String, User> credentials;

    private Model() {
        credentials = new HashMap<>();
        credentials.put("@", new User("Spencer", "passw"));
    }

    public HashMap<String, User> getCredentials() {
        return credentials;
    }

    public void addUser(String email, User user) {
        credentials.put(email, user);
    }
    public User getUser(String email) {
        return credentials.get(email);
    }
    public boolean checkEmail(String email) {
        return credentials.containsKey(email);
    }
    public boolean checkPassword(String email, String password) {
        User user = credentials.get(email);
        return user.getPassword().equals(password);
    }
}