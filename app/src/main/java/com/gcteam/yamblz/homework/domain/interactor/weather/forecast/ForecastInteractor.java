package com.gcteam.yamblz.homework.domain.interactor.weather.forecast;

import com.gcteam.yamblz.homework.data.ForecastResponseMapper;
import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.object.ForecastData;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 07.08.17
 */
public class ForecastInteractor {

    private WeatherRepository weatherRepository;
    private Scheduler executionScheduler;
    private Scheduler postExecutionScheduler;
    private ForecastResponseMapper forecastResponseMapper;

    @Inject
    public ForecastInteractor(WeatherRepository weatherRepository,
                                    Scheduler executionScheduler,
                                    Scheduler postExecutionScheduler,
                                    ForecastResponseMapper forecastResponseMapper) {
        this.weatherRepository = weatherRepository;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
        this.forecastResponseMapper = forecastResponseMapper;
    }

    public Single<ForecastData> getForecast(String lat, String lon) {
        return weatherRepository.getForecastResponse(lat, lon)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler)
                .map(forecastResponseMapper);
    }
}
