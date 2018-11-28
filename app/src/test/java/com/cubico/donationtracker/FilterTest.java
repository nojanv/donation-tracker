package com.cubico.donationtracker;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.widget.Filter;

import com.cubico.donationtracker.POJOs.DonationItem;
import com.cubico.donationtracker.POJOs.ItemType;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.assertNull;
import androidx.test.core.app.ApplicationProvider;

public class FilterTest {
    private DonationAdapter adapter;
    private List<DonationItem> donations;

    private String match;
    private String noMatch;
    private int matchSize;
    private int noMatchSize;

    @Before
    public void setUp() {
        donations = new ArrayList<>();
        donations.add(new DonationItem("Chicken", ItemType.FOOD));
        donations.add(new DonationItem("Shirt", ItemType.CLOTHING));
        donations.add(new DonationItem("TV", ItemType.ELECTRONICS));
        donations.add(new DonationItem("TV", ItemType.HOUSEHOLD_ITEMS));
        adapter = new DonationAdapter(donations, null);
        match = "t";
        noMatch = "q";
        matchSize = 0;
        noMatchSize = 0;
    }

    @Test
    public void testMatch() {
        adapter.getFilter().filter(match);
        assertNotNull(adapter.getFilter());
        assertEquals(matchSize, adapter.getFilter().getFiltered().size());
    }
    @Test
    public void testNoMatch() {
        System.out.println("entered method");
        String className = "com.cubico.donationtracker.DonationAdapter";
        DonationAdapter.ValueFilter filter = adapter.getFilter();
        try {
            Class donationAdapter = Class.forName(className);
            Method performFilter = donationAdapter.getMethod("performFiltering", Class.forName("java.lang.CharSequence"));
            Method publish = donationAdapter.getMethod("publishResults", Class.forName("java.lang.CharSequence"), Class.forName("android.widget.Filter.FilterResults"));
            publish.invoke(filter, match, performFilter.invoke(filter, match));
            System.out.println("Filtered");

        } catch (Exception e) {

        }
        assertNotNull(filter);
        assertEquals(noMatchSize, filter.getFiltered().size());
        assertTrue(filter.isEmpty());
    }
}
