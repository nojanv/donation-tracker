package com.cubico.donationtracker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//@RunWith(AndroidJUnit4.class)
public class ModelTest {

    @Rule
    public ActivityTestRule<Model> mActivityTestRule = new ActivityTestRule<Model>(Model.class);

    private Model mActivity = null;

    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();

    }

    @Test
    public void validDonationTest() {

        assertTrue(mActivity.isValidDonation(5.99f, "Jesus", "Jesus Statue"));
        assertTrue(mActivity.isValidDonation(3f, "Jesus", "Jesus Statue"));
        //FALSE CONDITIONS
        assertFalse(mActivity.isValidDonation(0, "Jesus", "Jesus Statue"));
        assertFalse(mActivity.isValidDonation(5.99f, "", "Jesus Statue"));
        assertFalse(mActivity.isValidDonation(5.99f, "Jesus", ""));
    }

    @After
    public void tearDown() throws Exception {

        mActivity = null;
    }

//    private static Model model = new Model();
//
//    @Test
//    public void validDonationTest() {
//        //TRUE CONDITIONS
//        assertTrue(model.isValidDonation(5.99f, "Jesus", "Jesus Statue"));
//        assertTrue(model.isValidDonation(3f, "Jesus", "Jesus Statue"));
//        //FALSE CONDITIONS
//        assertTrue(model.isValidDonation(0, "Jesus", "Jesus Statue"));
//        assertTrue(model.isValidDonation(5.99f, "", "Jesus Statue"));
//        assertTrue(model.isValidDonation(5.99f, "Jesus", ""));
//
//    }


}


