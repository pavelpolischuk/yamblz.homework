package com.gcteam.yamblz.homework.domain.object;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Kim Michael on 06.08.17
 */
public class ForecastData {
    @NonNull
    private final String cityName;
    @NonNull
    private final List<WeatherData> forecast;

    public ForecastData(@NonNull String cityName,
                        @NonNull List<WeatherData> forecast) {
        this.cityName = cityName;
        this.forecast = forecast;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    @NonNull
    public List<WeatherData> getForecast() {
        return forecast;
    }

}
