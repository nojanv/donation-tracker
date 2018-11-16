package com.cubico.donationtracker;

import android.os.Looper;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * A junit to test the ability for the isValidPassword method in LoginActivity to function properly
 */
@RunWith(AndroidJUnit4.class)
public class PasswordAuthenticatorTest {
    /**
     * Checks if password is valid by calling isValidPassword method within LoginActivity
     */
    @Test
    public void password_isValid() {
        //TRUE CONDITIONS
        assertTrue(LoginActivity.isPasswordValid("password1!"));
        assertTrue(LoginActivity.isPasswordValid("PassWoowrD@#!23"));
        //FALSE CONDITIONS
        assertFalse(LoginActivity.isPasswordValid(""));
        assertFalse(LoginActivity.isPasswordValid("pass"));
        assertFalse(LoginActivity.isPasswordValid("p!@3"));
        assertFalse(LoginActivity.isPasswordValid("password!"));
        assertFalse(LoginActivity.isPasswordValid("password2"));
    }
}