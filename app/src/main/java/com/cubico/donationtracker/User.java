package com.cubico.donationtracker;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String name;
    private String password;
    private String type;
    public static User DEFAULT = new User("No name", "password", "user");

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    User () {

    }
    User(String name, String password, String accountType) {
        this.name = name;
        this.password = password;
        this.type = accountType;
    }
    User(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
        this.type = in.readString();
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(type);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
}