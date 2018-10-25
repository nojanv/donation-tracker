package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

public enum AccountType implements Parcelable{
    USER("User"), ADMIN("Admin"), LOCATION_EMPLOYEE("Location Employee");

    private String name;
    public static final Parcelable.Creator<AccountType> CREATOR = new Parcelable.Creator<AccountType>() {
        public AccountType createFromParcel(Parcel in) {
            String inName = in.readString();
            for (AccountType type : AccountType.values()) {
                if (type.getName().equals(inName)) {
                    return type;
                }
            }
            return null;
        }

        public AccountType[] newArray(int size) {
            return new AccountType[size];
        }
    };

    AccountType(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public String getName() {
        return name;
    }

}