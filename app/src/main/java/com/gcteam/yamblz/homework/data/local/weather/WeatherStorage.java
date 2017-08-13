package com.gcteam.yamblz.homework.data.local.weather;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.object.StoredFullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherStorage {


    private final AppDatabase appDatabase;
    private final Gson gson;

    @Inject
    public WeatherStorage(AppDatabase appDatabase,
                          Gson gson) {
        this.appDatabase = appDatabase;
        this.gson = gson;
    }

    @WorkerThread
    public void saveWeather(@NonNull FullWeatherReport fullWeatherReport) {
        Timber.d("Saving full weather with %1.0f lat and %1.0f lng", fullWeatherReport.getLat(),
                fullWeatherReport.getLng());
        appDatabase.fullWeatherReportDAO().insert(new StoredFullWeatherReport(
                fullWeatherReport.getLat(),
                fullWeatherReport.getLng(),
                gson.toJson(fullWeatherReport.getForecastData()),
                gson.toJson(fullWeatherReport.getWeatherData()),
                System.currentTimeMillis() / 1000
        ));
    }

    @WorkerThread
    @NonNull
    public Single<ForecastData> getForecast(double lat, double lng) {
        Timber.d("Getting forecast for %1.0f lat and %1.0f lng", lat, lng);
        return appDatabase.fullWeatherReportDAO().get(lat, lng)
                .map(storedFullWeatherReport ->
                        gson.fromJson(storedFullWeatherReport.getForecastDataJson(),
                                ForecastData.class));
    }

    @WorkerThread
    @NonNull
    public Single<WeatherData> getCurrentWeather(double lat, double lng) {
        Timber.d("Getting current weather for %1.0f lat and %1.0f lng", lat, lng);
        return appDatabase.fullWeatherReportDAO().get(lat, lng)
                .map(storedFullWeatherReport ->
                        gson.fromJson(storedFullWeatherReport.getWeatherDataJson(),
                                WeatherData.class));
    }

}
