package com.gcteam.yamblz.homework;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gcteam.yamblz.homework.pageobjects.AboutPage;
import com.gcteam.yamblz.homework.pageobjects.MainPage;
import com.gcteam.yamblz.homework.pageobjects.SettingsPage;
import com.gcteam.yamblz.homework.pageobjects.WeatherPage;
import com.gcteam.yamblz.homework.presentation.view.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Kim Michael on 29.07.17
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
    }

    @Test
    public void weatherFragmentIsShownOnStart() {
        WeatherPage.obtain()
                .assertOn();
    }

    @Test
    public void userCanSwitchToSettingsSection() {
        MainPage.obtain()
                .moveToSettings();

        SettingsPage.obtain()
                .assertOn();
    }

    @Test
    public void userCanSwitchToAboutSection() {
        MainPage.obtain()
                .moveToAbout();

        AboutPage.obtain()
                .assertOn();
    }
}
