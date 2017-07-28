package com.gcteam.yamblz.homework.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.weather.api.WeatherData;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherStorage {

    private static final String CURRENT_WEATHER_KEY = "current_weather_key";

    private final Subject<WeatherData> weatherSubject = PublishSubject.create();
    private SharedPreferences preferences;

    @Inject
    public WeatherStorage(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    public Observable<WeatherData> lastWeather() {
        return weatherSubject;
    }

    @NonNull
    public Consumer<WeatherData> updateLastWeather() {
        return new Consumer<WeatherData>() {
            @Override
            public void accept(@NonNull WeatherData weather) throws Exception {
                updateLastWeather(weather);
            }
        };
    }

    @NonNull
    public void updateLastWeather(WeatherData weather) {
        save(weather);
        weatherSubject.onNext(weather);
    }

    public void save(WeatherData weather) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CURRENT_WEATHER_KEY, new Gson().toJson(weather));
        editor.apply();
    }

    @Nullable
    public WeatherData load() {
        return new Gson().fromJson(preferences.getString(CURRENT_WEATHER_KEY, null), WeatherData.class);
    }
}
