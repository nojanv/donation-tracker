package com.cubico.donationtracker;

import java.util.ArrayList;
import java.util.List;

public enum LocationType {
    DROP_OFF ("Drop Off"), STORE("Store"), WAREHOUSE("Warehouse");

    private String name;

    LocationType(String name) {
        this.name = name;
    }

    public String getName() {return name;}
}
