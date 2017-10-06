package com.gcteam.yamblz.homework.presentation.di.module;

import com.gcteam.yamblz.homework.data.api.GooglePlacesAPI;
import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.network.cities.CityService;
import com.gcteam.yamblz.homework.data.network.cities.GoogleCityService;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepositoryImpl;
import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.presentation.di.scope.CityFilterScope;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Kim Michael on 03.08.17
 */
@Module
public class CityModule {

    @Provides
    @CityFilterScope
    public CityFilterInteractor provideCityInteractor(
            CityRepository cityRepository,
            @Named(SchedulersModule.JOB) Scheduler executionScheduler,
            @Named(SchedulersModule.UI) Scheduler postExecutionScheduler) {
        return new CityFilterInteractor(
                cityRepository,
                executionScheduler,
                postExecutionScheduler
        );
    }

    @Provides
    @CityFilterScope
    public CityRepository provideCitiesRepository(
            CityStorage сityStorage,
            CityService cityService,
            PreferencesManager preferencesManager) {
        return new CityRepositoryImpl(сityStorage,
                cityService,
                preferencesManager);
    }

    @Provides
    @CityFilterScope
    public CityService provideCitiesService(GooglePlacesAPI googlePlacesAPI) {
        return new GoogleCityService(googlePlacesAPI);
    }
}
