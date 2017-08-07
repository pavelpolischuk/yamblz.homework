package com.gcteam.yamblz.homework.domain.interactor.weather.current;

import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 02.08.17
 */
public class CurrentWeatherInteractor {

    private static final long FETCHING_WEATHER_LIMIT_SECONDS = 10;

    private WeatherRepository weatherRepository;
    private Scheduler executionScheduler;
    private Scheduler postExecutionScheduler;

    @Inject
    public CurrentWeatherInteractor(WeatherRepository weatherRepository,
                                    Scheduler executionScheduler,
                                    Scheduler postExecutionScheduler) {
        this.weatherRepository = weatherRepository;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    public Single<WeatherData> getWeather() {
        return weatherRepository.getCurrentWeather()
                .timeout(FETCHING_WEATHER_LIMIT_SECONDS, TimeUnit.SECONDS)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

}
