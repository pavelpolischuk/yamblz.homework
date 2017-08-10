package com.gcteam.yamblz.homework.utils;

import android.content.SharedPreferences;

import com.gcteam.yamblz.homework.data.object.StoredChosenCity;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.google.gson.Gson;

/**
 * Created by Kim Michael on 26.07.17
 */
public class PreferencesManager {

    public static final String UPDATE_INTERVAL_KEY = "update_interval_key";
    public static final String CHOSEN_CITY_KEY = "city_key";
    public static final String LAT_KEY = "lat_key";
    public static final String LNG_KEY = "lng_key";
    public static final String CURRENT_WEATHER_KEY = "current_weather_key";
    public static final String UNITS_KEY = "units_key";
    public static final String CHOSEN_CITY_ID_KEY = "chosen_city_id_key";

    public static final String DEFAULT_UPDATE_INTERVAL = "3600";
    public static final String NO_CHOSEN_CITY = "no_chosen_city";

    private SharedPreferences sharedPreferences;
    private Gson gson;


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
        return sharedPreferences.getString(CHOSEN_CITY_KEY, NO_CHOSEN_CITY);
    }

    public int getChosenCityId() {
        return sharedPreferences.getInt(CHOSEN_CITY_ID_KEY, 0);
    }

    public void saveChosenCity(StoredChosenCity storedChosenCity) {
        sharedPreferences.edit()
                .putString(LAT_KEY,
                        Double.toString(storedChosenCity.getLat()))
                .putString(LNG_KEY,
                        Double.toString(storedChosenCity.getLon()))
                .putString(CHOSEN_CITY_KEY,
                        storedChosenCity.getCityName())
                .putInt(CHOSEN_CITY_ID_KEY,
                        storedChosenCity.getPriority())
                .apply();
    }

    public void saveNoChosenCity() {
        sharedPreferences.edit()
                .putString(LAT_KEY,
                        Double.toString(-1L))
                .putString(LNG_KEY,
                        Double.toString(-1L))
                .putString(CHOSEN_CITY_KEY,
                        NO_CHOSEN_CITY)
                .putInt(CHOSEN_CITY_ID_KEY,
                        -1)
                .apply();
    }
}
