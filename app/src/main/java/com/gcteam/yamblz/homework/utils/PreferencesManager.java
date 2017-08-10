package com.gcteam.yamblz.homework.utils;

import android.content.SharedPreferences;

import com.gcteam.yamblz.homework.data.object.StoredChosenCity;

import timber.log.Timber;

/**
 * Created by Kim Michael on 26.07.17
 */
public class PreferencesManager {

    public static final String UPDATE_INTERVAL_KEY = "update_interval_key";
    public static final String CHOSEN_CITY_KEY = "city_key";
    public static final String LAT_KEY = "lat_key";
    public static final String LNG_KEY = "lng_key";
    public static final String UNITS_KEY = "units_key";
    public static final String CHOSEN_CITY_ID_KEY = "chosen_city_id_key";

    public static final String DEFAULT_UPDATE_INTERVAL = "3600";
    public static final String NO_CHOSEN_CITY = "no_chosen_city";

    private SharedPreferences sharedPreferences;

    public PreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public double getLat() {
        return Double.valueOf(sharedPreferences.getString(LAT_KEY, "0d"));
    }

    public double getLng() {
        return Double.valueOf(sharedPreferences.getString(LNG_KEY, "0d"));
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
        Timber.d("Chosen city is %s with %d id", storedChosenCity.getCityName(), storedChosenCity.getPriority());
        sharedPreferences.edit()
                .putString(LAT_KEY,
                        Double.toString(storedChosenCity.getLat()))
                .putString(LNG_KEY,
                        Double.toString(storedChosenCity.getLng()))
                .putString(CHOSEN_CITY_KEY,
                        storedChosenCity.getCityName())
                .putInt(CHOSEN_CITY_ID_KEY,
                        storedChosenCity.getPriority())
                .commit();
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
