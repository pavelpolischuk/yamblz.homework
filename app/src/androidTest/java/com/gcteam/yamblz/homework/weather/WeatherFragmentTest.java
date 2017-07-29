package com.gcteam.yamblz.homework.weather;

import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gcteam.yamblz.homework.main.MainActivity;
import com.gcteam.yamblz.homework.pageobjects.MainPage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by Kim Michael on 29.07.17
 */
@RunWith(AndroidJUnit4.class)
public class WeatherFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        MainPage.obtain()
//                .openDrawer()
                .moveToWeather();
    }


}
