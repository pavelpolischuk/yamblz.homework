package com.gcteam.yamblz.homework.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.weather.api.Weather;
import com.google.gson.Gson;

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

    private final Subject<Weather> weatherSubject = PublishSubject.create();
    private SharedPreferences preferences;

    private static final WeatherStorage impl = new WeatherStorage();

    public static void init(Context context) {
        impl.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static WeatherStorage get() {
        return impl;
    }

    @NonNull
    public Observable<Weather> lastWeather() {
        return weatherSubject;
    }

    @NonNull
    public Consumer<Weather> updateLastWeather() {
        return new Consumer<Weather>() {
            @Override
            public void accept(@NonNull Weather weather) throws Exception {
                updateLastWeather(weather);
            }
        };
    }

    @NonNull
    public void updateLastWeather(Weather weather) {
        save(weather);
        weatherSubject.onNext(weather);
    }

    public void save(Weather weather) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CURRENT_WEATHER_KEY, new Gson().toJson(weather));
        editor.apply();
    }

    @Nullable
    public Weather load() {
        return new Gson().fromJson(preferences.getString(CURRENT_WEATHER_KEY, null), Weather.class);
    }
}
