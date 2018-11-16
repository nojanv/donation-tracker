package com.cubico.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.cubico.donationtracker.LoginActivity;

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
        //TRUE CONDITIONS
        assertTrue(LoginActivity.isEmailValid("name@email.com"));
        assertTrue(LoginActivity.isEmailValid("website.name@email.com"));
        //FALSE CONDITIONS
        assertFalse(LoginActivity.isEmailValid(""));
        assertFalse(LoginActivity.isEmailValid("name"));
        assertFalse(LoginActivity.isEmailValid("@."));
        assertFalse(LoginActivity.isEmailValid("@com"));
    }
}