package com.gcteam.yamblz.homework.di.component;

import com.gcteam.yamblz.homework.di.module.AppModule;
import com.gcteam.yamblz.homework.weather.WeatherFragment;
import com.gcteam.yamblz.homework.weather.WeatherLoadingInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Kim Michael on 26.07.17
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(WeatherFragment weatherFragment);
    void inject(WeatherLoadingInteractor weatherLoadingInteractor);
}
