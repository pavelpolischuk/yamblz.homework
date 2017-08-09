package com.gcteam.yamblz.homework.presentation.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.data.ForecastResponseMapper;
import com.gcteam.yamblz.homework.data.WeatherResponseMapper;
import com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
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
    CityStorage provideCityStorage(PreferencesManager preferencesManager,
                                   AppDatabase appDatabase) {
        return new CityStorage(preferencesManager, appDatabase);
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(PreferencesManager preferencesManager,
                                         WeatherResponseMapper weatherResponseMapper,
                                         OpenWeatherMapApi api) {
        return new WeatherService(preferencesManager, weatherResponseMapper, api);
    }

    @Provides
    @Singleton
    WeatherStorage provideWeatherStorage(PreferencesManager preferencesManager,
                                         AppDatabase appDatabase,
                                         Gson gson) {
        return new WeatherStorage(preferencesManager, appDatabase, gson);
    }

    @Provides
    @Singleton
    WeatherResponseMapper provideWeatherMapper() {
        return new WeatherResponseMapper();
    }

    @Provides
    @Singleton
    ForecastResponseMapper provideForecastResponseMapper() {
        return new ForecastResponseMapper();
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

    @Provides
    @Singleton
    AppDatabase provideAppDataBase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "weather").build();
    }

}
