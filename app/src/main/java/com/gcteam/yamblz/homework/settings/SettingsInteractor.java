package com.gcteam.yamblz.homework.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.weather.updating.UpdateJob;

/**
 * Created by turist on 16.07.2017.
 */

public class SettingsInteractor implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String UPDATE_INTERVAL_KEY = "update_interval_key";

    private SettingsView view;

    SettingsInteractor(SettingsView view) {
        this.view = view;
    }

    public static int getUpdateInterval(Context context) {
        return Integer.decode(PreferenceManager.getDefaultSharedPreferences(context)
                .getString(UPDATE_INTERVAL_KEY, "120"));
    }

    public void initView(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        view.updateSummary(UPDATE_INTERVAL_KEY, preferences.getString(UPDATE_INTERVAL_KEY, ""));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String value = sharedPreferences.getString(key, "");
        view.updateSummary(key, value);

        if(UPDATE_INTERVAL_KEY.equals(key)) {
            UpdateJob.startUpdate(Integer.decode(value));
        }
    }
}
