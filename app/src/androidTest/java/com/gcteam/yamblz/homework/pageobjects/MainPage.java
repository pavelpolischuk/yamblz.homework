package com.gcteam.yamblz.homework.pageobjects;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;

import com.gcteam.yamblz.homework.R;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Kim Michael on 29.07.17
 */
public class MainPage extends PageObject {

    private static MainPage instance = null;

    public static MainPage obtain() {
        if (instance == null)
            instance = new MainPage();
        return instance;
    }

    @Override
    public MainPage assertOn() {
        return this;
    }

    public MainPage openDrawer() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
        return instance;
    }

    public MainPage moveToWeather() {
        openDrawer();
        onView(withId(R.id.nav_weather)).perform(click());
        return this;
    }

    public MainPage moveToSettings() {
        openDrawer();
        onView(withId(R.id.nav_settings)).perform(click());
        return this;
    }

    public MainPage moveToAbout() {
        openDrawer();
        onView(withId(R.id.nav_about)).perform(click());
        return this;
    }
}
