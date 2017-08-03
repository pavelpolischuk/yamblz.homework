package com.gcteam.yamblz.homework.presentation.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.data.WeatherMapper;
import com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.data.local.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.WeatherService;
import com.gcteam.yamblz.homework.utils.PreferencesManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kim Michael on 01.08.17
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    WeatherService provideWeatherService(PreferencesManager preferencesManager,
                                         WeatherMapper weatherMapper,
                                         OpenWeatherMapApi api) {
        return new WeatherService(preferencesManager, weatherMapper, api);
    }

    @Provides
    @Singleton
    WeatherStorage provideWeatherStorage(PreferencesManager preferencesManager) {
        return new WeatherStorage(preferencesManager);
    }

    @Provides
    @Singleton
    WeatherMapper provideWeatherMapper() {
        return new WeatherMapper();
    }

    @Provides
    @Singleton
    PreferencesManager providePreferencesManager(SharedPreferences sharedPreferences, Gson gson) {
        return new PreferencesManager(sharedPreferences, gson);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }


}