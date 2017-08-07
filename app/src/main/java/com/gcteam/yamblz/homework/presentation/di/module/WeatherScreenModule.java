package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.domain.interactor.weather.current.CurrentWeatherInteractor;
import com.gcteam.yamblz.homework.presentation.di.scope.WeatherScreenScope;
import com.gcteam.yamblz.homework.presentation.presenter.weather.WeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kim Michael on 01.08.17
 */
@Module
public class WeatherScreenModule {

    @Provides
    @WeatherScreenScope
    public WeatherPresenter provideWeatherPresenter(CurrentWeatherInteractor currentWeatherInteractor) {
        return new WeatherPresenter(currentWeatherInteractor);
    }
}
