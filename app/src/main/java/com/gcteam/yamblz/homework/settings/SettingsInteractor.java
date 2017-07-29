package com.gcteam.yamblz.homework.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.weather.updating.UpdateJob;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

/**
 * Created by turist on 16.07.2017.
 */

public class SettingsInteractor implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

    static final String UPDATE_INTERVAL_KEY = "update_interval_key";
    static final String CHOOSE_CITY_KEY = "city_key";
    static final String LAT_KEY = "lat_key";
    static final String LNG_KEY = "lng_key";

    private SettingsView view;
    private PreferencesManager preferencesManager;

    SettingsInteractor(SettingsView view, PreferencesManager preferencesManager) {
        this.view = view;
        this.preferencesManager = preferencesManager;
    }

    public void setPlace(Place place) {
        preferencesManager.savePlace(place);
    }

    public void initView() {
        view.updateSummary(UPDATE_INTERVAL_KEY, String.valueOf(preferencesManager.getUpdateInterval()));
        view.updateSummary(CHOOSE_CITY_KEY, preferencesManager.getChosenCity());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String value = sharedPreferences.getString(key, "");
        view.updateSummary(key, value);

        if(UPDATE_INTERVAL_KEY.equals(key)) {
            UpdateJob.startUpdate(Integer.decode(value));
        } else if (CHOOSE_CITY_KEY.equals(key)) {
            UpdateJob.startUpdate(Integer.decode(sharedPreferences.getString(UPDATE_INTERVAL_KEY, "")));
        }

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        view.showCityChooser();
        return false;
    }
}
