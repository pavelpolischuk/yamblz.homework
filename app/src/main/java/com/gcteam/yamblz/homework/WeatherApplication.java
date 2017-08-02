package com.gcteam.yamblz.homework;

import android.app.Application;

import com.evernote.android.job.JobManager;
import com.gcteam.yamblz.homework.presentation.di.ComponentManager;
import com.gcteam.yamblz.homework.presentation.di.component.AppComponent;
import com.gcteam.yamblz.homework.presentation.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.presentation.main.WeatherJobCreator;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherApplication extends Application {

    private static ComponentManager componentManager;

    @Override
    public void onCreate() {
        super.onCreate();
        componentManager = new ComponentManager(this);
        JobManager.create(this).addJobCreator(new WeatherJobCreator());
    }

    public static ComponentManager getComponentManager() {return componentManager;}

}
