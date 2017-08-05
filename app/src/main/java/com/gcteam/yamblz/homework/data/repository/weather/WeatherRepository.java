package com.gcteam.yamblz.homework.data.repository.weather;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherRepository {

    private WeatherStorage weatherStorage;
    private WeatherService weatherService;

    @Inject
    public WeatherRepository(WeatherStorage weatherStorage,
                             WeatherService weatherService) {
        this.weatherStorage = weatherStorage;
        this.weatherService = weatherService;
    }

    public Single<WeatherData> startRefresh() {
        return weatherService
                .currentWeather(Locale.getDefault().getLanguage());
    }
}