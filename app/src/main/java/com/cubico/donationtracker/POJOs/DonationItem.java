package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

public class DonationItem implements Parcelable {

    private String name;
    private String timeStamp;
    private String location;
    private String fullDescription;
    private float value;
    private ItemType itemType;

    public static final Parcelable.Creator<DonationItem> CREATOR = new Parcelable.Creator<DonationItem>() {
        public DonationItem createFromParcel(Parcel in) {
            return new DonationItem(in);
        }

        public DonationItem[] newArray(int size) {
            return new DonationItem[size];
        }
    };

    public DonationItem() {

    }

    public DonationItem(String name, String timeStamp, String location, String fullDescription, float value, ItemType itemType) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.location = location;
        this.fullDescription = fullDescription;
        this.value = value;
        this.itemType = itemType;
    }

    public DonationItem(Parcel in) {
        this.name = in.readString();
        this.timeStamp = in.readString();
        this.location = in.readString();
        this.fullDescription = in.readString();
        this.value = in.readFloat();
        this.itemType = in.readParcelable(ItemType.class.getClassLoader());
    }



    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(timeStamp);
        dest.writeString(location);
        dest.writeString(fullDescription);
        dest.writeFloat(value);
        dest.writeParcelable(itemType, flags);
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
