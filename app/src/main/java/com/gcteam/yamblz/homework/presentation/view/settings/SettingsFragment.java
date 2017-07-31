package com.gcteam.yamblz.homework.presentation.view.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.WeatherApplication;
import com.gcteam.yamblz.homework.utils.PreferencesManager;
import com.gcteam.yamblz.homework.domain.settings.SettingsPresenter;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import javax.inject.Inject;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by turist on 07.07.2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SettingsView {

    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

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
        WeatherApplication.getInstance().getAppComponent().inject(this);
        interactor = new SettingsPresenter(this, preferencesManager);
        interactor.initView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(interactor);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                interactor.setPlace(place);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(interactor);
        Preference cityPreference = findPreference(SettingsPresenter.CHOOSE_CITY_KEY);
        cityPreference.setOnPreferenceClickListener(interactor);
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

    public void showCityChooser() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(new AutocompleteFilter.Builder()
                                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                                    .build())
                            .build(getActivity());
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Toast.makeText(getContext(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }
    }


}
