package com.gcteam.yamblz.homework.weather;

import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.weather.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.weather.api.Weather;
import com.gcteam.yamblz.homework.weather.api.WeatherMapper;
import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.HashSet;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private WeatherMapper weatherMapper;
    private PreferencesManager preferencesManager;

    public static WeatherService get(PreferencesManager preferencesManager) {
        if(instance == null) {
            instance = new WeatherService();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            instance.api = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(OpenWeatherMapApi.class);
            instance.weatherMapper = new WeatherMapper();
            instance.preferencesManager = preferencesManager;
        }

        return instance;
    }

    public Single<Weather> currentWeather(int cityId, String units, String lang) {
        return api.weatherByCityId(API_KEY, cityId, checkUnitsType(units), checkLangCode(lang))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(weatherMapper);
    }

    public Single<Weather> currentWeather(LatLng latLng, String units, String lang) {
        return api.weatherByLatLng(API_KEY,
                Double.toString(latLng.latitude),
                Double.toString(latLng.longitude),
                checkUnitsType(units),
                checkLangCode(lang))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(weatherMapper);
    }

    public Single<Weather> currentWeather(String lang) {
        return currentWeather(new LatLng(preferencesManager.getLat(),
                                        preferencesManager.getLng()),
                METRIC_UNITS,
                lang);
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
