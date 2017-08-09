package com.gcteam.yamblz.homework.presentation.presenter.settings;

import android.content.SharedPreferences;

import com.gcteam.yamblz.homework.domain.update.weather.UpdateWeatherJob;
import com.gcteam.yamblz.homework.presentation.view.settings.SettingsView;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import static com.gcteam.yamblz.homework.utils.PreferencesManager.CHOSEN_CITY_KEY;
import static com.gcteam.yamblz.homework.utils.PreferencesManager.UPDATE_INTERVAL_KEY;

/**
 * Created by turist on 16.07.2017.
 */

public class SettingsPresenter implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SettingsView view;
    private PreferencesManager preferencesManager;

    public SettingsPresenter(SettingsView view, PreferencesManager preferencesManager) {
        this.view = view;
        this.preferencesManager = preferencesManager;
    }

    public void initView() {
        view.updateSummary(UPDATE_INTERVAL_KEY, String.valueOf(preferencesManager.getUpdateInterval()));
        view.updateSummary(CHOSEN_CITY_KEY, preferencesManager.getChosenCity());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String value = sharedPreferences.getString(key, "");
        view.updateSummary(key, value);

        if (UPDATE_INTERVAL_KEY.equals(key)) {
            UpdateWeatherJob.startUpdate(Integer.decode(value));
        } else if (CHOSEN_CITY_KEY.equals(key)) {
            UpdateWeatherJob.startUpdate(Integer.decode(sharedPreferences.getString(UPDATE_INTERVAL_KEY, "")));
        }

    }

}
