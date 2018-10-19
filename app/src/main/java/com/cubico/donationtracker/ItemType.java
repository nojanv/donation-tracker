package com.cubico.donationtracker;

import android.os.Parcel;
import android.os.Parcelable;

public enum ItemType implements Parcelable{
    FOOD("FD"),
    CLOTHING("CL");

    private String abbreviation;
    public static final Parcelable.Creator<ItemType> CREATOR = new Parcelable.Creator<ItemType>() {
        public ItemType createFromParcel(Parcel in) {
            String ab = in.readString();
            for(ItemType type : ItemType.values()) {
                if(ab.equals(type.getAbbreviation())) {
                    return type;
                }
            }
            return ItemType.FOOD;
        }

        public ItemType[] newArray(int size) {
            return new ItemType[size];
        }
    };

    private ItemType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(abbreviation);
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
