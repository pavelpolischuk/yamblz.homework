package com.gcteam.yamblz.homework.data.local.weather;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.object.StoredFullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Single;

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


    public void saveWeather(FullWeatherReport fullWeatherReport) {
        appDatabase.fullWeatherReportDAO().insert(new StoredFullWeatherReport(
                fullWeatherReport.getLat(),
                fullWeatherReport.getLng(),
                gson.toJson(fullWeatherReport.getForecastData()),
                gson.toJson(fullWeatherReport.getWeatherData())
        ));
    }

    public Single<ForecastData> getForecast(double lat, double lng) {
        return appDatabase.fullWeatherReportDAO().get(lat, lng)
                .map(storedFullWeatherReport ->
                        gson.fromJson(storedFullWeatherReport.getForecastDataJson(),
                                ForecastData.class));
    }

    public Single<WeatherData> getCurrentWeather(double lat, double lng) {
        return appDatabase.fullWeatherReportDAO().get(lat, lng)
                .map(storedFullWeatherReport ->
                        gson.fromJson(storedFullWeatherReport.getWeatherDataJson(),
                                WeatherData.class));
    }

}
