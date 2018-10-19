package com.cubico.donationtracker;

public enum ItemType {
    FOOD("FD"),
    CLOTHING("CL");

    private String abbreviation;

    private ItemType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
