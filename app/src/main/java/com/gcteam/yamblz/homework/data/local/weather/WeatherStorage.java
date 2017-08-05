package com.gcteam.yamblz.homework.data.local.weather;

import android.support.annotation.Nullable;

import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherStorage {

    private final Subject<WeatherData> weatherSubject = PublishSubject.create();
    private PreferencesManager preferencesManager;

    @Inject
    public WeatherStorage(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    @NonNull
    public Observable<WeatherData> lastWeather() {
        return weatherSubject;
    }

    @NonNull
    public Consumer<WeatherData> updateLastWeather() {
        return this::updateLastWeather;
    }

    @NonNull
    public void updateLastWeather(WeatherData weather) {
        save(weather);
        weatherSubject.onNext(weather);
    }

    public void save(@NonNull WeatherData weather) {
        preferencesManager.putCurrentWeather(weather);
    }

    @Nullable
    public WeatherData load() {
        return preferencesManager.loadCachedWeather();
    }
}
