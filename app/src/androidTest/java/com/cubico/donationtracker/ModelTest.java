package com.cubico.donationtracker;

        import android.support.test.runner.AndroidJUnit4;

        import com.cubico.donationtracker.POJOs.Model;

        import org.junit.Test;
        import org.junit.runner.RunWith;

        import static junit.framework.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;

/**
 * A junit to test the ability for the isValidEmail method in LoginActivity to function properly
 */
@RunWith(AndroidJUnit4.class)
public class ModelTest {
    /**
     * Checks if email is valid by calling isValidEmail method within LoginActivity
     */
    @Test
    public void model_isValid() {
        //TRUE CONDITIONS
        assertTrue(validDonation(5.99f, "Jesus", "Jesus Statue"));
        assertTrue(validDonation(3f, "Jesus", "Jesus Statue"));
        //FALSE CONDITIONS
        assertFalse(validDonation(0, "Jesus", "Jesus Statue"));
        assertFalse(validDonation(5.99f, "", "Jesus Statue"));
        assertFalse(validDonation(5.99f, "Jesus", ""));
    }

    public boolean validDonation(float value, String name, String description) {
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
