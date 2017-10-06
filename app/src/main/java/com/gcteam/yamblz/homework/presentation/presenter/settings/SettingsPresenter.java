package com.gcteam.yamblz.homework.presentation.presenter.settings;

import android.content.SharedPreferences;

import com.gcteam.yamblz.homework.domain.update.weather.UpdateWeatherJob;
import com.gcteam.yamblz.homework.presentation.view.settings.SettingsView;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import static com.gcteam.yamblz.homework.utils.PreferencesManager.CHOSEN_CITY_ID_KEY;
import static com.gcteam.yamblz.homework.utils.PreferencesManager.CHOSEN_CITY_KEY;
import static com.gcteam.yamblz.homework.utils.PreferencesManager.DEFAULT_UNITS;
import static com.gcteam.yamblz.homework.utils.PreferencesManager.NO_CHOSEN_CITY;
import static com.gcteam.yamblz.homework.utils.PreferencesManager.UNITS_KEY;
import static com.gcteam.yamblz.homework.utils.PreferencesManager.UPDATE_INTERVAL_KEY;

/**
 * Created by turist on 16.07.2017.
 */

public class SettingsPresenter implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SettingsView settingsView;
    private PreferencesManager preferencesManager;
    private UpdateWeatherJob updateWeatherJob;

    public SettingsPresenter(PreferencesManager preferencesManager,
                             UpdateWeatherJob updateWeatherJob) {
        this.preferencesManager = preferencesManager;
        this.updateWeatherJob = updateWeatherJob;
    }

    public void initView(SettingsView settingsView) {
        this.settingsView = settingsView;
        this.settingsView.updateSummary(UPDATE_INTERVAL_KEY, String.valueOf(preferencesManager.getUpdateInterval()));
        this.settingsView.updateSummary(CHOSEN_CITY_KEY, preferencesManager.getChosenCity());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String value;
        switch (key) {
            case UPDATE_INTERVAL_KEY:
                value = sharedPreferences.getString(key, "");
                updateWeatherJob.startUpdate(Integer.decode(value));
                settingsView.updateSummary(key, value);
                break;
            case CHOSEN_CITY_KEY:
                value = sharedPreferences.getString(key, NO_CHOSEN_CITY);
                updateWeatherJob.startUpdate(Integer.decode(sharedPreferences.getString(UPDATE_INTERVAL_KEY, "")));
                settingsView.updateSummary(key, value);
                break;
            case UNITS_KEY:
                value = sharedPreferences.getString(key, DEFAULT_UNITS);
                settingsView.updateSummary(key, value);
            case CHOSEN_CITY_ID_KEY:
            default:
        }

    }

}
