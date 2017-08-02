package com.gcteam.yamblz.homework.presentation.view.weather;

import com.gcteam.yamblz.homework.data.WeatherData;
import com.gcteam.yamblz.homework.presentation.BaseView;

/**
 * Created by turist on 16.07.2017.
 */

public interface WeatherView extends BaseView {
    void showWeatherData(WeatherData weather);

    void showErrorMessage();

    void showLoadingStarted();
}
