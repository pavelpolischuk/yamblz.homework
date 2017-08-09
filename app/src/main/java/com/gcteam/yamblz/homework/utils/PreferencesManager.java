package com.gcteam.yamblz.homework.utils;

import android.content.SharedPreferences;

import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
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

    public static final String DEFAULT_UPDATE_INTERVAL = "3600";
    public static final String DEFAULT_CHOSEN_CITY = "Moscow";

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
        return sharedPreferences.getString(CHOSEN_CITY_KEY, DEFAULT_CHOSEN_CITY);
    }

    public void saveChosenCity(CityDetailsResponse cityDetailsResponse) {
        sharedPreferences.edit()
                .putString(LAT_KEY,
                        Double.toString(cityDetailsResponse.getResult().getGeometry().getLocation().getLat()))
                .putString(LNG_KEY,
                        Double.toString(cityDetailsResponse.getResult().getGeometry().getLocation().getLng()))
                .putString(CHOSEN_CITY_KEY,
                        cityDetailsResponse.getResult().getName())
                .apply();
    }
}
