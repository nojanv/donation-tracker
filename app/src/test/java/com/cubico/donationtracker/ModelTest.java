package com.cubico.donationtracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void isValidDonation() {
        assertTrue(model.isValidDonation(5.99f, "Jesus", "Jesus Statue"));
        assertTrue(model.isValidDonation(3f, "Jesus", "Jesus Statue"));
        //FALSE CONDITIONS
        assertFalse(model.isValidDonation(0, "Jesus", "Jesus Statue"));
        assertFalse(model.isValidDonation(5.99f, "", "Jesus Statue"));
        assertFalse(model.isValidDonation(5.99f, "Jesus", ""));

    }
}