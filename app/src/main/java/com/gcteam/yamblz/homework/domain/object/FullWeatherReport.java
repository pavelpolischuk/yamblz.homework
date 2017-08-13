package com.gcteam.yamblz.homework.domain.object;

import android.support.annotation.NonNull;

/**
 * Created by Kim Michael on 08.08.17
 */
public class FullWeatherReport {

    private final double lat;
    private final double lng;
    @NonNull
    private final ForecastData forecastData;
    @NonNull
    private final WeatherData weatherData;

    private final long lastSyncTime;

    public FullWeatherReport(double lat, double lng,
                             @NonNull ForecastData forecastData,
                             @NonNull WeatherData weatherData,
                             long lastSyncTime) {
        this.forecastData = forecastData;
        this.weatherData = weatherData;
        this.lat = lat;
        this.lng = lng;
        this.lastSyncTime = lastSyncTime;
    }

    @NonNull
    public ForecastData getForecastData() {
        return forecastData;
    }

    @NonNull
    public WeatherData getWeatherData() {
        return weatherData;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public long getLastSyncTime() {
        return lastSyncTime;
    }
}
