package com.gcteam.yamblz.homework.data;

import com.gcteam.yamblz.homework.presentation.view.weather.WeatherView;
import com.gcteam.yamblz.homework.data.network.WeatherService;
import com.gcteam.yamblz.homework.data.local.WeatherStorage;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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