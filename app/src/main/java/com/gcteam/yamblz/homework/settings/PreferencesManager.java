package com.gcteam.yamblz.homework.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

/**
 * Created by Kim Michael on 26.07.17
 */
public class PreferencesManager {


    SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public double getLat() {
        return Double.valueOf(sharedPreferences.getString(SettingsInteractor.LAT_KEY, ""));
    }

    public double getLng() {
        return Double.valueOf(sharedPreferences.getString(SettingsInteractor.LNG_KEY, ""));
    }
}
