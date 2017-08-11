package com.gcteam.yamblz.homework.presentation.navigation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.presentation.view.about.AboutFragment;
import com.gcteam.yamblz.homework.presentation.view.detail.DetailWeatherActivity;
import com.gcteam.yamblz.homework.presentation.view.detail.DetailWeatherFragment;
import com.gcteam.yamblz.homework.presentation.view.settings.SettingsFragment;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherFragment;

/**
 * Created by turist on 14.07.2017.
 */

public class MainActivityRouter implements MainRouter {

    private FragmentActivity activity;

    public MainActivityRouter(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void showWeather() {
        replaceFragment(new WeatherFragment(), Integer.toString(R.string.weather_title), true);
    }

    @Override
    public void showDetailedWeather(WeatherData weatherData, boolean isTwoPane) {
        if (isTwoPane) {
            DetailWeatherFragment fragment = new DetailWeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(DetailWeatherFragment.EXTRA_WEATHER_ARG, weatherData);
            fragment.setArguments(bundle);
            FragmentManager fm = activity.getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.detail_container, fragment, Integer.toString(R.string.detail_title))
                .commit();

        } else {
            Intent weatherDetailIntent = new Intent(activity, DetailWeatherActivity.class);
            weatherDetailIntent.putExtra(DetailWeatherActivity.EXTRA_WEATHER_DATA, weatherData);
            activity.startActivity(weatherDetailIntent);
        }
    }

    @Override
    public void showSettings() {
        activity.setTitle(R.string.settings_title);
        replaceFragment(new SettingsFragment(), Integer.toString(R.string.settings_title), false);
    }

    @Override
    public void showAbout() {
        activity.setTitle(R.string.about_title);
        replaceFragment(new AboutFragment(), Integer.toString(R.string.about_title), false);
    }

    protected void replaceFragment(Fragment fragmentToSet, String tag, boolean showOnTopOfBackStack) {
        removeDetailedView();
        FragmentManager manager = activity.getSupportFragmentManager();
        if (showOnTopOfBackStack) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        if (manager.findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = manager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .replace(R.id.content_main, fragmentToSet, tag);

            if (!showOnTopOfBackStack) {
                transaction.addToBackStack(null);
            }

            transaction.commit();
        }
    }

    protected void removeDetailedView() {
        FragmentManager fm = activity.getSupportFragmentManager();
        if (fm.findFragmentByTag(Integer.toString(R.string.detail_title)) != null) {
            fm.beginTransaction().remove(fm.findFragmentById(R.id.detail_container)).commit();
        }
    }
}
