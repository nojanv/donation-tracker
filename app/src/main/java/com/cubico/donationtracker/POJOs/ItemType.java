package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Enum to decribe type of donation item
 */
public enum ItemType implements Parcelable{
    HOUSEHOLD_ITEMS("Household Items"),
    ELECTRONICS("Electronics"),
    HAT("Hat"),
    FOOD("Food"),
    CLOTHING("Clothing"),
    KITCHEN("Kitchen"),
    OTHER("Other");

    private final String name;
    public static final Parcelable.Creator<ItemType> CREATOR = new Parcelable.Creator<ItemType>() {
        @Override
        public ItemType createFromParcel(Parcel in) {
            String ab = in.readString();
            for(ItemType type : ItemType.values()) {
                if(ab.equals(type.getName())) {
                    return type;
                }
            }
            return ItemType.OTHER;
        }

        @Override
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

    /**
     * Public method that returns name of item type
     * @return string of name
     */
    public String getName() {
        return name;
    }

}

