package com.gcteam.yamblz.homework.domain.interactor.weather;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.presentation.di.module.SchedulersModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by Kim Michael on 07.08.17
 */
public class WeatherInteractor {

    private WeatherRepository weatherRepository;
    private Scheduler executionScheduler;
    private Scheduler postExecutionScheduler;
    private FullWeatherReport cachedFullWeatherReport;
    private double cachedLat;
    private double cachedLng;

    @Inject
    public WeatherInteractor(
            WeatherRepository weatherRepository,
            @Named(SchedulersModule.JOB) Scheduler executionScheduler,
            @Named(SchedulersModule.UI) Scheduler postExecutionScheduler) {
        this.weatherRepository = weatherRepository;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    @WorkerThread
    @NonNull
    public Single<FullWeatherReport> getWeather(double lat, double lng, boolean forceUpdate) {
        Timber.d("Getting weather for %1.0f lat and %1.0f lng", lat, lng);
        if (!forceUpdate && cachedLat == lat && cachedLng == lng) {
            Timber.d("Using cached weather for getting weather");
            return Single.just(cachedFullWeatherReport);
        }
        Timber.d("Using repository for getting weather");
        return weatherRepository.getFullWeatherReport(lat, lng)
                .doOnSuccess(fullWeatherReport -> {
                    cachedFullWeatherReport = fullWeatherReport;
                    cachedLat = lat;
                    cachedLng = lng;
                    weatherRepository.saveWeather(fullWeatherReport);
                })
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }
}
