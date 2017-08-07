package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.data.repository.weather.WeatherRepository;
import com.gcteam.yamblz.homework.domain.interactor.weather.current.CurrentWeatherInteractor;
import com.gcteam.yamblz.homework.presentation.di.scope.WeatherScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Kim Michael on 01.08.17
 */
@Module
public class WeatherModule {

    @Provides
    @WeatherScope
    public CurrentWeatherInteractor provideWeatherInteractor(
            WeatherRepository weatherRepository,
            @Named(SchedulersModule.JOB) Scheduler executionScheduler,
            @Named(SchedulersModule.UI) Scheduler postExecutionScheduler) {
        return new CurrentWeatherInteractor(
                weatherRepository,
                executionScheduler,
                postExecutionScheduler);
    }

}
