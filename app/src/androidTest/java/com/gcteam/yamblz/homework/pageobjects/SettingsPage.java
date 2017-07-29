package com.gcteam.yamblz.homework.pageobjects;

import android.support.test.espresso.Espresso;

import com.gcteam.yamblz.homework.R;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Kim Michael on 29.07.17
 */
public class SettingsPage extends PageObject {

    private static SettingsPage instance;

    public static SettingsPage obtain() {
        if (instance == null)
            instance = new SettingsPage();
        return instance;
    }

    @Override
    public PageObject assertOn() {
        Espresso.onView(withText(R.string.update_interval)).check(matches(isDisplayed()));
        return instance;
    }
}
