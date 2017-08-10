package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.domain.update.weather.UpdateWeatherJob;
import com.gcteam.yamblz.homework.presentation.di.module.WeatherModule;
import com.gcteam.yamblz.homework.presentation.di.scope.WeatherScope;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 01.08.17
 */
@Subcomponent(modules = {WeatherModule.class})
@WeatherScope
public interface WeatherComponent {

    WeatherScreenComponent getWeatherScreenComponent();

    void inject(UpdateWeatherJob updateWeatherJob);
}
