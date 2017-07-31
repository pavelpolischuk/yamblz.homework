package com.gcteam.yamblz.homework.domain.settings;

import android.content.SharedPreferences;
import android.support.v7.preference.Preference;

import com.gcteam.yamblz.homework.presentation.view.settings.SettingsView;
import com.gcteam.yamblz.homework.presentation.view.settings.preference.CityPreference;
import com.gcteam.yamblz.homework.domain.update.UpdateJob;
import com.gcteam.yamblz.homework.utils.PreferencesManager;
import com.google.android.gms.location.places.Place;

/**
 * Created by turist on 16.07.2017.
 */

public class SettingsPresenter implements SharedPreferences.OnSharedPreferenceChangeListener,
        Preference.OnPreferenceClickListener {

    public static final String UPDATE_INTERVAL_KEY = "update_interval_key";
    public static final String CHOOSE_CITY_KEY = "city_key";
    public static final String LAT_KEY = "lat_key";
    public static final String LNG_KEY = "lng_key";

    private SettingsView view;
    private PreferencesManager preferencesManager;

    public SettingsPresenter(SettingsView view, PreferencesManager preferencesManager) {
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
        if (preference instanceof CityPreference)
            view.showCityChooser();
        return false;
    }
}
