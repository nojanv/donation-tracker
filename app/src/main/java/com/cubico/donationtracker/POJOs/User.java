package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String name;
    private String email;
    private AccountType accountType;
    public static User DEFAULT = new User("No name", "noEmail@gmail.com", AccountType.USER);

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
    public User(String name, String password, AccountType accountType) {
        this.name = name;
        this.email = email;
        this.accountType = accountType;
    }
    public User(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.accountType = in.readParcelable(AccountType.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeParcelable(accountType, flags);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}