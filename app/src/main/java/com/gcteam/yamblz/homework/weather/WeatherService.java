package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.weather.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.weather.api.Weather;
import com.gcteam.yamblz.homework.weather.api.WeatherMapper;

import java.util.Arrays;
import java.util.HashSet;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherService {

    private static final String API_BASE_URL = "http://api.openweathermap.org/data/";
    private static final String API_KEY = "8fd5656437393710869297fbf372df49";

    private static final int MOSCOW_CITY_ID = 524901;
    private static final String METRIC_UNITS = "metric";

    private static final HashSet<String> units = new HashSet<>(Arrays.asList(
            "standard", "metric", "imperial"));
    private static final HashSet<String> codes = new HashSet<>(Arrays.asList(
            "ar", "bg", "ca", "cz", "de", "el", "en", "fa", "fi", "fr", "gl", "hr", "hu",
            "it", "ja", "kr", "la", "lt", "mk", "nl", "pl", "pt", "ro", "ru", "se", "sk",
            "sl", "es", "tr", "ua", "vi", "zh_cn", "zh_tw"));

    private static WeatherService instance;

    private OpenWeatherMapApi api;

    public static WeatherService get() {
        if(instance == null) {
            instance = new WeatherService();
            instance.api = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OpenWeatherMapApi.class);
        }

        return instance;
    }

    public Single<Weather> currentWeather(int cityId, String units, String lang) {
        return api.current(API_KEY, cityId, checkUnitsType(units), checkLangCode(lang))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new WeatherMapper());
    }

    public Single<Weather> currentWeather(String lang) {
        return currentWeather(MOSCOW_CITY_ID, METRIC_UNITS, lang);
    }

    public static String checkUnitsType(String unitsType) {
        if(units.contains(unitsType)) {
            return unitsType;
        }

        return "standard";
    }

    public static String checkLangCode(String lang) {
        if(codes.contains(lang)) {
            return lang;
        }

        return "en";
    }
}
