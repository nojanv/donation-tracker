package com.cubico.donationtracker;

import java.util.UUID;

public class User {

    private String name;
    private String email;
    private String accountType;

    User(String name, String email, String accountType) {
        this.name = name;
        this.email = email;
        this.accountType = accountType;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountType() {
        return accountType;
    }

}