package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String name;
    private String password;
    private AccountType accountType;
    public static User DEFAULT = new User("No name", "password", AccountType.USER);

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
        this.password = password;
        this.accountType = accountType;
    }
    public User(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
        this.accountType = in.readParcelable(AccountType.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeParcelable(accountType, flags);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}