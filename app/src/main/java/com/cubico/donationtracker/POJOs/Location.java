package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

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

    Location() {
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
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

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
        return String.format(name);
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public float getLat() {
        return lat;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public LocationType getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public ArrayList<DonationItem> getDonations() {
        return donations;
    }

    public void addDonation(DonationItem item) {
        donations.add(item);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Location)) {
            return false;
        }
        Location that = (Location) obj;
        return this.name.equals(that.getName());
    }
}

