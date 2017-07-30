package com.gcteam.yamblz.homework.pageobjects;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;

import com.gcteam.yamblz.homework.R;

import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Kim Michael on 29.07.17
 */
public class AboutPage extends PageObject {

    private static AboutPage instance;

    public static AboutPage obtain() {
        if (instance == null)
            instance = new AboutPage();
        return instance;
    }

    @Override
    public AboutPage assertOn() {
        Espresso.onView(withId(R.id.about_message)).check(matches(isDisplayed()));
        return this;
    }
}
