package com.gcteam.yamblz.homework.presentation.view.settings;

import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.WeatherApplication;
import com.gcteam.yamblz.homework.presentation.presenter.settings.SettingsPresenter;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import javax.inject.Inject;

/**
 * Created by turist on 07.07.2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SettingsView {

    SettingsPresenter interactor;
    @Inject
    PreferencesManager preferencesManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        WeatherApplication.getComponentManager().getAppComponent().inject(this);
        interactor = new SettingsPresenter(this, preferencesManager);
        interactor.initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(interactor);
    }

    @Override
    public void updateSummary(String key, String value) {
        Preference preference = findPreference(key);
        if (preference == null) {
            return;
        }

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            preference.setSummary(value);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(interactor);
    }

}
