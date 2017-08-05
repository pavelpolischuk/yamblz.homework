package com.gcteam.yamblz.homework.utils;

import android.content.SharedPreferences;

import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;

import static com.gcteam.yamblz.homework.presentation.presenter.settings.SettingsPresenter.CHOOSE_CITY_KEY;
import static com.gcteam.yamblz.homework.presentation.presenter.settings.SettingsPresenter.LAT_KEY;
import static com.gcteam.yamblz.homework.presentation.presenter.settings.SettingsPresenter.LNG_KEY;
import static com.gcteam.yamblz.homework.presentation.presenter.settings.SettingsPresenter.UPDATE_INTERVAL_KEY;

/**
 * Created by Kim Michael on 26.07.17
 */
public class PreferencesManager {


    private SharedPreferences sharedPreferences;
    private Gson gson;

    public static final String CURRENT_WEATHER_KEY = "current_weather_key";

    public static final String DEFAULT_UPDATE_INTERVAL = "3600";
    public static final String DEFAULT_CHOSEN_CITY = "Moscow";


    public PreferencesManager(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public double getLat() {
        return Double.valueOf(sharedPreferences.getString(LAT_KEY, "0d"));
    }

    public double getLng() {
        return Double.valueOf(sharedPreferences.getString(LNG_KEY, "0d"));
    }

    public void savePlace(Place place) {
        sharedPreferences.edit()
                .putString(LAT_KEY, Double.toString(place.getLatLng().latitude))
                .putString(LNG_KEY, Double.toString(place.getLatLng().longitude))
                .putString(CHOOSE_CITY_KEY, place.getName().toString())
                .apply();
    }

    public void putCurrentWeather(WeatherData weather) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CURRENT_WEATHER_KEY, gson.toJson(weather));
        editor.apply();
    }

    public WeatherData loadCachedWeather() {
        return gson.fromJson(sharedPreferences.getString(CURRENT_WEATHER_KEY, null), WeatherData.class);
    }

    public int getUpdateInterval() {
        return Integer.decode(sharedPreferences
                .getString(UPDATE_INTERVAL_KEY, DEFAULT_UPDATE_INTERVAL));
    }

    public String getChosenCity() {
        return sharedPreferences.getString(CHOOSE_CITY_KEY, DEFAULT_CHOSEN_CITY);
    }
}
