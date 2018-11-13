package com.cubico.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * A junit to test the ability for the isValidEmail method in LoginActivity to function properly
 */
@RunWith(AndroidJUnit4.class)
public class EmailAuthenticatorTest {
    /**
     * Checks if email is valid by calling isValidEmail method within LoginActivity
     */
    @Test
    public void email_isValid() {
        LoginActivity login = new LoginActivity();
        //TRUE CONDITIONS
        assertTrue(login.isEmailValid("name@email.com"));
        assertTrue(login.isEmailValid("website.name@email.com"));
        //FALSE CONDITIONS
        assertFalse(login.isEmailValid(""));
        assertFalse(login.isEmailValid("name"));
        assertFalse(login.isEmailValid("@."));
        assertFalse(login.isEmailValid("@com"));
    }
}