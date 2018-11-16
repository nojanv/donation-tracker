package com.cubico.donationtracker.POJOs;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ModelTest {

    @Test
    public void validDonation() {
        Model model = new Model();
        //TRUE CONDITIONS
        assertTrue(model.validDonation(5.99f, "Jesus", "Jesus Statue"));
        assertTrue(model.validDonation(3f, "Jesus", "Jesus Statue"));
        //FALSE CONDITIONS
        assertTrue(model.validDonation(0, "Jesus", "Jesus Statue"));
        assertTrue(model.validDonation(5.99f, "", "Jesus Statue"));
        assertTrue(model.validDonation(5.99f, "Jesus", ""));

    }
}


//    @Rule
////    public ActivityTestRule<Model> mActivityTestRule = new ActivityTestRule<Model>(Model.class);
////
////    private Model mActivity = null;
////
////    @Before
////    public void setUp() throws Exception {
////
////        mActivity = mActivityTestRule.getActivity();
////
////    }
////
////    @After
////    public void tearDown() throws Exception {
////
////        mActivity = null;
////    }
