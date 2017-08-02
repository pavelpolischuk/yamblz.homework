package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.presentation.di.module.WeatherModule;
import com.gcteam.yamblz.homework.presentation.di.module.WeatherScreenModule;
import com.gcteam.yamblz.homework.presentation.di.scope.WeatherScope;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by Kim Michael on 01.08.17
 */
@Subcomponent(modules = {WeatherModule.class})
@WeatherScope
public interface WeatherComponent {

    WeatherScreenComponent plus(WeatherScreenModule weatherScreenModule);
}
