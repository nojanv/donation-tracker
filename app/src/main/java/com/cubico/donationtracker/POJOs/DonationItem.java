package com.cubico.donationtracker.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Donation Item Object Class
 */
public class DonationItem implements Parcelable {

    private String name;
    private String timeStamp;
    private String location;
    private String fullDescription;
    private float value;
    private ItemType itemType;

    public static final Parcelable.Creator<DonationItem> CREATOR =
            new Parcelable.Creator<DonationItem>() {
        public DonationItem createFromParcel(Parcel in) {
            return new DonationItem(in);
        }

        public DonationItem[] newArray(int size) {
            return new DonationItem[size];
        }
    };

    /**
     * Donation Item Constructor
     * @param name of item
     * @param timeStamp of item
     * @param location of item
     * @param fullDescription of item
     * @param value of item
     * @param itemType of item
     */
    public DonationItem(String name, String timeStamp, String location,
                        String fullDescription, float value, ItemType itemType) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.location = location;
        this.fullDescription = fullDescription;
        this.value = value;
        this.itemType = itemType;
    }

    /**
     * Donation Item Constructor
     * @param in is parcel we use to extract relevant data
     */
    private DonationItem(Parcel in) {
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

    /**
     * gets timestamp of item
     * @return string rep timestamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * sets timestamp of item
     * @param timeStamp is what we want to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * gets location of item
     * @return string rep location
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets location of item
     * @param location of what we want to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * gets description of item
     * @return string representation of desc.
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * sets fullDescription of item
     * @param fullDescription of what we want to set
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * gets value of item
     * @return string representation of value
     */
    public float getValue() {
        return value;
    }

    /**
     * sets value of item
     * @param value of what we want to set
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * gets value of name
     * @return string representation of name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name of item
     * @param name of what we want to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets value of itemType
     * @return string representation of itemType
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * sets itemType of item
     * @param itemType of what we want to set
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
