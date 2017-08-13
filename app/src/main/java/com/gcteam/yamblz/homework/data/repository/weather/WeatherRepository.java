package com.gcteam.yamblz.homework.data.repository.weather;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.mapper.ForecastResponseMapper;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherRepository {

    private final WeatherStorage weatherStorage;
    private final WeatherService weatherService;
    private final PreferencesManager preferencesManager;

    @Inject
    public WeatherRepository(WeatherStorage weatherStorage,
                             WeatherService weatherService,
                             PreferencesManager preferencesManager) {
        this.weatherStorage = weatherStorage;
        this.weatherService = weatherService;
        this.preferencesManager = preferencesManager;
    }

    @WorkerThread
    @NonNull
    public Single<WeatherData> getWeather(double lat, double lng) {
        Timber.d("Getting weather for %1.0f lat and %1.0f lng", lat, lng);
        return weatherStorage.getCurrentWeather(lat, lng).onErrorResumeNext(
                weatherService.getCurrentWeather(lat, lng, Locale.getDefault().getLanguage()));
    }

    @WorkerThread
    @NonNull
    public Single<ForecastData> getForecast(double lat, double lng) {
        Timber.d("Getting forecast for %1.0f lat and %1.0f lng", lat, lng);
        return weatherStorage.getForecast(lat, lng).onErrorResumeNext(
                weatherService
                        .getForecast(lat, lng, Locale.getDefault().getLanguage())
                        .map(ForecastResponseMapper::toForecastData)
        );
    }

    @WorkerThread
    public void saveWeather(@NonNull FullWeatherReport fullWeatherReport) {
        Timber.d("Full weather report is being saved for %1.0f lat and %1.0f lng",
                fullWeatherReport.getLat(), fullWeatherReport.getLng());
        weatherStorage.saveWeather(fullWeatherReport);
        preferencesManager.saveLastSyncTime(System.currentTimeMillis());
    }
}