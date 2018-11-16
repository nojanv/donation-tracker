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
        LoginActivity login = new LoginActivity();
        //TRUE CONDITIONS
        assertTrue(login.isPasswordValid("password1!"));
        assertTrue(login.isPasswordValid("PassWoowrD@#!23"));
        //FALSE CONDITIONS
        assertFalse(login.isPasswordValid(""));
        assertFalse(login.isPasswordValid("pass"));
        assertFalse(login.isPasswordValid("p!@3"));
        assertFalse(login.isPasswordValid("password!"));
        assertFalse(login.isPasswordValid("password2"));
    }
}