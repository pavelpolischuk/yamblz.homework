package com.gcteam.yamblz.homework.presentation.navigation.main;

import com.gcteam.yamblz.homework.domain.object.WeatherData;

/**
 * Created by turist on 14.07.2017.
 */

public interface MainRouter {
    void showWeather();

    void showSettings();

    void showAbout();

    void showDetailedWeather(WeatherData weatherData, boolean isTwoPane);
}
