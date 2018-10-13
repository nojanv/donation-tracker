package com.cubico.donationtracker;

public class Location {
    private String name;
    private String city;

    Location() {
    }

    Location(String name, String city) {
        this.name = name;
        this.city = city;
    }


    public String toString() {
        return String.format("Welcome to %s, located in %s", name, city);
    }

    public String getCity() {
        return city;
    }

   public String getName() {
        return name;
   }
}

