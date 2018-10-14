package com.cubico.donationtracker;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String name;
    private String password;
    private AccountType type;
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    User(String name, String password) {
        this.name = name;
        this.password = password;
        type = AccountType.USER;
    }
    User(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
        this.type = in.readParcelable(AccountType.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeParcelable(type, flags);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getType() {
        return type;
    }
}
