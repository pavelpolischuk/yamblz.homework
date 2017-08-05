package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.presentation.di.module.WeatherScreenModule;
import com.gcteam.yamblz.homework.presentation.di.scope.WeatherScreenScope;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherFragment;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 01.08.17
 */
@Subcomponent(modules = {WeatherScreenModule.class})
@WeatherScreenScope
public interface WeatherScreenComponent {

    void inject(WeatherFragment weatherFragment);
}
