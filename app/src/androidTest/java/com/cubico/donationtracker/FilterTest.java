package com.cubico.donationtracker;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import com.cubico.donationtracker.POJOs.DonationItem;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FilterTest {
    @Rule
    public ActivityTestRule<AllDonations> mActivityRule =
            new ActivityTestRule<>(AllDonations.class);

    @Before
    public void setUp() {
        onView(withId(R.id.donationSearch2)).perform(typeText("s"), closeSoftKeyboard());
    }

    @Test
    public void testSearch(){
        onView(withId(R.id.donationSearch2)).perform(typeText("st"), closeSoftKeyboard());
        onData(withName("sticks")).perform(click());
        onView(withId(R.id.donation_name)).check(matches(withText("sticks")));
    }
    public static Matcher withName(final String name){
        return new TypeSafeMatcher<DonationItem>(){
            @Override
            public boolean matchesSafely(DonationItem d) {
                return name.equals(d.getName());
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}
