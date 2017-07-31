package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.data.WeatherRepository;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.presentation.main.MainActivity;
import com.gcteam.yamblz.homework.presentation.view.settings.SettingsFragment;
import com.gcteam.yamblz.homework.presentation.view.weather.WeatherFragment;
import com.gcteam.yamblz.homework.domain.update.UpdateJob;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Kim Michael on 26.07.17
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(WeatherFragment weatherFragment);
    void inject(WeatherRepository weatherRepository);
    void inject(SettingsFragment settingsFragment);
    void inject(MainActivity mainActivity);
    void inject(UpdateJob updateJob);
}
