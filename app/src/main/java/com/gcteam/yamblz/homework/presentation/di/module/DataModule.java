package com.gcteam.yamblz.homework.presentation.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.gcteam.yamblz.homework.data.mapper.ForecastResponseMapper;
import com.gcteam.yamblz.homework.data.mapper.WeatherResponseMapper;
import com.gcteam.yamblz.homework.data.api.OpenWeatherMapApi;
import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;
import com.gcteam.yamblz.homework.domain.update.weather.UpdateWeatherJob;
import com.gcteam.yamblz.homework.presentation.presenter.settings.SettingsPresenter;
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
    CityStorage provideCityStorage(AppDatabase appDatabase) {
        return new CityStorage(appDatabase);
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
    WeatherStorage provideWeatherStorage(AppDatabase appDatabase,
                                         Gson gson) {
        return new WeatherStorage(appDatabase, gson);
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
    PreferencesManager providePreferencesManager(SharedPreferences sharedPreferences) {
        return new PreferencesManager(sharedPreferences);
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

    @Provides
    @Singleton
    SettingsPresenter provideSettingsPresenter(PreferencesManager preferencesManager,
                                               UpdateWeatherJob updateWeatherJob) {
        return new SettingsPresenter(preferencesManager, updateWeatherJob);
    }

    @Provides
    @Singleton
    UpdateWeatherJob provideUpdateWeatherJob() {
        return new UpdateWeatherJob();
    }

}
