package com.gcteam.yamblz.homework.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.weather.WeatherService;
import com.gcteam.yamblz.homework.weather.WeatherStorage;
import com.gcteam.yamblz.homework.weather.api.Weather;
import com.gcteam.yamblz.homework.weather.api.WeatherMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kim Michael on 26.07.17
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(@NonNull Context appContext) {
        this.context = appContext;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(PreferencesManager preferencesManager, WeatherMapper weatherMapper) {
        return new WeatherService(preferencesManager, weatherMapper);
    }

    @Provides
    @Singleton
    WeatherStorage provideWeatherStorage(Context context) {
        return new WeatherStorage(context);
    }

    @Provides
    @Singleton
    WeatherMapper provideWeatherMapper() {
        return new WeatherMapper();
    }

    @Provides
    @Singleton
    PreferencesManager providePreferencesManager(Context context) {
        return new PreferencesManager(context);
    }


}
