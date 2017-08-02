package com.gcteam.yamblz.homework.presentation.di;

import android.app.Application;

import com.gcteam.yamblz.homework.presentation.di.component.AppComponent;
import com.gcteam.yamblz.homework.presentation.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.presentation.di.component.WeatherComponent;
import com.gcteam.yamblz.homework.presentation.di.component.WeatherScreenComponent;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.presentation.di.module.WeatherModule;
import com.gcteam.yamblz.homework.presentation.di.module.WeatherScreenModule;

/**
 * Created by Kim Michael on 01.08.17
 */
public class ComponentManager {
    AppComponent appComponent;
    WeatherComponent weatherComponent;
    WeatherScreenComponent weatherScreenComponent;

    public ComponentManager(Application app) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(app))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public WeatherComponent getWeatherComponent() {
        if (weatherComponent == null) {
            weatherComponent = appComponent.plus(new WeatherModule());
        }
        return weatherComponent;
    }

    public WeatherScreenComponent getWeatherScreenComponent() {
        if (weatherScreenComponent == null) {
            weatherScreenComponent = getWeatherComponent().plus(new WeatherScreenModule());
        }
        return weatherScreenComponent;
    }

    public void releaseWeatherComponent() {
        weatherComponent = null;
    }

    public void releaseWeatherScreenComponent() {
        weatherScreenComponent = null;
    }
}
