package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Manages our location's types by providing them with the correct information needed to display
 * on our application's interface.
 */
public enum LocationType implements Parcelable {
    DROP_OFF ("Drop Off"), STORE("Store"), WAREHOUSE("Warehouse");

    private final String name;
    public static final Parcelable.Creator<LocationType> CREATOR =
            new Parcelable.Creator<LocationType>() {
        @Override
        public LocationType createFromParcel(Parcel in) {
            String inName = in.readString();
            for(LocationType type: LocationType.values()) {
                if(type.getName().equals(inName)) {
                    return type;
                }
            }
            return null;
        }
        @Override
        public LocationType[] newArray(int size) {
            return new LocationType[size];
        }
    };

    LocationType(String name) {
        this.name = name;
    }

    LocationType(Parcel in) {
        name = in.readString();
    }

    /**
     * gets the name of this location
     * @return the location's name
     */
    public CharSequence getName() {return name;}

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}