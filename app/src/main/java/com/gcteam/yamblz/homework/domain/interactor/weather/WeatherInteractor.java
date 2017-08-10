package com.gcteam.yamblz.homework.domain.interactor.weather;

import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.presentation.di.module.SchedulersModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

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

    public Single<FullWeatherReport> getWeather(double lat, double lng, boolean forceUpdate) {
        if (!forceUpdate && cachedLat == lat && cachedLng == lng) {
            return Single.just(cachedFullWeatherReport);
        }
        return Single.zip(weatherRepository.getForecast(lat, lng)
                        .subscribeOn(executionScheduler),
                weatherRepository.getWeather(lat, lng)
                        .subscribeOn(executionScheduler),
                (forecastData, weatherData) ->
                        new FullWeatherReport(lat, lng, forecastData, weatherData))
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
