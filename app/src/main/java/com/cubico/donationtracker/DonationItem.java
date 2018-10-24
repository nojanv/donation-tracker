package com.cubico.donationtracker;

import android.os.Parcel;
import android.os.Parcelable;

public class DonationItem implements Parcelable {

    private String name;
    private int quantity;
    private ItemType itemType;

    public static final Parcelable.Creator<DonationItem> CREATOR = new Parcelable.Creator<DonationItem>() {
        public DonationItem createFromParcel(Parcel in) {
            return new DonationItem(in);
        }

        public DonationItem[] newArray(int size) {
            return new DonationItem[size];
        }
    };


    public DonationItem(String name, int quantity, ItemType itemType) {
        this.name = name;
        this.quantity = quantity;
        this.itemType = itemType;
    }
    public DonationItem(Parcel in) {
        this.name = in.readString();
        this.quantity = in.readInt();
        this.itemType = in.readParcelable(ItemType.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(quantity);
        dest.writeParcelable(itemType, flags);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}