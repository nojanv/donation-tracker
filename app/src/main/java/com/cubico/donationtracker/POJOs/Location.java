package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * The class that allows us to parse different locations into useable objects in our application.
 */
public class Location implements Parcelable {
    private int key;
    private String name;
    private float lat;
    private float longitude;
    private String address;
    private String city;
    private String state;
    private int zip;
    private LocationType type;
    private String phone;
    private String website;
    private ArrayList<DonationItem> donations;

    Location(int key, String name, float lat, float longitude, String address, String city,
             String state, int zip, LocationType type, String phone, String website) {
        this.key = key;
        this.name = name;
        this.lat = lat;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
        donations = new ArrayList<>();
    }

    /**
     * Default no-arg constructor required for Pacelable implementation
     */
    public Location() {
    }

    Location(Parcel in) {
        this.key = in.readInt();
        this.name = in.readString();
        this.lat = in.readFloat();
        this.longitude = in.readFloat();
        this.address = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zip = in.readInt();
        this.type = in.readParcelable(LocationType.class.getClassLoader());
        this.phone = in.readString();
        this.website = in.readString();
        donations = new ArrayList<>();
        in.readTypedList(donations, DonationItem.CREATOR);
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(key);
        dest.writeString(name);
        dest.writeFloat(lat);
        dest.writeFloat(longitude);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeInt(zip);
        dest.writeParcelable(type, flags);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeTypedList(donations);
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    public String toString() {
        return name;
    }

    /**
     * @return returns int key
     */
    public int getKey() {
        return key;
    }

    /**
     * @return returns name of location
     */
    public CharSequence getName() {
        return name;
    }

    /**
     * @return returns latitude
     */
    public float getLat() {
        return lat;
    }

    /**
     * @return returns longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @return returns address
     */
    public CharSequence getAddress() {
        return address;
    }

    /**
     * @return returns location's city name
     */
    public String getCity() {
        return city;
    }

    /**
     * @return returns location's state
     */
    public String getState() {
        return state;
    }

    /**
     * @return returns location's zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * @return returns location's type
     */
    public LocationType getType() {
        return type;
    }

    /**
     * @return returns location's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return returns location's website
     */
    public String getWebsite() {
        return website;
    }


    /**
     * @return an ArrayList of all the donations at a location
     */
    public ArrayList<DonationItem> getDonations() {
        ArrayList<DonationItem> copy = new ArrayList<>();
        if (donations == null) {
            return null;
        }
        for (DonationItem d : donations) {
            copy.add(d);
        }
        return copy;
    }

    /**
     * @param item the donation item to be added to a location
     */
    public void addDonation(DonationItem item) {
        donations.add(item);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof Location)) {
            return false;
        }
        Location that = (Location) obj;
        return this.name.contentEquals(that.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

