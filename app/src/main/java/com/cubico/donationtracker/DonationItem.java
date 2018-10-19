package com.cubico.donationtracker;

import android.content.ClipData;

public class DonationItem {

    private String name;
    private int quantity;
    private ItemType itemType;

    public DonationItem(String name, int quantity, ItemType itemType) {
        this.name = name;
        this.quantity = quantity;
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