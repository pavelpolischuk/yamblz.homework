package com.gcteam.yamblz.homework.data.network.weather;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.data.mapper.ForecastResponseMapper;
import com.gcteam.yamblz.homework.data.mapper.WeatherResponseMapper;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import java.util.Arrays;
import java.util.HashSet;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

import static com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi.API_KEY;

/**
 * Created by turist on 16.07.2017.
 */

public class OpenWeatherService implements WeatherService {

    private static final String METRIC_UNITS = "metric";

    private static final HashSet<String> codes = new HashSet<>(Arrays.asList(
            "ar", "bg", "ca", "cz", "de", "el", "en", "fa", "fi", "fr", "gl", "hr", "hu",
            "it", "ja", "kr", "la", "lt", "mk", "nl", "pl", "pt", "ro", "ru", "se", "sk",
            "sl", "es", "tr", "ua", "vi", "zh_cn", "zh_tw"));

    private OpenWeatherMapApi api;

    @Inject
    public OpenWeatherService(OpenWeatherMapApi api) {
        this.api = api;
    }


    private static String checkLangCode(String lang) {
        if (codes.contains(lang)) {
            return lang;
        }

        return "en";
    }

    @WorkerThread
    @NonNull
    public Single<WeatherData> getCurrentWeather(double lat, double lng, String lang) {
        Timber.d("Getting current weather for %1.0f lat and %1.0f lng", lat, lng);
        return api.weatherByLatLng(API_KEY,
                lat,
                lng,
                METRIC_UNITS,
                checkLangCode(lang))
                .map(WeatherResponseMapper::toWeatherData);
    }

    @WorkerThread
    @NonNull
    public Single<ForecastData> getForecast(double lat, double lng, String lang) {
        Timber.d("Getting forecast for %1.0f lat and %1.0f lng", lat, lng);
        return api.forecastByLatLng(API_KEY,
                lat, lng,
                METRIC_UNITS,
                checkLangCode(lang))
                .map(ForecastResponseMapper::toForecastData);
    }
}
