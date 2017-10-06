package com.gcteam.yamblz.homework.presentation.view.weather;

import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.presentation.BaseView;

/**
 * Created by turist on 16.07.2017.
 */

public interface WeatherView extends BaseView {
    void showErrorMessage();

    void showLoadingStarted();

    void showFullWeather(FullWeatherReport fullWeatherReport);

    void changeTitle(String title);

    void showEmptyView();
}
