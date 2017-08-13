package com.gcteam.yamblz.homework.data.repository.weather;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherRepositoryImpl {

    private final WeatherStorage weatherStorage;
    private final WeatherService weatherService;

    @Inject
    public WeatherRepositoryImpl(WeatherStorage weatherStorage,
                                 WeatherService weatherService) {
        this.weatherStorage = weatherStorage;
        this.weatherService = weatherService;
    }

    @WorkerThread
    @NonNull
    public Single<FullWeatherReport> getFullWeatherReport(double lat, double lng) {
        Timber.d("Getting full weather report for %1.0f lat and %1.0f lng", lat, lng);
        Single<FullWeatherReport> storageReport = weatherStorage.getFullWeatherReport(lat, lng);
        Single<FullWeatherReport> serviceReport = Single.zip(
                weatherService.getCurrentWeather(lat, lng, Locale.getDefault().getLanguage()),
                weatherService.getForecast(lat, lng, Locale.getDefault().getLanguage()),
                (weatherData, forecastData) ->
                        new FullWeatherReport(lat, lng,
                                forecastData, weatherData,
                                System.currentTimeMillis() / 1000)
        );
        return storageReport.onErrorResumeNext(serviceReport);
    }

    @WorkerThread
    public void saveWeather(@NonNull FullWeatherReport fullWeatherReport) {
        Timber.d("Full weather report is being saved for %1.0f lat and %1.0f lng",
                fullWeatherReport.getLat(), fullWeatherReport.getLng());
        weatherStorage.saveWeather(fullWeatherReport);
    }
}