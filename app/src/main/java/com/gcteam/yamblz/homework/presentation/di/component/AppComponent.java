package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.data.WeatherRepository;
import com.gcteam.yamblz.homework.domain.interactor.weather.WeatherInteractor;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;
import com.gcteam.yamblz.homework.presentation.di.module.DataModule;
import com.gcteam.yamblz.homework.presentation.di.module.NetworkModule;
import com.gcteam.yamblz.homework.presentation.di.module.SchedulersModule;
import com.gcteam.yamblz.homework.presentation.di.module.WeatherModule;
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
@Component(modules = {AppModule.class,
        DataModule.class,
        NetworkModule.class,
        SchedulersModule.class})
public interface AppComponent {

    WeatherComponent plus(WeatherModule weatherModule);

    void inject(UpdateJob updateJob);
    void inject(MainActivity mainActivity);
    void inject(SettingsFragment settingsFragment);
}
