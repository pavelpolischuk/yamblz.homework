package com.gcteam.yamblz.homework.main;

import android.app.Application;

import com.evernote.android.job.JobManager;
import com.gcteam.yamblz.homework.weather.WeatherStorage;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        WeatherStorage.init(getApplicationContext());
        JobManager.create(this).addJobCreator(new WeatherJobCreator());
    }
}
