package com.cubico.donationtracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test for validation class 'Model'
 */
public class ModelTest {

    Model model;

    /**
     * Sets up the junit by creating a model
     * @throws Exception throws when model is null
     */
    @Before
    public void setUp() throws Exception {
        model = new Model();
    }

    /**
     * Tears down junit once it is completed
     * @throws Exception throws exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test the validDonation method
     */
    @Test
    public void isValidDonation() {
        //TRUE CONDITIONS
        assertTrue(model.isValidDonation(5.99f, "Jesus", "Jesus Statue"));
        assertTrue(model.isValidDonation(3f, "Jesus", "Jesus Statue"));
        //FALSE CONDITIONS
        assertFalse(model.isValidDonation(0, "Jesus", "Jesus Statue"));
        assertFalse(model.isValidDonation(5.99f, "", "Jesus Statue"));
        assertFalse(model.isValidDonation(5.99f, "Jesus", ""));
    }
}