package com.gcteam.yamblz.homework.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.weather.api.WeatherData;
import com.gcteam.yamblz.homework.weather.api.dto.Weather;
import com.google.gson.Gson;

/**
 * Created by Kim Michael on 26.07.17
 */
public class PreferencesManager {


    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static final String CURRENT_WEATHER_KEY = "current_weather_key";


    public PreferencesManager(Context context, Gson gson) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
    }

    public double getLat() {
        return Double.valueOf(sharedPreferences.getString(SettingsInteractor.LAT_KEY, "0d"));
    }

    public double getLng() {
        return Double.valueOf(sharedPreferences.getString(SettingsInteractor.LNG_KEY, "0d"));
    }

    public void putCurrentWeather(WeatherData weather) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CURRENT_WEATHER_KEY, gson.toJson(weather));
        editor.apply();
    }

    public WeatherData loadCachedWeather() {
        return gson.fromJson(sharedPreferences.getString(CURRENT_WEATHER_KEY, null), WeatherData.class);
    }
}
