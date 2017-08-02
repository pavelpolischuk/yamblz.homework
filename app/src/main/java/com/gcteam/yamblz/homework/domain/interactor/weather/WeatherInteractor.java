package com.gcteam.yamblz.homework.domain.interactor.weather;

import com.gcteam.yamblz.homework.data.WeatherData;
import com.gcteam.yamblz.homework.data.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 02.08.17
 */
public class WeatherInteractor {

    WeatherRepository weatherRepository;
    Scheduler executionScheduler;
    Scheduler postExecutionScheduler;

    @Inject
    public WeatherInteractor(WeatherRepository weatherRepository,
                             Scheduler executionScheduler,
                             Scheduler postExecutionScheduler) {
        this.weatherRepository = weatherRepository;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    public Single<WeatherData> getWeather() {
        return weatherRepository.startRefresh()
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }
}
