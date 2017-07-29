package com.gcteam.yamblz.homework.pageobjects;

import android.support.test.espresso.Espresso;

import com.gcteam.yamblz.homework.R;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Kim Michael on 29.07.17
 */
public class WeatherPage extends PageObject {

    private static WeatherPage instance;

    public static WeatherPage obtain() {
        if (instance == null)
            instance = new WeatherPage();
        return instance;
    }

    @Override
    public WeatherPage assertOn() {
        Espresso.onView(withId(R.id.icon)).check(matches(isDisplayed()));
        return instance;
    }
}
