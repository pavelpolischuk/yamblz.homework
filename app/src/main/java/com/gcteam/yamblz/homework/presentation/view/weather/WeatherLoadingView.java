package com.gcteam.yamblz.homework.presentation.view.weather;

import com.gcteam.yamblz.homework.data.WeatherData;

/**
 * Created by turist on 16.07.2017.
 */

public interface WeatherLoadingView {
    void loaded(WeatherData weather);
    void loadingError();
    void loadingStart();
}
