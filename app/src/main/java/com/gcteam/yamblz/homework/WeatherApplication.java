package com.gcteam.yamblz.homework;

import android.app.Application;

import com.evernote.android.job.JobManager;
import com.gcteam.yamblz.homework.di.component.AppComponent;
import com.gcteam.yamblz.homework.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.di.module.AppModule;
import com.gcteam.yamblz.homework.main.WeatherJobCreator;
import com.gcteam.yamblz.homework.weather.WeatherStorage;

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
