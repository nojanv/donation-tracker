package com.cubico.donationtracker;

import android.os.Parcel;
import android.os.Parcelable;

public enum ItemType implements Parcelable{
    HOUSEHOLD_ITEMS("Household Items"),
    GAMES("Games"),
    FOOD("Food"),
    CLOTHING("Clothing"),
    OTHER("Other");

    private String name;
    public static final Parcelable.Creator<ItemType> CREATOR = new Parcelable.Creator<ItemType>() {
        public ItemType createFromParcel(Parcel in) {
            String ab = in.readString();
            for(ItemType type : ItemType.values()) {
                if(ab.equals(type.getName())) {
                    return type;
                }
            }
            return ItemType.OTHER;
        }

        public ItemType[] newArray(int size) {
            return new ItemType[size];
        }
    };

    ItemType(String name) {
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
