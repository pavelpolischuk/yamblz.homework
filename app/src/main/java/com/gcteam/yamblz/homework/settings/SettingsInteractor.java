package com.gcteam.yamblz.homework.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.weather.updating.UpdateJob;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by turist on 16.07.2017.
 */

public class SettingsInteractor implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

    static final String UPDATE_INTERVAL_KEY = "update_interval_key";
    static final String CHOOSE_CITY_KEY = "city_key";
    static final String LATLNG_KEY = "latlng_key";

    private SettingsView view;

    SettingsInteractor(SettingsView view) {
        this.view = view;
    }

    public static int getUpdateInterval(Context context) {
        return Integer.decode(PreferenceManager.getDefaultSharedPreferences(context)
                .getString(UPDATE_INTERVAL_KEY, "120"));
    }

    public void setPlace(Place place, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit()
                .putString(LATLNG_KEY, place.getLatLng().toString())
                .putString(CHOOSE_CITY_KEY, place.getName().toString())
                .apply();
    }

    public void initView(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        view.updateSummary(UPDATE_INTERVAL_KEY, preferences.getString(UPDATE_INTERVAL_KEY, ""));
        view.updateSummary(CHOOSE_CITY_KEY, preferences.getString(CHOOSE_CITY_KEY, ""));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String value = sharedPreferences.getString(key, "");
        view.updateSummary(key, value);

        if(UPDATE_INTERVAL_KEY.equals(key)) {
            UpdateJob.startUpdate(Integer.decode(value));
        }

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        view.showCityChooser();
        return false;
    }
}
