package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.domain.update.weather.UpdateWeatherJob;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.presentation.di.module.DataModule;
import com.gcteam.yamblz.homework.presentation.di.module.NetworkModule;
import com.gcteam.yamblz.homework.presentation.di.module.SchedulersModule;
import com.gcteam.yamblz.homework.presentation.di.module.WeatherModule;
import com.gcteam.yamblz.homework.presentation.view.main.MainActivity;
import com.gcteam.yamblz.homework.presentation.view.settings.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Kim Michael on 26.07.17
 */
@Singleton
@Component(modules = {AppModule.class,
        DataModule.class,
        NetworkModule.class,
        SchedulersModule.class})
public interface AppComponent {

    WeatherComponent plus(WeatherModule weatherModule);

    void inject(UpdateWeatherJob updateWeatherJob);
    void inject(MainActivity mainActivity);
    void inject(SettingsFragment settingsFragment);
}
