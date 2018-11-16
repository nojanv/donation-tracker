package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A class representing individual users of the application
 */
public class User implements Parcelable{
    private String name;
    private String email;
    private AccountType accountType;
    public static final User DEFAULT = new User("No name", "noEmail@gmail.com", AccountType.USER);

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * If this constructor isn't here the app freaks out.
     */
    public User() {

    }

    /**
     * Constructor for User
     * @param name of user
     * @param email of user
     * @param accountType of user
     */
    public User(String name, String email, AccountType accountType) {
        this.name = name;
        this.email = email;
        this.accountType = accountType;
    }

    private User(Parcel in) {
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

    /**
     * Get the name of user
     * @return string representation of name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the email of user
     * @return string representation of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the account of user
     * @return AccountType representation of account
     */
    public AccountType getAccountType() {
        return accountType;
    }
}