package com.cubico.donationtracker;

import android.app.Activity;

/**
 * Class for validation, i.e. donation items added are acceptable
 */
public class Model extends Activity {
    private static final Model ourInstance = new Model();

    /**
     * Used to get instance of model
     * @return an instance of model
     */
    public static Model getInstance() {
        return ourInstance;
    }

    /**
     * Constructor for model
     */
    public Model() {

    }

    /** This method checks if the values entered for adding a donation item are acceptable
     * @param value the value of the donation item
     * @param name the name/short description of the item
     * @param description the long description of the item
     * @return boolean value on if it is a valid donation or not
     */
    public boolean isValidDonation(float value, String name, String description) {
        if (value == 0) {
            return false;
        } else if (name.length() == 0) {
            return false;
        } else if (description.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
}