package com.gcteam.yamblz.homework;

import android.app.Application;

import com.evernote.android.job.JobManager;
import com.gcteam.yamblz.homework.presentation.di.component.AppComponent;
import com.gcteam.yamblz.homework.presentation.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.presentation.main.WeatherJobCreator;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherApplication extends Application {

    private static WeatherApplication instance;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        JobManager.create(this).addJobCreator(new WeatherJobCreator());
    }

    private static void setInstance(WeatherApplication instance) {
        WeatherApplication.instance = instance;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance.getApplicationContext()))
                .build();

    }

    public static WeatherApplication getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
