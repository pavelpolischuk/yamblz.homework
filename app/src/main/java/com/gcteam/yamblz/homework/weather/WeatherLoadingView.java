package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.weather.api.WeatherData;

/**
 * Created by turist on 16.07.2017.
 */

public interface WeatherLoadingView {
    void loaded(WeatherData weather);
    void loadingError();
    void loadingStart();
}
