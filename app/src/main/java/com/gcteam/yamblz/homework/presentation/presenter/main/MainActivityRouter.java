package com.gcteam.yamblz.homework.presentation.presenter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.gcteam.yamblz.homework.presentation.view.about.AboutFragment;
import com.gcteam.yamblz.homework.R;
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
        activity.setTitle(R.string.weather_title);
        replaceFragment(new WeatherFragment(), R.string.weather_title +"", true);
    }

    @Override
    public void showSettings() {
        activity.setTitle(R.string.settings_title);
        replaceFragment(new SettingsFragment(), R.string.settings_title +"", true);
    }

    @Override
    public void showAbout() {
        activity.setTitle(R.string.about_title);
        replaceFragment(new AboutFragment(), R.string.about_title +"", true);
    }

    protected void replaceFragment(Fragment fragmentToSet, String tag, boolean showOnTopOfBackStack) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(showOnTopOfBackStack) {
            boolean removed = manager.popBackStackImmediate();
            while (removed) {
                removed = manager.popBackStackImmediate();
            }
        }

        if(manager.findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = manager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .replace(R.id.content_main, fragmentToSet, tag);

            if(!showOnTopOfBackStack) {
                transaction.addToBackStack(null);
            }

            transaction.commit();
        }
    }
}
